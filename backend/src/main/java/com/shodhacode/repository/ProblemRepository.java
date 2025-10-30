package com.shodhacode.repository;

import com.shodhacode.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository for Problem entity
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByContestId(Long contestId);
}