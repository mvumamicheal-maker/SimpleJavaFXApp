package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HellojavaFX extends Application {
    @Override
    public void start(Stage stage) {
        // 2. Change label to "Welcome, <your name>!"
        Label message = new Label("Welcome, Micheal Mvuma!");

        // 3. Change button text to "Start"
        Button startButton = new Button("Start");
        startButton.setOnAction(event ->
                message.setText("Great! You clicked the start button.")
        );

        // 4. Add a second button called "Reset" that changes the label back
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event ->
                message.setText("Welcome, Micheal Mvuma!")
        );

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        // Add both buttons to the layout
        layout.getChildren().addAll(message, startButton, resetButton);

        Scene scene = new Scene(layout, 500, 300);

        // 1. Change window title to include your student name/number
        stage.setTitle("Practical Lab 1 - Micheal Mvuma");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

