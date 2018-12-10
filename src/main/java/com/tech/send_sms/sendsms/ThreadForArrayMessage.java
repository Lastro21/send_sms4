package com.tech.send_sms.sendsms;

public class ThreadForArrayMessage implements Runnable {

    private final int countForFixThreads;

    public ThreadForArrayMessage(int countForFixThreads) {
        this.countForFixThreads = countForFixThreads;
    }

    @Override
    public void run() {
    }

    public int getCountForFixThreads() {
        return countForFixThreads;
    }
}
