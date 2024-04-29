package com.amtenu.service;

import com.amtenu.models.Invitation;
import com.amtenu.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;
    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {
       String invitationToken= UUID.randomUUID().toString();
       Invitation invitation=new Invitation();

       invitation.setToken(invitationToken);
       invitation.setEmail(email);
       invitation.setProjectId(projectId);

       invitationRepository.save(invitation);

       String invitationLink="http://localhost:3838/accept_invitation?token=" +invitationToken;
       emailService.SendEmailWithToken(email,invitationLink);

    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) {
        return null;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        return null;
    }

    @Override
    public void deleteToken(String token) {

    }
}
