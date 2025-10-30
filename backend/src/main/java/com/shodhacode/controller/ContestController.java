package com.shodhacode.controller;

import com.shodhacode.model.*;
import com.shodhacode.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @GetMapping("/{id}")
    public Contest getContest(@PathVariable Long id) {
        return contestService.getContest(id)
                .orElseThrow(() -> new RuntimeException("Contest not found"));
    }

    @GetMapping("/{id}/leaderboard")
    public List<Submission> leaderboard(@PathVariable Long id) {
        return contestService.getLeaderboard(id);
    }
}