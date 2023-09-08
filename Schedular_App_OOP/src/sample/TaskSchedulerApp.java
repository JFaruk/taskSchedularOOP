package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TaskSchedulerApp extends Application {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Scheduler Application"); //set the name of project that will display on the top of pop up

        ListView<Task> taskListView = new ListView<>(tasks);

        TextField nameField = new TextField(); // input field
        DatePicker datePicker = new DatePicker(); // date picker field
        Button addButton = new Button("Add Task");
        Button deleteButton = new Button("Delete Selected");

        //task add functionality on button click
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            if (!name.isEmpty() && datePicker.getValue() != null) {
                Task newTask = new Task(name, datePicker.getValue());
                tasks.add(newTask);
                nameField.clear();
                datePicker.setValue(null);
            }
        });

        //task delete button functionality
        deleteButton.setOnAction(event -> {
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tasks.remove(selectedTask);
            }
        });

        VBox layout = new VBox(10);
//        layout.getStyleClass().add("main-vbox");
        layout.getChildren().addAll(
                new Label("Task Name:"), nameField,
                new Label("Choose a Date:"), datePicker, addButton, taskListView, deleteButton
        );

        Scene scene = new Scene(layout, 400, 600);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Load external CSS
        primaryStage.setScene(scene);
        primaryStage.show(); // display vbox pop up
    }

    public static class Task {
        private String name;
        private LocalDate dueDate;

        public Task(String name, LocalDate dueDate) {
            this.name = name;
            this.dueDate = dueDate;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        @Override
        public String toString() {
            return "Task Name: " + getName() + " - Date: " + getDueDate();
        }
    }
}

//new code

