package com.RepForge.activity_service.Repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.RepForge.activity_service.model.Activity;

@Repository
public interface ActivityRepo extends MongoRepository<Activity, UUID> {

}
