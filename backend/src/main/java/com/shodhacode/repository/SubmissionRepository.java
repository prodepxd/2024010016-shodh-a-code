package com.shodhacode.repository;

import com.shodhacode.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository for Submission entity
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByProblemContestIdOrderByCreatedAtAsc(Long contestId);
}