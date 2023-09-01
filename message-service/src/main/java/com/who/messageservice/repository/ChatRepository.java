package com.who.messageservice.repository;

import com.who.messageservice.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends CrudRepository<Chat, UUID> {
    Boolean existsByChatMessagesSenderIn(UUID senderId);
    @Override
    Optional<Chat> findById(UUID uuid);
    Optional<List<Chat>> findChatByCommunity(UUID recipientId);
    void deleteById(UUID id);
}
