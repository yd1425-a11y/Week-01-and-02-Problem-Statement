package main.java.problems;

import java.util.*;

public class UsernameAvailabilityChecker {

    // username -> userId
    private HashMap<String, Integer> usernameMap;

    // username -> attempt count
    private HashMap<String, Integer> attemptFrequency;

    public UsernameAvailabilityChecker() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    // Check username availability
    public boolean checkAvailability(String username) {

        attemptFrequency.put(
                username,
                attemptFrequency.getOrDefault(username, 0) + 1
        );

        return !usernameMap.containsKey(username);
    }

    // Register username
    public boolean registerUsername(String username, int userId) {

        if (usernameMap.containsKey(username)) {
            return false;
        }

        usernameMap.put(username, userId);
        return true;
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            String suggestion = username + i;

            if (!usernameMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // Most attempted username
    public String getMostAttempted() {

        String result = "";
        int max = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }

        return result + " (" + max + " attempts)";
    }

}