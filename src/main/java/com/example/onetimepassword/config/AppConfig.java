package com.example.onetimepassword.config;

import dev.samstevens.totp.code.HashingAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pat on 27-Mar-23 - 11:54 AM
 *
 * @author pat
 * @project one-time-password
 */
@Configuration
public class AppConfig {
    @Bean
    public HashingAlgorithm hashingAlgorithm() {
        return HashingAlgorithm.SHA256;
    }
}
