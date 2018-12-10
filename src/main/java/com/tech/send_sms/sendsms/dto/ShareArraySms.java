package com.tech.send_sms.sendsms.dto;

import java.util.ArrayList;
import java.util.List;

public class ShareArraySms {

    private List<String> shareArrayList;

    public ShareArraySms() {
        this.shareArrayList = new ArrayList<>();
    }

    public List<String> getShareArrayList() {
        return shareArrayList;
    }
    public String getShareArrayListElement(int i) {
        return shareArrayList.get(i);
    }

    public synchronized void addElement(String message) {
        shareArrayList.add(message);
    }

    public synchronized void cleanShareArraySms(){
        System.out.println("clean");
        shareArrayList.clear();
    }
}