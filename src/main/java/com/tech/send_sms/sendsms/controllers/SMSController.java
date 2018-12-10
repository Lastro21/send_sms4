package com.tech.send_sms.sendsms.controllers;

import com.tech.send_sms.sendsms.service.SmsService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
final public class SMSController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    private SmsService smsService;
    private final ExecutorService executor = Executors.newFixedThreadPool(12);

    @RequestMapping(value = "sendSmsController", method = RequestMethod.GET)
    final public String sendSmsControtller(@RequestParam final String number, final String message) {

        JSONObject jsonObject = new JSONObject();
        if (message.length() > 0 && number.length() > 0) {
            Future<Boolean> saveResult = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        smsService.saveSmsInDb("transient", message, number);
                        return true;
                    } catch (Exception e) {
                        LOGGER.error("save error");
                    }
                    return false;
                }
            });

            try {
                if (saveResult.get()) {
                    jsonObject.put("status", "SMS send success");
                } else {
                    jsonObject.put("status", "not input parameters");
                }
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return jsonObject.toString();
        }
        jsonObject.put("status", "not input parameters");
        return jsonObject.toString();
    }
}
