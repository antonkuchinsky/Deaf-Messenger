package com.who.messageservice.repository;

import com.who.messageservice.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {
    @Query("SELECT m FROM Message m WHERE (m.senderId = :userIdOne AND m.recipientId = :userIdTwo)" +
            " OR (m.senderId = :userIdTwo AND m.recipientId = :userIdOne) ORDER BY m.dateTimeSendingMessage DESC")
    List<Message> getExistingChatMessages(@Param("userIdOne") UUID userIdOne, @Param("userIdTwo") UUID userIdTwo, Pageable pageable);
}
