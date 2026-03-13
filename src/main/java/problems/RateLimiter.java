package main.java.problems;


import java.util.HashMap;

public class RateLimiter {

    private HashMap<String, TokenBucket> clients;

    private int maxRequests = 1000;

    public RateLimiter() {
        clients = new HashMap<>();
    }

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(
                clientId,
                new TokenBucket(maxRequests, maxRequests)
        );

        TokenBucket bucket = clients.get(clientId);

        return bucket.allowRequest();
    }
}