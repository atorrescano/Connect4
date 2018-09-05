/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Aaron
 */
public class Connect4Cell extends JLabel {
    private CellStatus status;
    
    public Connect4Cell(){
        super(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/Empty.png"));
        status = CellStatus.EMPTY;
        this.setMinimumSize(new Dimension(50,50));
        this.setSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(50,50));
    }
    
    public Connect4Cell(CellStatus status){
        super();
        this.setMinimumSize(new Dimension(50,50));
        this.setSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(50,50));
        this.status = status;
        switch(this.status){
            case EMPTY:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/Empty.png"));
                break;            
            case EMPTYOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/EmptyOut.png"));
                break;            
            case REDIN:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/RedChipIn.png"));
                break;            
            case PURPLEIN:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/PurpleChipIn.png"));
                break;            
            case REDOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/RedChipOut.png"));
                break;            
            case PURPLEOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/PurpleChipOut.png"));
                break;            
        }
    }
    
    public void setCell(CellStatus status){
        this.status = status;
        switch(this.status){
            case EMPTY:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/Empty.png"));
                break;            
            case EMPTYOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/EmptyOut.png"));
                break;            
            case REDIN:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/RedChipIn.png"));
                break;            
            case PURPLEIN:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/PurpleChipIn.png"));
                break;            
            case REDOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/RedChipOut.png"));
                break;            
            case PURPLEOUT:
                this.setIcon(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/PurpleChipOut.png"));
                break;            
        }        
    }
}
