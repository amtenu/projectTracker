package com.amtenu.service;

import com.amtenu.models.Chat;
import com.amtenu.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;
    @Override
    public Chat createChat(Chat chat) {
    return chatRepository.save(chat);
    }
}
