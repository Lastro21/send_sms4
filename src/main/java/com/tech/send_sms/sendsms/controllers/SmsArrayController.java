package com.tech.send_sms.sendsms.controllers;

import com.tech.send_sms.sendsms.ThreadForArrayMessage;
import com.tech.send_sms.sendsms.dto.ConfigArraySms;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@EnableScheduling
final public class SmsArrayController {

    @Autowired
    private ConfigArraySms configArraySms;

    private final ExecutorService executor = Executors.newFixedThreadPool(12);

    @RequestMapping(value = "controllerArraySendSms", method = RequestMethod.GET)
    public void controllerSendSms(@RequestParam String arrayPairTextNumber) {

        if (arrayPairTextNumber.length() > 0) {

            JSONObject jsonObject = new JSONObject();

            List array;

            array = Arrays.asList(arrayPairTextNumber.split(","));

            for (int i = 0; i < array.size(); i++) {
                int numberElement = i;
                executor.execute(new ThreadForArrayMessage(numberElement) {
                    @Override
                    public void run() {
                        String fullMessage = (String) array.get(getCountForFixThreads());
                        configArraySms.shareArraySms().addElement(fullMessage);
                    }
                });
            }
        }
    }
}
