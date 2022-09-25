package com.example.eaSpringDemo.feedback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class FeedbackConfig {
    @Bean
    CommandLineRunner commandLineRunner(FeedbackRepository feedbackRepository){
        return args -> {
            Feedback f1 = new Feedback(
                    "Test one"
                    ,1L
                    ,1L
                    , LocalDate.now()
            );
            Feedback f2 = new Feedback(
                    "Test two"
                    ,2L
                    ,2L
                    , LocalDate.now()
            );
        feedbackRepository.saveAll(
                List.of(f1,f2)
        );
        };
    }
}
