package ai.az.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AudioTranscriptionService {

    Logger log = LoggerFactory.getLogger(AudioTranscriptionService.class);
    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    public AudioTranscriptionService(@Value("${spring.ai.openai.api-key}") String apiKey) {
        OpenAiAudioApi openAiAudioApi = new OpenAiAudioApi(apiKey);
        this.openAiAudioTranscriptionModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

    public String transcribeAudio(MultipartFile file) throws IOException {
        log.info("Audio transcription service is running");
        File tempFile = File.createTempFile("audio", ".wav");
        file.transferTo(tempFile);
        var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .withLanguage("en")
                .withTemperature(0f)
                .build();

        var audioFile = new FileSystemResource(tempFile);

        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        AudioTranscriptionResponse response = openAiAudioTranscriptionModel.call(transcriptionRequest);
        log.info("temp file deleted {}", tempFile.delete());
        return response.getResult().getOutput();
    }
}