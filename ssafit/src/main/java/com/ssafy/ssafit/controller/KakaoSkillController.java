package com.ssafy.ssafit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.AlarmSetting;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.service.alarmService.AlarmService;
import com.ssafy.ssafit.service.alarmSettingService.AlarmSettingService;
import com.ssafy.ssafit.service.mealService.MealService;
import com.ssafy.ssafit.service.userService.UserService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.joda.time.field.StrictDateTimeField;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class KakaoSkillController {
    private final ChatgptService chatgptService;
    private final MealService mealService;
    private final AlarmService alarmService;
    private final AlarmSettingService alarmSettingService;

    @PostMapping("/skill")
    public Map<String, String> checkFoodkcal(@RequestBody Map<String, Object> event) {
        Map<String, String> params = getActionParams(event);
        String food = params.get("food");
        String gram = params.get("gram");
        String user = getUserId(event);

        String reply = chatgptService.sendMessage(String.format("%s %s의 칼로리를 '[kcal: ]' 형식으로 알려줘", food, gram));
        String kcal = extractKcal(reply);

        if (params.containsKey("delete")) {
            mealService.removeMeal(user);
        }

        Meal newMeal = new Meal(null, user, food, kcal, LocalDateTime.now());
        mealService.createMeal(newMeal);
        return createResponseMap(kcal);
    }

    private String getUserId(Map<String, Object> event) {
        Map<String, Object> userRequest = (Map<String, Object>) event.get("userRequest");
        System.out.println("userRequest = " + userRequest);
        Map<String, Object> user = (Map<String, Object>) userRequest.get("user");
        System.out.println("user = " + user);
        return (String) user.get("id");
    }

    private Map<String, String> getActionParams(Map<String, Object> event) {
        Map<String, Object> action = (Map<String, Object>) event.get("action");
        return (Map<String, String>) action.get("params");
    }

    private String extractKcal(String reply) {
        int startIdx = reply.indexOf(':');
        int endIdx = reply.indexOf(']', startIdx);

        return reply.substring(startIdx + 1, endIdx);
    }

    private Map<String, String> createResponseMap(String kcal) {
        Map<String, String> map = new HashMap<>();
        map.put("kcal", kcal + "kcal");

        return map;
    }

    @PostMapping("/skill/search")
    public Map<String, String> searchMeal(@RequestBody Map<String, Object> event) {
        Map<String, String> params = getActionParams(event);
        String period = params.get("period");
        String user = getUserId(event);

        Optional<List<Meal>> list = Optional.ofNullable(mealService.getMealAllByUser(user));
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        StringBuilder result = new StringBuilder();
        System.out.println("period = " + period);

        list.ifPresent(mealList -> {
            LocalDateTime before = switch (period) {
                case "이번 주" -> now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                case "이번 달" -> now.with(TemporalAdjusters.firstDayOfMonth());
                case "최근 일 년" -> now.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                default -> now.minusYears(3).with(TemporalAdjusters.firstDayOfYear());
            };

            for (Meal meal : mealList) {
                if (meal.getTime().isAfter(before)) {
                    result
                            .append(meal.getTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))
                            .append(" ")
                            .append(meal.getTime().getHour() >= 17 ? "저녁" : meal.getTime().getHour() >= 12 ? "점심" : "아침")
                            .append("\n")
                            .append(meal.getFood())
                            .append("(")
                            .append(meal.getKcal().trim())
                            .append("kcal)\n");


                }
            }
        });

        Map<String, String> map = new HashMap<>();
        map.put("result", result.toString());
        System.out.println("map.get(\"result\") = " + map.get("result"));
        return map;
    }

    @PostMapping("/dateTime")
    public Map<String, String> handleChatbotDateRequest(@RequestBody Map<String, Object> event) throws JsonProcessingException {
        Map<String, Object> action = (Map<String, Object>) event.get("action");
        Map<String, Object> params = (Map<String, Object>) action.get("params");
        String healthtimeMap = (String) params.get("healthtime");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> result = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(healthtimeMap);
            String value = jsonNode.get("value").asText();

            AlarmSetting alarmSetting = new AlarmSetting();
            alarmSetting.setDate(value);
            alarmSetting.setUser(getUserId(event));
            alarmSettingService.insertAlarmSetting(alarmSetting);

            result.put("settingTime", value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping("/nowTime")
    public Map<String, Object> nowTime(@RequestBody Map<String, Object> event) {
        System.out.println("event = " + event);
        String user = getUserId(event);

        AlarmSetting alarmSetting = alarmSettingService.findAlarmSettingByUser(user);
        String alarmSettingTime = alarmSetting.getDate();

        LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime inputTime = LocalTime.parse(alarmSettingTime, formatter);

        boolean isBefore = currentTime.isBefore(inputTime);

        Alarm alarm = new Alarm(null, user, LocalDateTime.now(ZoneId.of("Asia/Seoul")), isBefore);
        alarmService.insertAlarm(alarm);
        String msg = "";

        if (isBefore) {
            msg = "시간을 엄수해 주셔서 정말 고마워요! 꾸준히 운동에 최선을 다하는 모습이 멋져요";
        } else {
            msg = "늦어서 안타깝네요. 앞으로는 엄수해 주실거죠?";
        }

        return getStringObjectMap(msg);
    }

    private Map<String, Object> getStringObjectMap(String msg) {
        String jsonString = "{\n" +
                "  \"version\": \"2.0\",\n" +
                "  \"template\": {\n" +
                "    \"outputs\": [\n" +
                "      {\n" +
                "        \"simpleText\": {\n" +
                "          \"text\": \"" + msg + "\"\n" + // Added double quotes around msg
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(jsonString, Map.class);
            System.out.println(jsonMap);
            return jsonMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/listTime")
    public Map<String, Object> getDateList(@RequestBody Map<String, Object> event) {
        List<Alarm> alarms = alarmService.findAlarmsByUser(getUserId(event));
        StringBuilder sb = new StringBuilder();
        for (Alarm alarm : alarms) {
            LocalDateTime date = alarm.getDate();
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
            sb.append(formattedDate).append("\n");

        }

        String list = sb.toString().replace("\n", "\\n");
        return getStringObjectMap(list);
    }

    @PostMapping("/deleteTime")
    public ResponseEntity<Void> delete(@RequestBody Map<String, Object> event) {
        String user = getUserId(event);
        AlarmSetting alarmSetting = alarmSettingService.findAlarmSettingByUser(user);
        alarmService.removeAlarm(user);
        alarmSettingService.removeAlarmSetting(alarmSetting);

        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PostMapping("/updateSettingTime")
//    public Map<String, String> handleChatbotUpdateDateRequest(@RequestBody Map<String, Object> event) {
//        String healthtime = getActionParams(event).get("healthtime");
//        String user = getUserId(event);
//
//        alarmSettingService.removeAlarmSetting(alarmSettingService.findAlarmSettingByUser(user));
//
//        Map<String, String> result = new HashMap<>();
//        AlarmSetting alarmSetting = new AlarmSetting();
//        alarmSetting.setDate(healthtime);
//        alarmSetting.setUser(getUserId(event));
//        alarmSettingService.updateAlarmSetting(alarmSetting);
//
//        result.put("settingTime", healthtime);
//
//        return result;
//    }
}