package main.java.problems;
import java.util.*;

public class DNSCache {

    private Map<String, DNSEntry> cache;

    private int hits;
    private int misses;

    public DNSCache() {
        cache = new HashMap<>();
    }

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            return "Cache HIT → " + entry.ipAddress;
        }

        misses++;

        // simulate upstream DNS lookup
        String ip = queryUpstreamDNS(domain);

        cache.put(domain, new DNSEntry(domain, ip, 10));

        return "Cache MISS → " + ip;
    }

    private String queryUpstreamDNS(String domain) {

        // simulate IP generation
        return "192.168.1." + new Random().nextInt(255);
    }

    public void cleanExpiredEntries() {

        Iterator<Map.Entry<String, DNSEntry>> iterator = cache.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, DNSEntry> entry = iterator.next();

            if (entry.getValue().isExpired()) {
                iterator.remove();
            }
        }
    }

    public void getStats() {

        int total = hits + misses;

        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }
}