package com.RepForge.ai_service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.RepForge.ai_service.model.Recommendation;

@Repository
public interface AiRepo extends MongoRepository<Recommendation, String> {

}
