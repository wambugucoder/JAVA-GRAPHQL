package com.tutorial.javagraphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Configuration
@Import(JavagraphqlApplication.class)
public class ClockConfig {
    @Bean
    @Primary
    Clock testClock(){
        return Clock.fixed(LocalDate.of(2021,9,15).atStartOfDay(UTC).toInstant(),UTC);
    }
}
