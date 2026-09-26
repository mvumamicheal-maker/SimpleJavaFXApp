package com.example.hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HellojavaFX extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, Mulungushi University JavaFX!");
        Scene scene = new Scene(label, 400, 200);
        stage.setTitle("Practical Lab 1 - Micheal Mvuma");
        stage.setScene(scene);
        stage.show();
    }
}

