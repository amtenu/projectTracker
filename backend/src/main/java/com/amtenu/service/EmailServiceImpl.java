package com.amtenu.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.sound.midi.MidiMessage;

public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void SendEmailWithToken(String userEmail, String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join this project team invitation";
        String text = "Click the link to join the project team : " + link;

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
        } catch (MailSendException e) {
            throw new MailSendException("Failed to send Email");
        }
    }
}
