package com.amtenu.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void SendEmailWithToken(String userEmail,String link) throws MessagingException;

}
