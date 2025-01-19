package ai.az.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt) {
        log.info("Chat service is running");
        return chatModel.call(prompt);
    }

    public String getResponseWithOptions(String prompt) {
        log.info("Chat service is running with options");
        ChatResponse chatResponse = chatModel.call(
                new Prompt(prompt, OpenAiChatOptions.builder()
                        .withModel("gpt-4o")
                        .withTemperature(0.4)
                        .build())
        );
        return chatResponse.getResult().getOutput().getContent();
    }
}