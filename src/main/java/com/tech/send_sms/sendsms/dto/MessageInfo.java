package com.tech.send_sms.sendsms.dto;

import com.google.common.base.MoreObjects;

/**
 * Информацию сообщения
 */
public class MessageInfo {
    private final String phoneNumber;
    private final String text;

    /**
     * Создать информацию сообщения
     * @param phoneNumber номер телефона
     * @param text текст сообщения
     */
    public MessageInfo(String phoneNumber, String text) {
        this.phoneNumber = phoneNumber;
        this.text = text;
    }

    /** Номер телефона */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /** Текст сообщения */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("phoneNumber", phoneNumber)
                .add("text", text)
                .toString();
    }
}
