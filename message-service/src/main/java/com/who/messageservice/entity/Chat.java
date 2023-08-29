package com.who.messageservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nameChat;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Message> chatMessages;

    @OneToMany
    private List<Recipient> community;



}
