package com.RepForge.ai_service.service;

import org.springframework.stereotype.Service;

import com.RepForge.ai_service.model.Activity;

@Service

public class ActivityAiService {
    private final GeminiService geminiService;

    ActivityAiService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String generateRecommendation(Activity activity) {
        String prompt = createPromptForActivity(activity);
        String response = geminiService.getAnswers(prompt);
        return response;
    }

    private String createPromptForActivity(Activity activity) {
        return String.format("""
                You are an expert fitness coach and sports physiologist.

                Analyze the following fitness activity and provide personalized recommendations.

                Activity Details:
                - Activity Type: %s
                - Duration: %d minutes
                - Calories Burned: %d
                - Start Time: %s
                - Additional Information: %s

                Rules:
                1. Respond ONLY with valid JSON.
                2. Do NOT include markdown.
                3. Do NOT wrap the response in ```json.
                4. Do NOT add explanations outside the JSON.
                5. Keep recommendations practical and personalized.
                6. If some information is unavailable, infer reasonable advice.

                Return the response in exactly this format:

                {
                  "analysis": {
                    "overall": "Overall performance summary",
                    "pace": "Pace analysis",
                    "heartRate": "Heart rate analysis",
                    "caloriesBurned": "Calories burned analysis"
                  },
                  "improvements": [
                    {
                      "area": "Endurance",
                      "recommendation": "Specific recommendation"
                    },
                    {
                      "area": "Speed",
                      "recommendation": "Specific recommendation"
                    },
                    {
                      "area": "Recovery",
                      "recommendation": "Specific recommendation"
                    }
                  ],
                  "nutrition": {
                    "beforeWorkout": "Recommendation",
                    "afterWorkout": "Recommendation",
                    "hydration": "Recommendation"
                  },
                  "nextWorkout": {
                    "type": "Suggested activity",
                    "duration": "Recommended duration",
                    "intensity": "Low | Moderate | High"
                  },
                  "motivation": "A short motivating message."
                }
                """,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurnt(),
                activity.getStartTime(),
                activity.getAdditionalInfo());
    }

}
