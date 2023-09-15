package com.who.messageservice.repository;

import com.who.messageservice.entity.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends CrudRepository<Chat, UUID> {
    @Query("SELECT c FROM Chat c WHERE (c.userIdOne = :userOneId AND c.userIdTwo = :userTwoId) OR (c.userIdOne = :userTwoId AND c.userIdTwo = :userOneId)")
    List<Chat> findExistingChannel(@Param("userOneId") UUID userOneId, @Param("userTwoId") UUID userTwoId);
}
