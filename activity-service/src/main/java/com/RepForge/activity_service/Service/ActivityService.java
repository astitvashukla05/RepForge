package com.RepForge.activity_service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RepForge.activity_service.Repository.ActivityRepo;
import com.RepForge.activity_service.model.Activity;
import com.RepForge.activity_service.model.DTOs.ActivityRequest;
import com.RepForge.activity_service.model.DTOs.ActivityResponse;

import jakarta.validation.Valid;

@Service
public class ActivityService {

    private final ActivityRepo activityRepo;

    public ActivityService(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    public ActivityResponse trackActivity(@Valid ActivityRequest request) {

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
        return activityResponse;
    }

    public List<ActivityResponse> getActivity(String userId) {
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
        Optional<Activity> activity = activityRepo.findById(acitivityId);
        Activity act = activity.get();
        ActivityResponse res = new ActivityResponse();
        res.setAdditionalInfo(act.getAdditionalInfo());
        res.setCaloriesBurnt(act.getCaloriesBurnt());
        res.setDuration(act.getDuration());
        res.setStartTime(act.getStartTime());
        res.setType(act.getType());
        return res;
    }
}
