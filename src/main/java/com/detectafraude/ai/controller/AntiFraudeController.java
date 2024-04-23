package com.detectafraude.ai.controller;

import com.detectafraude.ai.service.SentimentAnalysisService;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/fraude")
public class AntiFraudeController {

    @Autowired
    private OpenAiChatClient openAiChatClient;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;


    @GetMapping("/verificacao")
    public HashMap<String, String> checkFraud(@RequestParam(value = "message") String message){
        String userMessage = "Essa mensagem é confiável? ".concat(message).concat("Responda em ingles");
        String gptMessage = openAiChatClient.call(userMessage);
        String analyzeSentiment = sentimentAnalysisService.analyzeSentiment(gptMessage);
        boolean isReliable = !Objects.equals(analyzeSentiment, "Negative");
        HashMap<String, String> response = new HashMap<>();
        if (isReliable){
            response.put("reliable", "true");
        }else {
            response.put("reliable", "false");
        }
        return response;
    }


}
