package main.java.problems;

import java.util.*;

public class TrieNode {

    Map<Character, TrieNode> children;
    boolean isWord;
    String word;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}