package com.RepForge.activity_service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.RepForge.activity_service.Repository.ActivityRepo;
import com.RepForge.activity_service.client.UserClient;
import com.RepForge.activity_service.exceptions.ActivityNotExists;
import com.RepForge.activity_service.exceptions.UserNotFoundException;
import com.RepForge.activity_service.model.Activity;

import com.RepForge.activity_service.model.DTOs.ActivityRequest;
import com.RepForge.activity_service.model.DTOs.ActivityResponse;

import feign.FeignException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final UserClient apiClient;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public ActivityService(ActivityRepo activityRepo, UserClient apiClient, RabbitTemplate rabbitTemplate) {
        this.activityRepo = activityRepo;
        this.apiClient = apiClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ActivityResponse trackActivity(@Valid ActivityRequest request) {
        // Request for user then validate if exsists or not

        try {
            apiClient.getUserById(request.getUserId());
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("User does not exist");

        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurnt(request.getCalorieBurnt())
                .startTime(request.getStarTime())
                .additionalInfo(request.getAddtionalInfo())
                .build();

        Activity savedActivity = activityRepo.save(activity);

        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setAdditionalInfo(savedActivity.getAdditionalInfo());
        activityResponse.setCaloriesBurnt(savedActivity.getCaloriesBurnt());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setStartTime(savedActivity.getStartTime());
        activityResponse.setType(savedActivity.getType());
        // Pubblish to RabbitMq for ai processing
        try {
            log.info("Publishing message: {}", activityResponse);

            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);

            log.info("Message published successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish to Rabbit MQ");
        }
        return activityResponse;
    }

    public List<ActivityResponse> getActivity(String userId) {

        // Request for user then validate if exsists or not
        List<Activity> userActivities = activityRepo.findByUserId(userId);
        List<ActivityResponse> activities = new ArrayList<>();
        for (Activity activity : userActivities) {
            ActivityResponse res = new ActivityResponse();
            res.setAdditionalInfo(activity.getAdditionalInfo());
            res.setCaloriesBurnt(activity.getCaloriesBurnt());
            res.setDuration(activity.getDuration());
            res.setStartTime(activity.getStartTime());
            res.setType(activity.getType());
            activities.add(res);
        }

        return activities;
    }

    public ActivityResponse getActivityById(String acitivityId) {

        Activity activity = activityRepo.findById(acitivityId)
                .orElseThrow(() -> new ActivityNotExists("activity Not Exists for id :" + acitivityId));

        ActivityResponse res = new ActivityResponse();
        res.setAdditionalInfo(activity.getAdditionalInfo());
        res.setCaloriesBurnt(activity.getCaloriesBurnt());
        res.setDuration(activity.getDuration());
        res.setStartTime(activity.getStartTime());
        res.setType(activity.getType());
        return res;
    }
}
