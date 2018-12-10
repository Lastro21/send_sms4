package com.tech.send_sms.sendsms.repository;

import com.tech.send_sms.sendsms.models.SmsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsModel, Long> {
}
