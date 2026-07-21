package com.RepForge.ai_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.RepForge.ai_service.Repository.AiRepo;
import com.RepForge.ai_service.model.Recommendation;

@Service
public class RecommendationService {
    AiRepo aiRepo;

    RecommendationService(AiRepo aiRepo) {
        this.aiRepo = aiRepo;
    }

    public List<Recommendation> getUserRecommendations(UUID userId) {
        return aiRepo.findByUserId(userId);

    }

    public Recommendation getActivityRecommendations(String activityId) {
        return aiRepo.findByActivityId(activityId);

    }

}
