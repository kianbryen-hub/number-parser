package com.kb.numberparser.config;

import com.kb.numberparser.NumberParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class WebConfiguration {

    @Bean
    public NumberParser numberParser() {
        return new NumberParser(
                Map.of("UK", 44, "FR", 33, "US", 1),
                Map.of("UK", "0", "FR", "0", "US", "1")
        );
    }

}
