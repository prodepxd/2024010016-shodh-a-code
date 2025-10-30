package com.shodhacode.service;

import com.shodhacode.model.*;
import com.shodhacode.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepo;
    private final ProblemRepository problemRepo;
    private final UserRepository userRepo;
    private final JudgeService judge;

    public Submission createSubmission(String username, Long problemId, String code, String language) {
        User user = userRepo.findByUsername(username)
                .orElseGet(() -> userRepo.save(User.builder().username(username).build()));

        Problem p = problemRepo.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        Submission sub = Submission.builder()
                .user(user)
                .problem(p)
                .code(code)
                .language(language)
                .status("Pending")
                .result("In queue")
                .createdAt(LocalDateTime.now())
                .build();

        Submission saved = submissionRepo.save(sub);
        // call judge asynchronously (simple thread for prototype)
        new Thread(() -> judge.evaluate(saved)).start();
        return saved;
    }

    public Optional<Submission> getSubmission(Long id) {
        return submissionRepo.findById(id);
    }
}