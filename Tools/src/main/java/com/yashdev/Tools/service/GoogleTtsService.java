package com.yashdev.Tools.service;

import com.google.cloud.texttospeech.v1.*;
import org.springframework.stereotype.Service;

@Service
public class GoogleTtsService {

    private final TextToSpeechClient ttsClient;
    //we will pickup ttsClient Bean from GoogleAudioConfig
    public GoogleTtsService(TextToSpeechClient ttsClient) {
        this.ttsClient = ttsClient;
    }

    public byte[] synthesizeText(String text) {
        SynthesisInput input = SynthesisInput.newBuilder()
                .setText(text)
                .build();

        // Select a premium Neural2 voice
        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode("en-US")
                .setName("en-US-Neural2-F")
                .build();

        AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.MP3)
                .build();

        SynthesizeSpeechResponse response = ttsClient.synthesizeSpeech(input, voice, audioConfig);
        return response.getAudioContent().toByteArray();
    }
}