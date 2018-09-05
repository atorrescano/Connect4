/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Aaron
 */
public class Connect4Cell extends JLabel implements MouseListener {
    private CellStatus status;
    private boolean isTurnBand=false;
    private CellStatus turnSwitch;
    private boolean isPlayed=false;
    
    public Connect4Cell(){
        super(new ImageIcon("D:/CODE/Connect4/Connect4Java/src/images/Empty.png"));
        status = CellStatus.EMPTY;
        isTurnBand=false;
        this.setMinimumSize(new Dimension(50,50));
        this.setSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(50,50));
        
        this.addMouseListener(this);
    }
    
    public Connect4Cell(CellStatus status){
        this();
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

    public Connect4Cell(CellStatus status, boolean isTurnBand){
        this(status);
        this.isTurnBand = isTurnBand;
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
    
    public void setPlayed(){
        this.isPlayed = true;
    }
    
    public void setTurnSwitch(CellStatus turnSwitch){
        this.turnSwitch = turnSwitch;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(isTurnBand){
            setCell(turnSwitch);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(isTurnBand){
            setCell(CellStatus.EMPTYOUT);
        }
    }
    
}
