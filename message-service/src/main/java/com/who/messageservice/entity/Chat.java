package com.who.messageservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
@Builder
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
