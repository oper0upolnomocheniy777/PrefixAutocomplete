package service;

import model.TrieNode;
import java.util.*;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Добавление слова в дерево
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.getChildren().putIfAbsent(ch, new TrieNode());
            current = current.getChildren().get(ch);
        }
        current.setEndOfWord(true);
        current.setFrequency(current.getFrequency() + 1);
    }

    // Поиск всех слов по префиксу (сортировка по частоте)
    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode node = findNode(prefix);
        if (node != null) {
            dfs(node, prefix, suggestions);
        }
        suggestions.sort(Comparator.comparingInt(this::getFrequency).reversed());
        return suggestions;
    }

    private TrieNode findNode(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                return null;
            }
            current = current.getChildren().get(ch);
        }
        return current;
    }

    private void dfs(TrieNode node, String currentWord, List<String> suggestions) {
        if (node.isEndOfWord()) {
            suggestions.add(currentWord);
        }
        for (char ch : node.getChildren().keySet()) {
            dfs(node.getChildren().get(ch), currentWord + ch, suggestions);
        }
    }

    private int getFrequency(String word) {
        TrieNode node = findNode(word);
        return (node != null && node.isEndOfWord()) ? node.getFrequency() : 0;
    }
}