package com.ssafy.ssafit.skill;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.github.flashvayne.chatgpt.property.ChatgptProperties;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import io.github.flashvayne.chatgpt.service.impl.DefaultChatgptService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class Handler implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private static final ChatgptProperties chatgptProperties;
    private static final ChatgptService chatGptService;
    private static final Util util;

    static {
        util = new Util();
        chatgptProperties = new ChatgptProperties();
        chatgptProperties.setApiKey(util.getApikey());
        chatGptService = new DefaultChatgptService(chatgptProperties);
    }

    //  ./gradlew buildZip

    @Override
    public Map<String, String> handleRequest(@RequestBody Map<String, Object> event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT: " + event);

        Map<String, String> params = getActionParams(event);
        String food = params.get("food");
        String gram = params.get("gram");
        Long userId = Long.parseLong(getUserId(event));

        String reply = chatGptService.sendMessage(food + " " + gram + "의 칼로리를 '[kcal: ]' 형식으로 알려줘");
        String kcal = extractKcal(reply);

        if (params.get("delete") != null) {
            try {
                deleteMeal(userId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            LocalDateTime now = LocalDateTime.now();
            String time = "아침";

            if (now.getHour() >= 17) {
                time = "저녁";
            } else if (now.getHour() >= 12) {
                time = "점심";
            }

            createMeal(userId, kcal, time);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return createResponseMap(kcal);
    }

    private String getUserId(Map<String, Object> event) {
        Map<String, Object> json = (Map<String, Object>) event.get("body-json");
        Map<String, Object> userRequest = (Map<String, Object>) json.get("userRequest");
        Map<String, Object> user = (Map<String, Object>) userRequest.get("user");
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

    private void createMeal(Long userId, String kcal, String time) throws IOException {

        // URL 설정
        URL url = new URL("http://healthpanda.site/meal");

        // HttpURLConnection 객체 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // POST 요청 설정
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // JSON 데이터 전송
        try (OutputStream os = conn.getOutputStream()) {
            String inputString = userId + "/" + kcal + "/" + time;
            byte[] input = inputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        conn.disconnect();
    }

    private void deleteMeal(Long userId) throws IOException {
        // URL 설정
        URL url = new URL("http://healthpanda.site/meal/delete");

        // HttpURLConnection 객체 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // POST 요청 설정
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // JSON 데이터 전송
        try (OutputStream os = conn.getOutputStream()) {
            String inputString = userId + "";
            byte[] input = inputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        conn.disconnect();
    }
}
