package com.who.messageservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="message")
@Builder
public class Message {
    @Id
    private UUID id;
    private UUID chatId;
    private String senderName;
    private String senderId;
    private String textMessage;
    @OneToMany
    private List<Recipient> recipients;
    private ZonedDateTime dateTimeSendingMessage;
    private MessageStatus messageStatus;

}
