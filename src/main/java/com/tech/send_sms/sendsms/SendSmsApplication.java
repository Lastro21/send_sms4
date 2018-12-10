package com.tech.send_sms.sendsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SendSmsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SendSmsApplication.class, args);
        System.out.println("Start SMS microservice");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SendSmsApplication.class);
    }
}
