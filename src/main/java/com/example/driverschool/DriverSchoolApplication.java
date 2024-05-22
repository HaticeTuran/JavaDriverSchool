package com.example.driverschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.driverschool.security.jwt")
public class DriverSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverSchoolApplication.class, args);
    }
}
