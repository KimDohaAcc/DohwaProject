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

        String reply = chatGptService.sendMessage(food + " " + gram + "의 칼로리를 '[kcal: ]' 형식으로 알려줘");
        String kcal = extractKcal(reply);

        return createResponseMap(kcal);
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
}