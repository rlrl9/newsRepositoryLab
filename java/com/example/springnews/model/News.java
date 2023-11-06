package com.example.springnews.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String writer;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalDateTime writeDate;
    private int cnt;
}
