package com.japan_go_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareConfig", modifyOnCreate = false)
public class JapanGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapanGoApplication.class, args);
    }

}
