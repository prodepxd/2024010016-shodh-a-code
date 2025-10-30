package com.shodhacode.repository;

import com.shodhacode.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Contest entity
public interface ContestRepository extends JpaRepository<Contest, Long> {
}