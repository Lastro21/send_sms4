package com.tech.send_sms.sendsms.convert;

import com.tech.send_sms.sendsms.dto.MessageInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Конвертер из строки в {@link MessageInfo}
 */
@Service
public class StringToMessageInfoConverter implements Converter<String, MessageInfo> {
    @Override
    public MessageInfo convert(String s) {
        if (s == null || s.isEmpty()) {
            return new MessageInfo("", "");
        }
        return new MessageInfo(
                s.substring(s.length() - 11, s.length()),
                s.substring(0, s.length() - 12)
                );
    }
}
