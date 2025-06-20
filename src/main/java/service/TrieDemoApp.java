package service;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrieDemoApp extends Application {
    private final Trie trie = new Trie();

    @Override
    public void start(Stage stage) {
        // Инициализация данных
        trie.insert("яблоко");
        trie.insert("яблоня");
        trie.insert("банан");
        trie.insert("банка");
        trie.insert("компьютер");
        trie.insert("команда");

        // Создание элементов интерфейса
        TextField inputField = new TextField();
        ListView<String> suggestionsList = new ListView<>();
        
        // Обработка ввода
        inputField.textProperty().addListener((obs, oldVal, newVal) -> {
            suggestionsList.getItems().setAll(trie.getSuggestions(newVal));
        });

        // Компоновка
        VBox root = new VBox(10, 
            new Label("Введите префикс:"), 
            inputField, 
            new Label("Предложения:"), 
            suggestionsList
        );
        root.setPadding(new javafx.geometry.Insets(15));

        // Настройка окна
        stage.setScene(new Scene(root, 300, 400));
        stage.setTitle("Trie Demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}