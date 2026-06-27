package com.yashdev.Tools.service;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

@Service
public class GoogleSttService {

    private final SpeechClient speechClient;

    public GoogleSttService(SpeechClient speechClient) {
        this.speechClient = speechClient;
    }

    public String transcribeAudio(byte[] audioBytes) {
        // LINEAR16 (WAV) format config. Change based on your frontend's recording output.
        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setSampleRateHertz(16000)
                .setLanguageCode("en-US")
                .build();

        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(ByteString.copyFrom(audioBytes))
                .build();

        RecognizeResponse response = speechClient.recognize(config, audio);

        StringBuilder transcription = new StringBuilder();
        for (SpeechRecognitionResult result : response.getResultsList()) {
            if (!result.getAlternativesList().isEmpty()) {
                transcription.append(result.getAlternatives(0).getTranscript());
            }
        }
        return transcription.toString();
    }
}