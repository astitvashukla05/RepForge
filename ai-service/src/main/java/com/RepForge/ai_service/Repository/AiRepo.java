package com.RepForge.ai_service.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.RepForge.ai_service.model.Recommendation;

@Repository
public interface AiRepo extends MongoRepository<Recommendation, String> {
    List<Recommendation> findByUserId(UUID userId);

    Recommendation findByActivityId(String activityId);
}
