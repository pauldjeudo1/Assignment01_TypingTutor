package com.mycompany.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        GridPane gridpane = new GridPane();
        Keyboard keyboard = new Keyboard();
        Label textToType = new Label("1 of 6");

        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setPadding(new Insets(15));
        
        BorderPane root = new BorderPane();
        root.setBottom(gridpane);
        root.setTop(textToType);
        root.setCenter(keyboard.getView());
        
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}