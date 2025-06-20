package service;

import model.TrieNode;
import java.util.*;

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, k -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.setFrequency(current.getFrequency() + 1);
    }

    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode node = findNode(prefix);
        if (node != null) {
            dfs(node, new StringBuilder(prefix), suggestions);
        }
        suggestions.sort((a, b) -> Integer.compare(getFrequency(b), getFrequency(a)));
        return suggestions;
    }

    private TrieNode findNode(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) return null;
            current = current.getChildren().get(ch);
        }
        return current;
    }

    private void dfs(TrieNode node, StringBuilder path, List<String> result) {
        if (node.isEndOfWord()) {
            result.add(path.toString());
        }
        node.getChildren().forEach((ch, child) -> {
            dfs(child, path.append(ch), result);
            path.deleteCharAt(path.length() - 1);
        });
    }

    private int getFrequency(String word) {
        TrieNode node = findNode(word);
        return (node != null && node.isEndOfWord()) ? node.getFrequency() : 0;
    }
}