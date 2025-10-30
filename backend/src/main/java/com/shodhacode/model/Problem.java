package com.shodhacode.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String inputExample;

    @Column(length = 2000)
    private String outputExample;

    private String testInput;
    private String expectedOutput;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;
}