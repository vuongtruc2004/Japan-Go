package com.nass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareConfig", modifyOnCreate = false)
public class NassApplication {

    public static void main(String[] args) {
        SpringApplication.run(NassApplication.class, args);
    }

}
