package com.yashdev.Tools.config;

import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GoogleAudioConfig {

    @Bean
    public TextToSpeechClient textToSpeechClient() throws IOException{
        return TextToSpeechClient.create();
    }

    @Bean
    public SpeechClient speechClient() throws IOException{
        return SpeechClient.create();
    }
}
