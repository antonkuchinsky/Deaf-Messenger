package com.who.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User contact;

    private String contactName;

    private Boolean isBlocked;

    @Enumerated(EnumType.STRING)
    private ContactCategory contactCategory;
}