package main.java.problems;
import java.util.*;

public class RealTimeAnalytics {

    // page -> total visits
    private HashMap<String, Integer> pageViews;

    // page -> unique users
    private HashMap<String, Set<String>> uniqueVisitors;

    // traffic source -> count
    private HashMap<String, Integer> trafficSources;

    public RealTimeAnalytics() {
        pageViews = new HashMap<>();
        uniqueVisitors = new HashMap<>();
        trafficSources = new HashMap<>();
    }

    // Process incoming page view event
    public void processEvent(String url, String userId, String source) {

        // update page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // update unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // update traffic source
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // Get top N pages
    public void getTopPages(int n) {

        List<Map.Entry<String, Integer>> pages =
                new ArrayList<>(pageViews.entrySet());

        pages.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Top Pages:");

        for (int i = 0; i < Math.min(n, pages.size()); i++) {

            String url = pages.get(i).getKey();
            int views = pages.get(i).getValue();
            int unique = uniqueVisitors.get(url).size();

            System.out.println(
                    (i + 1) + ". " + url +
                            " - " + views +
                            " views (" + unique + " unique)"
            );
        }
    }

    // Show traffic sources
    public void getTrafficSources() {

        System.out.println("\nTraffic Sources:");

        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}