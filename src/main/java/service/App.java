package service;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Устанавливаем UTF-8 для вывода
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        Trie trie = new Trie();
        trie.insert("яблоко");
        trie.insert("яблоня");
        trie.insert("ящик");
        trie.insert("банан");
        
        Console console = System.console();
        if (console == null) {
            demoMode(trie);
            return;
        }

        while (true) {
            String input = console.readLine("%nВведите префикс (или 'exit' для выхода): ");
            if (input == null || "exit".equalsIgnoreCase(input.trim())) {
                break;
            }
            
            List<String> suggestions = trie.getSuggestions(input.trim());
            if (suggestions.isEmpty()) {
                console.printf("Нет предложений для '%s'%n", input);
            } else {
                console.printf("Найдено %d вариантов:%n", suggestions.size());
                suggestions.forEach(console::printf);
            }
        }
    }

    private static void demoMode(Trie trie) {
        System.out.println("\nРежим демонстрации (запустите из командной строки для интерактивного ввода)");
        System.out.println("Пример для 'я': " + trie.getSuggestions("я"));
        System.out.println("Пример для 'яб': " + trie.getSuggestions("яб"));
        System.out.println("Пример для 'б': " + trie.getSuggestions("б"));
    }
}