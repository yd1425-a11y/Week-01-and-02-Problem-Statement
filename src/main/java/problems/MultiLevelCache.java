package main.java.problems;

import java.util.*;

public class MultiLevelCache {

    private LinkedHashMap<String, Video> L1;
    private HashMap<String, Video> L2;
    private HashMap<String, Video> database;

    private int L1_CAPACITY = 3;

    public MultiLevelCache() {

        L1 = new LinkedHashMap<>(L1_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > L1_CAPACITY;
            }
        };

        L2 = new HashMap<>();
        database = new HashMap<>();
    }

    public void addToDatabase(String id, String data) {
        database.put(id, new Video(id, data));
    }

    public Video getVideo(String id) {

        if (L1.containsKey(id)) {
            System.out.println("L1 Cache HIT");
            return L1.get(id);
        }

        if (L2.containsKey(id)) {
            System.out.println("L2 Cache HIT");

            Video v = L2.get(id);
            L1.put(id, v);

            return v;
        }

        if (database.containsKey(id)) {

            System.out.println("Database HIT");

            Video v = database.get(id);

            L2.put(id, v);
            L1.put(id, v);

            return v;
        }

        return null;
    }

}