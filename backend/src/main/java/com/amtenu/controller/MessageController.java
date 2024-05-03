package com.amtenu.controller;

import com.amtenu.models.Chat;
import com.amtenu.models.Message;
import com.amtenu.models.User;
import com.amtenu.request.CreateMessageRequest;
import com.amtenu.service.ChatService;
import com.amtenu.service.MessageService;
import com.amtenu.service.ProjectService;
import com.amtenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request
    ) throws Exception {
        User user = userService.findUserById(request.getSenderId());

        if (user == null) throw new Exception("User not found with id " + request.getSenderId());

        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if (chat == null) throw new Exception("Chat not found");

        Message sendMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getMessageContent());

        return ResponseEntity.ok(sendMessage);

    }
}
