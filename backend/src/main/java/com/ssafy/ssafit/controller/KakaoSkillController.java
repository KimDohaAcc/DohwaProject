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
import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatRequest;
import io.github.flashvayne.chatgpt.property.ChatgptProperties;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/skill")
public class KakaoSkillController {
    private final ChatgptService chatgptService;
    private final ChatgptProperties chatgptProperties;
    private final MealService mealService;
    private final AlarmService alarmService;
    private final AlarmSettingService alarmSettingService;

    @PostMapping("/meal")
    public Map<String, String> checkFoodkcal(@RequestBody Map<String, Object> event) {
        Map<String, String> params = getActionParams(event);
        String food = params.get("food");
        String gram = params.get("gram");
        String user = getUserId(event);

        String reply = chatgptService.sendMessage(String.format("%s %s의 칼로리를 '[kcal: ]' 형식으로 알려줘", food, gram));
        String kcal = extractKcal(reply);
        System.out.println("reply = " + reply);
        Meal newMeal = new Meal(null, user, food, kcal, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        mealService.createMeal(newMeal);
        return createResponseMap(kcal);
    }

    @PostMapping("/meal/delete")
    public Map<String, String> deleteMeal(@RequestBody Map<String, Object> event) {
        String user = getUserId(event);
        mealService.removeMeal(user);
        Map<String, String> map = new HashMap<>();
        map.put("delete", "ok");
        return map;
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

    @PostMapping("/meal/search")
    public Map<String, Object> searchMeal(@RequestBody Map<String, Object> event) {
        Map<String, String> params = getActionParams(event);
        String period = params.get("period");
        String user = getUserId(event);
        System.out.println("user = " + user);

        Optional<List<Meal>> list = Optional.ofNullable(mealService.getMealAllByUser(user));
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        System.out.println("period = " + period);

        return list.map(mealList -> {
                    LocalDateTime before = switch (period) {
                        case "이번 주" -> now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                        case "이번 달" -> now.with(TemporalAdjusters.firstDayOfMonth());
                        case "올해" -> now.with(TemporalAdjusters.firstDayOfYear());
                        default -> now.minusYears(3).with(TemporalAdjusters.firstDayOfYear());
                    };

                    return sendList(period + " 식단 조회", period + " 동안 기록한 식단을 알려드릴게요!", returnItemList(mealList, before));
                })
                .orElseGet(() -> getStringObjectMap("기록한 식단이 존재하지 않습니다. 헬스판다봇과 함께 식단을 기록해보세요!"));
    }

    public List<Map<String, Object>> returnItemList(List<Meal> list, LocalDateTime before) {
        List<Map<String, Object>> itemList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Meal meal = list.get(i);
            if (meal.getTime().isAfter(before)) {
                String key = "(" + (i + 1) + ")";
                String value = "[" + meal.getTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                        + " " + (meal.getTime().getHour() >= 17 ? "저녁" : meal.getTime().getHour() >= 12 ? "점심" : "아침") + "] " + meal.getFood() + " " + meal.getKcal().trim() + "kcal";
                itemList.add(createItem(key, value));
            }
        }

        return itemList;
    }

    public Map<String, Object> sendList(String title, String description, List<Map<String, Object>> list) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> itemCardMap = new HashMap<>();
        Map<String, Object> imageTitleMap = new HashMap<>();
        imageTitleMap.put("title", title);
        imageTitleMap.put("description", description);

        Map<String, Object> profileMap = new HashMap<>();
        profileMap.put("title", "healthpanda");
        profileMap.put("imageUrl", "https://i.ibb.co/0D7BKFc/logo.jpg");

        itemCardMap.put("imageTitle", imageTitleMap);
        itemCardMap.put("title", "");
        itemCardMap.put("description", "");
        itemCardMap.put("profile", profileMap);
        itemCardMap.put("itemList", list);
        itemCardMap.put("itemListAlignment", "left");

        Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("outputs", Collections.singletonList(Collections.singletonMap("itemCard", itemCardMap)));

        resultMap.put("template", templateMap);
        resultMap.put("version", "2.0");

        return resultMap;
    }

    private static Map<String, Object> createItem(String title, String description) {
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put("title", title);
        itemMap.put("description", description);
        return itemMap;
    }

    @PostMapping("/alarm/dateTime")
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

    @PostMapping("/alarm/nowTime")
    public Map<String, Object> nowTime(@RequestBody Map<String, Object> event) {
        System.out.println("event = " + event);
        String user = getUserId(event);

        Optional<AlarmSetting> opt = Optional.ofNullable(alarmSettingService.findAlarmSettingByUser(user));
        if (opt.isEmpty()) {
            return getStringObjectMap("출석 시간 설정을 하지 않아 기록할 수 없어요. 시간을 먼저 설정해주세요");
        }

        AlarmSetting alarmSetting = opt.get();
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

    public List<Map<String, Object>> returnTimeList(List<Alarm> list) {
        List<Map<String, Object>> itemList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Alarm alarm = list.get(i);
            LocalDateTime date = alarm.getDate();
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
            itemList.add(createItem("(" + (i + 1) + ")", formattedDate));
        }

        return itemList;
    }

    @PostMapping("/alarm/listTime")
    public Map<String, Object> getDateList(@RequestBody Map<String, Object> event) {
        List<Alarm> alarms = alarmService.findAlarmsByUser(getUserId(event));
        if (alarms.size() > 0) {
            return sendList("출석 조회", "꾸준히 운동하는 모습이 멋져요!", returnTimeList(alarms));
        } else {
            return getStringObjectMap("출석 기록이 존재하지 않아요. 출석 시간을 설정하고 헬스판다봇과 함께 운동해봐요!");
        }
    }

    @PostMapping("/alarm/deleteTime")
    public Map<String, Object> delete(@RequestBody Map<String, Object> event) {
        String user = getUserId(event);
        return Optional.ofNullable(alarmSettingService.findAlarmSettingByUser(user))
                .map(alarmSetting -> {
                    alarmService.removeAlarm(user);
                    alarmSettingService.removeAlarmSetting(alarmSetting);
                    return getStringObjectMap("시간 설정과 출석 기록이 삭제되었습니다." +
                            "헬스판다는 항상 여러분을 기다리고 있습니다.");
                })
                .orElseGet(() -> getStringObjectMap("운동 시간 설정이 이미 삭제되었거나, 존재하지 않습니다."));
    }

    @PostMapping("/question")
    public Map<String, String> question(@RequestBody Map<String, Object> event) {
        Map<String, String> param = getActionParams(event);
        ChatRequest chatRequest = new ChatRequest(chatgptProperties.getModel(), "7일의 다이어트 식단을 리스트로 작성해줘", 3000, 0.8, 0.8);
        ChatResponse reply = chatgptService.sendChatRequest(chatRequest);
        String result = reply.getChoices().get(0).getText();
        System.out.println(result);
        result = result.replace("\n", "");
        System.out.println("reply = " + reply);
        Map<String, String> map = new HashMap<>();
        map.put("answer", result);
        return map;
    }
}