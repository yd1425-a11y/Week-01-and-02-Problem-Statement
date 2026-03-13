package main.java.problems;

import java.util.*;

public class FlashSaleInventoryManager {

    private Map<String, Integer> stock;
    private Map<String, Queue<Integer>> waitingList;

    public FlashSaleInventoryManager() {
        stock = new HashMap<>();
        waitingList = new HashMap<>();
    }

    // Add product
    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock
    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    // Purchase product
    public String purchaseItem(String productId, int userId) {

        int available = stock.getOrDefault(productId, 0);

        if (available > 0) {
            stock.put(productId, available - 1);
            return "Success. Remaining stock: " + (available - 1);
        }

        Queue<Integer> queue = waitingList.get(productId);
        queue.add(userId);

        return "Added to waiting list. Position #" + queue.size();
    }

    // View waiting list
    public Queue<Integer> getWaitingList(String productId) {
        return waitingList.get(productId);
    }
}