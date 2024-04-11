package com.detectafraude.ai.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraude")
public class AntiFraudeController {

    @Autowired
    private OpenAiChatClient openAiChatClient;

    @GetMapping("/verificacao")
    public String checkFraud(@RequestParam(value = "message") String message){
        return openAiChatClient.call(message);
    }
}
