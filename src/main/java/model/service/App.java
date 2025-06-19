import service.Trie;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("banana");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите префикс: ");
        String prefix = scanner.nextLine();

        System.out.println("Предложения: " + trie.getSuggestions(prefix));
    }
}