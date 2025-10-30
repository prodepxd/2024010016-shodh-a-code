package com.shodhacode.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Problem> problems = new ArrayList<>();
}