package com.mycompany.assignment01;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Keyboard keyboard = new Keyboard();
        Label textToType = new Label("1 of 6");
        Label notHandled = new Label("Not Handled");
        notHandled.setStyle("-fx-text-fill: red");
        TextField tf = new TextField();
        tf.setScaleX(100);
        
        VBox topBox = new VBox(5, textToType, tf);
        VBox bottomBox = new VBox(5, notHandled);
        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);
        root.setCenter(keyboard.getView());
        
        Scene scene = new Scene(root, 640, 480);
        
        scene.setOnKeyPressed(e -> {
        Button btn = keyboard.getKeyMap().get(e.getCode());
        if (btn != null) {
            btn.setStyle("-fx-background-color: blue");
        } else {
            bottomBox.setAlignment(Pos.CENTER);
            root.setBottom(bottomBox);
        }
        });

        scene.setOnKeyReleased(e -> {
            Button btn = keyboard.getKeyMap().get(e.getCode());
            if (btn != null) {
                btn.setStyle("");
            }
        });
        
        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}