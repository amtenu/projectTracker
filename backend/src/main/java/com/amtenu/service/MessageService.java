package com.amtenu.service;

import com.amtenu.models.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long chatId, String messageContent) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
