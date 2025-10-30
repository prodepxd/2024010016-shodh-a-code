package com.shodhacode.controller;

import com.shodhacode.service.SubmissionService;
import com.shodhacode.model.Submission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public Submission submit(@RequestBody Map<String, Object> payload) {
        String username = (String) payload.get("username");
        Long problemId = Long.parseLong(payload.get("problemId").toString());
        String code = (String) payload.get("code");
        String language = (String) payload.get("language");
        return submissionService.createSubmission(username, problemId, code, language);
    }

    @GetMapping("/{id}")
    public Submission get(@PathVariable Long id) {
        return submissionService.getSubmission(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }
}