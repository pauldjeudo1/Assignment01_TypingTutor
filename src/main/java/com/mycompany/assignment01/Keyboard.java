/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment01;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Paul Djeudo
 */
public class Keyboard {
    private final Map<KeyCode, Button> keyMap = new HashMap<>();
    private final VBox root;

    public Keyboard() {
        root = new VBox(4);
        
        String[] row1 = {"1","2","3","4","5","6","7","8","9","0","Backspace"};
        String[] row2 = {"Q","W","E","R","T","Y","U","I","O","P","[","]"};
        String[] row3 = {"A","S","D","F","G","H","J","K","L",";","'","Enter"};
        String[] row4 = {"Shift","Z","X","C","V","B","N","M",",",".","/"};
        String[] row5 = {"Space"};
        
        root.getChildren().add(createRowOfKeys(row1));
        root.getChildren().add(createRowOfKeys(row2));
        root.getChildren().add(createRowOfKeys(row3));
        root.getChildren().add(createRowOfKeys(row4));
        root.getChildren().add(createRowOfKeys(row5));
    }
    
    private HBox createRowOfKeys(String[] keys) {
        HBox row = new HBox(4);
        for (String key : keys) {
            Button btn = new Button(key);
            btn.setMinWidth(35);
            
            if (key.equals("Space")) { 
                btn.setMinWidth(250);
            } else if (key.equals("Shift") || key.equals("Backspace")) {
                btn.setMinWidth(60);
            }
            
            keyMap.put(KeyCode.getKeyCode(key), btn);
            row.getChildren().add(btn);
        }
        return row;
    }

    public Map<KeyCode, Button> getKeyMap() {
        return keyMap;
    }
    
    public VBox getView() {
        return root;
    }
}

