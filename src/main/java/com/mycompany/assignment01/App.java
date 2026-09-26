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
        //creating controls and layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Keyboard keyboard = new Keyboard();
        TextField tf = new TextField();
        tf.setPrefWidth(400);
        Label textToType = new Label("");
        Label notHandled = new Label("");
        notHandled.setStyle("-fx-text-fill: red");
        Label correctLabel = new Label("");
        Label incorrectLabel = new Label("");
        
        //creating array of sample texts
        String[] sampleTexts = {
            "Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
            "The quick brown fox jumps over the lazy dog.",
            "Five big quacking zephyrs jolt my wax bed.",
            "Sympathizing would fix Quaker objectives.",
            "A large fawn jumped quickly over white zinc boxes."
        };

        VBox topBox = new VBox(5, textToType, tf);
        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);
        VBox bottomBox = new VBox(5, notHandled, correctLabel,
        incorrectLabel);
        root.setBottom(bottomBox);

        
        root.setCenter(keyboard.getView());

        Scene scene = new Scene(root, 640, 480);

        scene.setOnKeyPressed(e -> {
            StringBuilder typedText = new StringBuilder();
            
            Button btn = keyboard.getKeyMap().get(e.getCode());
            if (btn != null) {
                btn.setStyle("-fx-background-color: blue");
                // Append the character to the StringBuilder and update the TextField
                String keyText = e.getCode().getName().toLowerCase();
                typedText.append(keyText);
                tf.setText(typedText.toString());
            } else {
                notHandled.setText("Not handled");
            }
        });

        scene.setOnKeyReleased(e -> {
            Button btn = keyboard.getKeyMap().get(e.getCode());
            if (btn != null) {
                btn.setStyle("");
            }
            notHandled.setText("");
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
    
}