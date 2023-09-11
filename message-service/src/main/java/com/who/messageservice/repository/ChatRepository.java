package com.who.messageservice.repository;

import com.who.messageservice.entity.Chat;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends CrudRepository<Chat, UUID> {
    @Query("SELECT c FROM Chat c JOIN c.community r WHERE r.recipientId = :recipientId")
    List<Chat> findChatsByRecipientId(@Param("recipientId") UUID recipientId);
    void deleteById(UUID id);
}
