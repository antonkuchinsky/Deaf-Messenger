package com.who.messageservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="message")
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID chatId;
    private UUID senderId;
    private UUID recipientId;
    private String textMessage;
    private ZonedDateTime dateTimeSendingMessage;
    private MessageStatus messageStatus;
}
