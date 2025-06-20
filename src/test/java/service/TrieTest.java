package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearch() {
        trie.insert("apple");
        trie.insert("app");
        
        List<String> suggestions = trie.getSuggestions("app");
        assertAll(
            () -> assertTrue(suggestions.contains("app")),
            () -> assertTrue(suggestions.contains("apple"))
        );
    }

    @Test
    public void testEmptyPrefix() {
        trie.insert("test");
        trie.insert("temp");
        
        List<String> suggestions = trie.getSuggestions("");
        assertTrue(suggestions.containsAll(List.of("test", "temp")));
    }

    @Test
    public void testNonExistentPrefix() {
        trie.insert("hello");
        assertTrue(trie.getSuggestions("xyz").isEmpty());
    }

    @Test
    public void testFrequencySorting() {
        trie.insert("java");
        trie.insert("java");
        trie.insert("javascript");
        
        List<String> suggestions = trie.getSuggestions("jav");
        assertEquals("java", suggestions.get(0));
        assertEquals("javascript", suggestions.get(1));
    }
}