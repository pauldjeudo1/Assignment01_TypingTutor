/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment01;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Paul Djeudo
 */
public class AppController implements EventHandler<ActionEvent>{
    //key pressed, key released
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button clicked!");
    }
    
}
