package com.amtenu.repository;

import com.amtenu.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
List<Message> findByChatIdOrderedByCreatedAtAsc(Long chatId);
}
