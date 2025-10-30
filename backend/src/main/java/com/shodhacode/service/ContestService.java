package com.shodhacode.service;

import com.shodhacode.model.*;
import com.shodhacode.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepo;
    private final SubmissionRepository submissionRepo;

    public Optional<Contest> getContest(Long id) {
        return contestRepo.findById(id);
    }

    public List<Submission> getLeaderboard(Long contestId) {
        // simplified: show all submissions ordered by createdAt
        return submissionRepo.findByProblemContestIdOrderByCreatedAtAsc(contestId);
    }
}