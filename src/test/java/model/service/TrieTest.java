package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TrieTest {
    @Test
    public void testInsertAndSearch() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");

        List<String> suggestions = trie.getSuggestions("app");
        assertTrue(suggestions.contains("app"));
        assertTrue(suggestions.contains("apple"));
        assertTrue(suggestions.contains("application"));
    }

    @Test
    public void testFrequencySorting() {
        Trie trie = new Trie();
        trie.insert("java");
        trie.insert("java");
        trie.insert("javascript");

        List<String> suggestions = trie.getSuggestions("jav");
        assertEquals("java", suggestions.get(0)); // "java" встречается чаще
    }
}