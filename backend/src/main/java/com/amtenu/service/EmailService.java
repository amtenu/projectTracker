package com.amtenu.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void SendEmailWithToken(String userEmail, String link) throws MessagingException;

}
