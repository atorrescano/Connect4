/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

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
        game.setMnemonic(KeyEvent.VK_G);
        
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setMnemonic(KeyEvent.VK_N);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });
        
        exit.setMnemonic(KeyEvent.VK_X);
        
        game.add(newGame);
        game.add(exit);
        
        JMenuItem aboutConnect4 = new JMenuItem("About Connect4");
                
        aboutConnect4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JDialog aboutDialog = new JDialog(mainFrame, "About Connect 4", true);
                JTextArea aboutText = new JTextArea("Java Again!\n\nGame of Connect 4\nCoded by:\natorrescano@gmail.com\nguadalupe.chilton@gmail.com");
                aboutDialog.add(aboutText);
                aboutDialog.pack();
                aboutDialog.setLocationRelativeTo(mainFrame);
                aboutDialog.setVisible(true);
            }
            });
        
        mainMenu.add(game);
        mainMenu.add(aboutConnect4);
        
        mainFrame.setJMenuBar(mainMenu);
        mainFrame.pack();        
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
