package ai.az.example.controller;

import ai.az.example.service.AudioTranscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/gen-ai-audio")
@RestController
public class AudioTranscriptionController {
    Logger log = LoggerFactory.getLogger(AudioTranscriptionController.class);
    private final AudioTranscriptionService audioTranscriptionService;

    public AudioTranscriptionController(AudioTranscriptionService audioTranscriptionService) {
        this.audioTranscriptionService = audioTranscriptionService;
    }

    @PostMapping("/transcription")
    public ResponseEntity<String> getAITranscription(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Audio Transcription controller is running");
        return ResponseEntity.ok(audioTranscriptionService.transcribeAudio(file));
    }
}