/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Aaron
 */
public class Connect4Java {

    private JMenuBar mainMenu;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connect4Java connect4Java = new Connect4Java();      
    }
    
    public Connect4Java(){
        JFrame mainFrame = new JFrame("Connect 4");        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        
        mainMenu = new JMenuBar();
        
        JMenu game = new JMenu("Game");
        JMenu aboutConnect4 = new JMenu("About Connect4");
        mainMenu.add(game);
        mainMenu.add(aboutConnect4);
        
        mainFrame.setJMenuBar(mainMenu);
        mainFrame.pack();        
        mainFrame.setVisible(true);
    }
}
