package com.who.messageservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID senderId;

    @OneToMany
    private List<Recipient> recipients;

    private String textMessage;

    private ZonedDateTime dateTimeSendingMessage;

}
