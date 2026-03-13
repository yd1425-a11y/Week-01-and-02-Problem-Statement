package main.java.problems;

import java.util.*;

public class AutocompleteSystem {

    private TrieNode root;
    private HashMap<String, Integer> frequency;

    public AutocompleteSystem() {
        root = new TrieNode();
        frequency = new HashMap<>();
    }

    // Add query to system
    public void insert(String query) {

        frequency.put(query, frequency.getOrDefault(query, 0) + 1);

        TrieNode node = root;

        for (char c : query.toCharArray()) {

            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
        node.word = query;
    }

    // Search suggestions for prefix
    public List<String> search(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c))
                return new ArrayList<>();

            node = node.children.get(c);
        }

        List<String> results = new ArrayList<>();

        dfs(node, results);

        results.sort((a, b) ->
                frequency.get(b) - frequency.get(a)
        );

        return results.subList(0, Math.min(10, results.size()));
    }

    // DFS through trie
    private void dfs(TrieNode node, List<String> results) {

        if (node.isWord)
            results.add(node.word);

        for (TrieNode child : node.children.values()) {
            dfs(child, results);
        }
    }
}