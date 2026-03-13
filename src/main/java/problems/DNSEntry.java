package main.java.problems;

public class DNSEntry {

    String domain;
    String ipAddress;
    long expiryTime;

    public DNSEntry(String domain, String ipAddress, long ttlSeconds) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }

}