package com.tech.send_sms.sendsms.controllers;

import com.google.common.collect.ImmutableList;
import com.tech.send_sms.sendsms.convert.StringToMessageInfoConverter;
import com.tech.send_sms.sendsms.dto.ConfigArraySms;
import com.tech.send_sms.sendsms.dto.MessageInfo;
import com.tech.send_sms.sendsms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableScheduling
public class ScheduledSaveSmsArrayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledSaveSmsArrayController.class);

    private ConfigArraySms configArraySms;
    private SmsService smsService;
    private ConversionService conversionService;
    private Converter<String, MessageInfo> stringToMessageInfoConverter;

    @Scheduled(initialDelay = 2000, fixedRate = 10000)
    public void disableOnline() {
        if (configArraySms.shareArraySms().getShareArrayList().size() > 0) {
            List<String> copyShareArrayList = ImmutableList.copyOf(configArraySms.shareArraySms().getShareArrayList());
            configArraySms.shareArraySms().cleanShareArraySms();
            LOGGER.debug("copyShareArrayList: {}", String.join(";", copyShareArrayList));
            for (int i = 0; i < copyShareArrayList.size(); i++) {
                String fullMessage = copyShareArrayList.get(i);
//                final MessageInfo message = conversionService.convert(fullMessage, MessageInfo.class);
                final MessageInfo message = stringToMessageInfoConverter.convert(fullMessage);
                LOGGER.debug("converted message: {}", message);
                try {
                    // WITHOUT THREAD
                    smsService.saveSmsInDb("Transient", message.getText(), message.getPhoneNumber());
                } catch (Exception e) {
                    LOGGER.error("Save error...", e);
                }
            }
        }
    }

    @Autowired
    public void setConfigArraySms(ConfigArraySms configArraySms) {
        this.configArraySms = configArraySms;
    }

    @Autowired
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setStringToMessageInfoConverter(Converter<String, MessageInfo> stringToMessageInfoConverter) {
        this.stringToMessageInfoConverter = stringToMessageInfoConverter;
    }
}
