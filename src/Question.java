package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This page allows users to add, view, update, and delete questions.
 */
public class Question {

    private ObservableList<String> questionList = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(questionList);

    public void show(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Title Label
        Label titleLabel = new Label("Ask a Question:");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Input Field for New Questions
        TextField questionInput = new TextField();
        questionInput.setPromptText("Type your question here...");

        // Buttons
        Button addButton = new Button("Ask");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        // Add question to the list
        addButton.setOnAction(e -> {
            String question = questionInput.getText();
            if (!question.isEmpty()) {
                questionList.add(question);
                questionInput.clear();
            }
        });

        // Update selected question
        updateButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                String newQuestion = questionInput.getText();
                if (!newQuestion.isEmpty()) {
                    questionList.set(selectedIndex, newQuestion);
                    questionInput.clear();
                }
            }
        });

        // Delete selected question
        deleteButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                questionList.remove(selectedIndex);
            }
        });

        // Navigate back to UserHomePage
        backButton.setOnAction(e -> {
            new UserHomePage().show(primaryStage);
        });

        layout.getChildren().addAll(titleLabel, questionInput, addButton, listView, updateButton, deleteButton, backButton);
        Scene questionScene = new Scene(layout, 800, 400);

        primaryStage.setScene(questionScene);
        primaryStage.setTitle("Question Page");
    }
}

