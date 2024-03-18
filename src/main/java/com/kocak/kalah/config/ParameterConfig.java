package com.kocak.kalah.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterConfig {

    @Value("${initial.pit.token.count}")
    private int initialPitTokenCount;

    @Bean
    public int initialPitTokenCountBean() {
        return initialPitTokenCount;
    }
}
