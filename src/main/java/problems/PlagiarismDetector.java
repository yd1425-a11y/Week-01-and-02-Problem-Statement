package main.java.problems;
import java.util.*;

public class PlagiarismDetector {

    // ngram -> documents containing it
    private HashMap<String, Set<String>> ngramIndex;

    private int n; // size of n-gram

    public PlagiarismDetector(int n) {
        this.n = n;
        ngramIndex = new HashMap<>();
    }

    // Add document to system
    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        for (String ngram : ngrams) {

            ngramIndex.putIfAbsent(ngram, new HashSet<>());

            ngramIndex.get(ngram).add(docId);
        }
    }

    // Analyze document similarity
    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String ngram : ngrams) {

            if (ngramIndex.containsKey(ngram)) {

                for (String existingDoc : ngramIndex.get(ngram)) {

                    if (!existingDoc.equals(docId)) {

                        matchCount.put(
                                existingDoc,
                                matchCount.getOrDefault(existingDoc, 0) + 1
                        );
                    }
                }
            }
        }

        System.out.println("Analysis for " + docId);

        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {

            double similarity =
                    (entry.getValue() * 100.0) / ngrams.size();

            System.out.println(
                    "Match with " + entry.getKey()
                            + " → " + similarity + "% similarity"
            );
        }
    }

    // Generate ngrams
    private List<String> generateNGrams(String text) {

        String[] words = text.toLowerCase().split("\\s+");

        List<String> result = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words[i + j]).append(" ");
            }

            result.add(gram.toString().trim());
        }

        return result;
    }
}