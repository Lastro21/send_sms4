package com.tech.send_sms.sendsms.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigArraySms {

    @Bean
    public ShareArraySms shareArraySms(){
        ShareArraySms shareArraySms = new ShareArraySms();
        return shareArraySms;
    }

}
