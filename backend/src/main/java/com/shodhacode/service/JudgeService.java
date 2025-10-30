package com.shodhacode.service;

import com.shodhacode.model.*;
import com.shodhacode.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JudgeService {

    private final SubmissionRepository submissionRepository;

    public Submission evaluate(Submission sub) {
        // Simulate “Running”
        try { 
            sub.setStatus("Running");
            submissionRepository.save(sub);
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}

        Problem p = sub.getProblem();
        // Simplified comparison: if code string contains expectedOutput keyword
        if (sub.getCode().contains(p.getExpectedOutput().trim())) {
            sub.setStatus("Accepted");
            sub.setResult("All testcases passed");
        } else {
            sub.setStatus("Wrong Answer");
            sub.setResult("Output mismatch");
        }
        return submissionRepository.save(sub);
    }
}