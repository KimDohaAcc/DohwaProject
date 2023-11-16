package com.ssafy.ssafit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.AlarmSetting;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.alarmService.AlarmService;
import com.ssafy.ssafit.service.alarmSettingService.AlarmSettingService;
import com.ssafy.ssafit.service.mealService.MealService;
import com.ssafy.ssafit.service.userService.UserService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class KakaoSkillController {
    private final ChatgptService chatgptService;
    private final MealService mealService;
    private final UserService userService;
    private final AlarmService alarmService;
    private final AlarmSettingService alarmSettingService;

    @PostMapping("/skill")
    public Map<String, String> checkFoodkcal(@RequestBody Map<String, Object> event) {
        Map<String, String> params = getActionParams(event);
        String food = params.get("food");
        String gram = params.get("gram");

        Optional<User> opt = userService.findUserById(Long.parseLong(getUserId(event)));


        String reply = chatgptService.sendMessage(food + " " + gram + "의 칼로리를 '[kcal: ]' 형식으로 알려줘");
        String kcal = extractKcal(reply);

        if (params.get("delete") != null && opt.isPresent()) {
            mealService.removeMeal(opt.get());
        }

        opt.map(user -> mealService.createMeal(new Meal(null, user, food, kcal, LocalDateTime.now())));
        return createResponseMap(kcal);
    }

    private String getUserId(Map<String, Object> event) {
        Map<String, Object> json = (Map<String, Object>) event.get("body-json");
        System.out.println("json = " + json);
        Map<String, Object> userRequest = (Map<String, Object>) json.get("userRequest");
        System.out.println("userRequest = " + userRequest);
        Map<String, Object> user = (Map<String, Object>) userRequest.get("user");
        System.out.println("user = " + user);
        return (String) user.get("id");
    }

    private Map<String, String> getActionParams(Map<String, Object> event) {
        Map<String, Object> json = (Map<String, Object>) event.get("body-json");
        Map<String, Object> action = (Map<String, Object>) json.get("action");
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
        Optional<User> opt = userService.findUserById(Long.parseLong(getUserId(event)));

        Optional<List<Meal>> list = opt.map(mealService::getMealByUser);
        LocalDateTime now = LocalDateTime.now();
        StringBuilder result = new StringBuilder();

        if (list.isPresent()) {
            LocalDateTime before = switch (period) {
                case "이번 주" -> now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                case "이번 달" -> now.with(TemporalAdjusters.firstDayOfMonth());
                case "최근 세 달" -> now.minusMonths(2).with(TemporalAdjusters.firstDayOfMonth());
                case "최근 여섯 달" -> now.minusMonths(5).with(TemporalAdjusters.firstDayOfMonth());
                case "최근 일 년" -> now.minusYears(1);
                default -> now.minusYears(3);
            };

            for (Meal meal : list.get()) {
                if (meal.getTime().isAfter(before)) {
                    result.append("------");
                    result.append(meal.getTime().getYear()).append("/").append(meal.getTime().getMonth()).append("/").append(meal.getTime().getDayOfMonth()).append(" ").append(meal.getTime().getHour() >= 17 ? "저녁" : meal.getTime().getHour() >= 12 ? "점심" : "아침" + "\n");
                    result.append(meal.getFood()).append("(").append(meal.getKcal().trim()).append("kcal)\n");
                    result.append("------");
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("result", result.toString());
        return map;
    }

    @PostMapping("/dateTime")
    public ResponseEntity<?> handleChatbotDateRequest(@RequestBody Map<String, Object> request) {
        Map<String, Object> action = (Map<String, Object>) request.get("action");
        if (action == null) {
            return ResponseEntity.status(400).body("Action is missing or null");
        }

        Map<String, String> params = (Map<String, String>) action.get("params");
        String datetime = params.get("datetime");
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> datetimeInfo = mapper.readValue(datetime, Map.class);
            String value = datetimeInfo.get("value");
            String userTimeZone = datetimeInfo.get("userTimeZone");

            LocalDateTime localTime = LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
            params.put("localTime", localTime.toString());
            AlarmSetting alarmSetting = new AlarmSetting();
            alarmSetting.setDate(localTime);
            Optional<User> user = userService.findUserById(Long.parseLong(getUserId(request)));
            if (user.isPresent()) {
                alarmSetting.setUser(user.get());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

        return new ResponseEntity<Map<String, String>>(params, HttpStatus.OK);
    }
    @PostMapping("/nowTime")
    public Map<String, Object> nowTime(@RequestBody Map<String, Object> request) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> res = new HashMap<>();

        Optional<User> user = userService.findUserById(Long.parseLong(getUserId(request)));
        if (user.isPresent()) {
            AlarmSetting alarmSetting = alarmSettingService.findAlarmSettingByUser(user.get());
            LocalDateTime alarmSettingTime = alarmSetting.getDate();

            boolean isBefore = now.toLocalTime().isBefore(alarmSettingTime.toLocalTime());
            res.put("isBefore", isBefore);


            if (isBefore) {
                res.put("msg", "아주 잘했구나~");
            } else {
                res.put("msg", "다음부터는 늦게 오지 말라구~");
            }
        }

        return res;
    }
    @GetMapping("/listTime")
    public List<String> getDateList(@RequestBody Map<String, Object> request) {
        List<Alarm> alarms = new ArrayList<>();
        List<String> formattedDates = new ArrayList<>();

        Optional<User> user = userService.findUserById(Long.parseLong(getUserId(request)));
        if (user.isPresent()) {
            alarms = (List<Alarm>) alarmService.findAlarmsByUser(user.get());

            for (Alarm alarm : alarms) {
                LocalDateTime date = alarm.getDate();
                String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
                formattedDates.add(formattedDate);
            }
        }

        return formattedDates;
    }

    @DeleteMapping("/deleteTime")
    public ResponseEntity<Void> delete(@RequestBody Map<String, Object> request) {
        long userId = Long.parseLong(getUserId(request));

        Optional<User> user = userService.findUserById(Long.parseLong(getUserId(request)));
        if (user.isPresent()) {
            List<Alarm> userAlarms = alarmService.findAlarmsByUser(user.get());
            AlarmSetting alarmSetting = alarmSettingService.findAlarmSettingByUser(user.get());

            alarmService.removeAlarm(userAlarms);
            alarmSettingService.removeAlarmSetting(alarmSetting);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateSettingTime")
    public ResponseEntity<?> handleChatbotUpdateDateRequest(@RequestBody Map<String, Object> request) {
        Map<String, Object> action = (Map<String, Object>) request.get("action");
        if (action == null) {
            return ResponseEntity.status(400).body("Action is missing or null");
        }

        Map<String, String> params = (Map<String, String>) action.get("params");
        String datetime = params.get("datetime");
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> datetimeInfo = mapper.readValue(datetime, Map.class);
            String value = datetimeInfo.get("value");
            String userTimeZone = datetimeInfo.get("userTimeZone");

            LocalDateTime localTime = LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
            params.put("localTime", localTime.toString());

            Optional<User> user = userService.findUserById(Long.parseLong(getUserId(request)));
            if (user.isPresent()) {

                AlarmSetting existingSetting = alarmSettingService.findAlarmSettingByUser(user.get());
                if (existingSetting != null) {
                    alarmSettingService.removeAlarmSetting(existingSetting);
                }


                AlarmSetting alarmSetting = new AlarmSetting();
                alarmSetting.setDate(localTime);
                alarmSetting.setUser(user.get());
                alarmSettingService.updateAlarmSetting(alarmSetting);
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

        return new ResponseEntity<Map<String, String>>(params, HttpStatus.OK);
    }



}
