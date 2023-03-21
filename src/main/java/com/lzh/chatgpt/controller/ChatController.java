package com.lzh.chatgpt.controller;

import com.alibaba.fastjson.JSON;
import com.lzh.chatgpt.model.completion.CompletionChoice;
import com.lzh.chatgpt.model.completion.CompletionRequest;
import com.lzh.chatgpt.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.controller
 * @Project：chat-gpt
 * @name：ChatController
 * @Date：2023/3/21 10:24
 * @Filename：ChatController
 */
@RestController
public class ChatController {
    /**
     * 填入自己的api key
     */
    OpenAiService openAiService = new OpenAiService("sk-********************************************************");

    @PostMapping("/api/chat")
    public String sendMessage(@RequestBody String message) {

        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .maxTokens(2048)
                .echo(true)
                .n(1)
                .topP(1.0)
                .temperature(0.5)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .prompt(message)
                .build();
        List<CompletionChoice> choices = openAiService.createCompletion(completionRequest).getChoices();
        if (!CollectionUtils.isEmpty(choices)) {
            String text = choices.get(0).getText();
            System.out.println(text);
            return JSON.toJSONString(replaceBlank(text));
        }
        // 将消息发送到OpenAI API 或其他聊天机器人后端
        // 并返回响应
        return JSON.toJSONString("chatgpt未给出任何回答！");
    }

        public static String replaceBlank(String str) {
            String dest = "";
            if (str != null) {
                int i = str.indexOf("}");
                dest = str.substring(i + 1, str.length());
//                Pattern p = Pattern.compile("\\s*|\\t|\\r|\\n");
//                Matcher m = p.matcher(substring);
//                dest = m.replaceAll("");
            }
            return dest;
        }

//    @GetMapping("/api/chat")
//    public String sendMessage() {
//        // 将消息发送到OpenAI API 或其他聊天机器人后端
//        // 并返回响应
//        return "Hello";
//    }
    }
