package com.tech.send_sms.sendsms.service;

import com.tech.send_sms.sendsms.models.SmsModel;
import com.tech.send_sms.sendsms.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class SmsService {

    @Autowired
    SmsRepository smsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public  void saveSmsInDb(String anumber, String text, String bnumber){
        smsRepository.save(new SmsModel(anumber, text, bnumber));
//        entityManager.flush();
    }
}
