package com.shodhacode.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String language;
    private String status;
    private String result;        // Accepted / Wrong Answer / Error
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;
}