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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {
    private int currentIdx = 0;
    private StringBuilder typedText = new StringBuilder();
    private boolean shiftPressed = false;

    @Override
    public void start(Stage stage) {
        //creating controls and layout
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 640, 480);
        root.setPadding(new Insets(10));
        Keyboard keyboard = new Keyboard();
        TextField tf = new TextField();
        tf.setPrefWidth(400);
        Label counterLabel = new Label("1 of  6");
        Label textToType = new Label("");
        Label notHandled = new Label("");
        notHandled.setStyle("-fx-text-fill: red");
        Label correctLabel = new Label("");
        Label incorrectLabel = new Label("");
        Button nextButton = new Button("Next");
        Button resetButton = new Button("Reset");
        HBox nextAndReset = new HBox(nextButton, resetButton);
        nextAndReset.setAlignment(Pos.CENTER);
        nextAndReset.setPadding(new Insets(10));
        
        //creating array of sample texts
        String[] sampleTexts = {
            "Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
            "The quick brown fox jumps over the lazy dog.",
            "Five big quacking zephyrs jolt my wax bed.",
            "Sympathizing would fix Quaker objectives.",
            "A large fawn jumped quickly over white zinc boxes."
        };
        
        //presenting first sample text, currentIdx = 0
        textToType.setText(sampleTexts[currentIdx]); 
        
        VBox topBox = new VBox(5, counterLabel, tf, textToType,
        nextAndReset);
        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);
        VBox bottomBox = new VBox(5, notHandled, correctLabel,
        incorrectLabel);
        root.setBottom(bottomBox);
        root.setCenter(keyboard.getView());
        
        //Event handler for pressed keys
        scene.setOnKeyPressed(e -> {
            Button btn = keyboard.getKeyMap().get(e.getCode());
            if (btn != null) {
                btn.setStyle("-fx-background-color: blue");
                
                // if user presses BACKSPACE, the previous character gets deleted
                if (e.getCode() == KeyCode.BACK_SPACE) {
                    if (typedText.length() > 0) {
                        typedText.deleteCharAt(typedText.length() - 1);
                    }
                } else if (e.getCode() == KeyCode.SHIFT) {
                    shiftPressed = true;
                } else {                
                    String keyText;
                    if (e.getCode() == KeyCode.SPACE) {
                        keyText = " ";
                    } else if (e.getCode() == KeyCode.COMMA) {
                        keyText = ",";
                    } else if (e.getCode() == KeyCode.PERIOD) {
                        keyText = ".";
                    } else {
                        keyText = e.getCode().getName().toLowerCase();
                        keyText = shiftPressed ? keyText.toUpperCase() : 
                                keyText.toLowerCase();
                    }
                    typedText.append(keyText);
                }
                
                tf.setText(typedText.toString());
                trackKeystrokes(typedText.toString(), sampleTexts[currentIdx], 
                        correctLabel, incorrectLabel);
            } else {
                notHandled.setText("Not handled");
            }
        });

        //Event handler for released keys
        scene.setOnKeyReleased(e -> {
            Button btn = keyboard.getKeyMap().get(e.getCode());
            if (btn != null) {
                btn.setStyle("");
                if (e.getCode() == KeyCode.SHIFT) {
                    shiftPressed = false;
                }
            }
            notHandled.setText("");
        });
        
        //Event handler for "Next" button
        nextButton.setOnAction(e -> {
            currentIdx = (currentIdx + 1) % sampleTexts.length; // goes back to 0 after last
            textToType.setText(sampleTexts[currentIdx]);
            tf.clear();
            counterLabel.setText((currentIdx + 1) + " of " + sampleTexts.length);
        });
        
        ////Event handler for "Reset" button
        resetButton.setOnAction(e -> {
            currentIdx = 0;
            textToType.setText(sampleTexts[currentIdx]);
            tf.clear();
            counterLabel.setText((currentIdx + 1) + " of " + sampleTexts.length);
            notHandled.setText("");
            correctLabel.setText("");
            incorrectLabel.setText("");
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }
    
    private void trackKeystrokes(String typed, String target, Label correctLabel, Label incorrectLabel) {
        int correct = 0;
        int incorrect = 0;

        for (int i = 0; i < typed.length(); i++) {
            if (i < target.length() && typed.charAt(i) == target.charAt(i)) {
                correct++;
            } else {
                incorrect++;
            }
        }

        correctLabel.setText("Correct: " + correct);
        incorrectLabel.setText("Incorrect: " + incorrect);
    }

    public static void main(String[] args) {
        launch();
    }
    
}
