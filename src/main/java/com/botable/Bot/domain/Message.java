package com.botable.Bot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    public Message(String content) {
        this.content = content;
    }
}
