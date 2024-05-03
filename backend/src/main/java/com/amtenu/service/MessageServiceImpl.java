package com.amtenu.service;

import com.amtenu.models.Chat;
import com.amtenu.models.Message;
import com.amtenu.models.User;
import com.amtenu.repository.MessageRepository;
import com.amtenu.repository.ProjectRepository;
import com.amtenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;


    @Override
    public Message sendMessage(Long senderId, Long projectId, String messageContent) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User not found :" + senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();

        message.setChat(chat);
        message.setContent(messageContent);
        message.setCreatedAt(LocalDateTime.now());
        message.setSender(sender);

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        return savedMessage;

    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {

        Chat chat=projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderedByCreatedAtAsc=messageRepository.findByChatIdOrderedByCreatedAtAsc(chat.getId());

        return findByChatIdOrderedByCreatedAtAsc;

    }
}
