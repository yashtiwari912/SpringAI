package com.yashdev.Tools.controllers;

import com.yashdev.Tools.service.GoogleSttService;
import com.yashdev.Tools.service.GoogleTtsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/audio")
public class AudioController {


    private final GoogleTtsService ttsService;
    private final GoogleSttService sttService;

    public AudioController(GoogleTtsService ttsService, GoogleSttService sttService) {
        this.ttsService = ttsService;
        this.sttService = sttService;
    }

    @PostMapping("/tts")
    public ResponseEntity<byte[]> textToSpeech(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        if (text == null || text.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        byte[] audioData = ttsService.synthesizeText(text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mp3"));
        headers.setContentDispositionFormData("attachment", "speech.mp3");

        return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/stt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> speechToText(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Empty file provided"));
        }

        try {
            byte[] audioBytes = file.getBytes();
            String transcription = sttService.transcribeAudio(audioBytes);

            return ResponseEntity.ok(Map.of("transcription", transcription));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to process audio file: " + e.getMessage()));
        }
    }

}
