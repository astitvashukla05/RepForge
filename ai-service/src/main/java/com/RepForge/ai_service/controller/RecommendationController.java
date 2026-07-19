package com.RepForge.ai_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepForge.ai_service.model.ApiResponse;
import com.RepForge.ai_service.model.Recommendation;
import com.RepForge.ai_service.service.RecommendationService;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {
    private final RecommendationService recommendationService;

    RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Recommendation>>> getUserRecommendations(@PathVariable UUID userId) {

    }
}
