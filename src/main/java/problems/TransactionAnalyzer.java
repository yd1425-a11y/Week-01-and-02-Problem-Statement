package main.java.problems;


import java.util.*;

public class TransactionAnalyzer {

    // Classic Two-Sum
    public List<int[]> findTwoSum(List<Transaction> transactions, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                result.add(new int[]{
                        map.get(complement).id,
                        t.id
                });
            }

            map.put(t.amount, t);
        }

        return result;
    }

    // Duplicate detection
    public Map<String, List<Transaction>> detectDuplicates(List<Transaction> transactions) {

        Map<String, List<Transaction>> duplicates = new HashMap<>();

        for (Transaction t : transactions) {

            String key = t.amount + "-" + t.merchant;

            duplicates.putIfAbsent(key, new ArrayList<>());
            duplicates.get(key).add(t);
        }

        return duplicates;
    }

}