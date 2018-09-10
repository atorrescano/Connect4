/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import com.connect4.java.CellStatus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Aaron Torrescano
 */
public class Connect4Cell extends JLabel implements MouseListener {
    private CellStatus status;
    private boolean isTurnBand=false;
    private boolean isPlayed=false;
    private CellStatus playedStatus;
    private Connect4Board parent;
    private int myColumn;
    
    public Connect4Cell(Connect4Board parent, int myColumn){        
        super();
        this.parent = parent;
        status = CellStatus.EMPTY;
        this.setIcon(status.getImage());
        isTurnBand=false;
        this.myColumn = myColumn;
        this.setMinimumSize(new Dimension(50,50));
        this.setSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(50,50));
        
        this.addMouseListener(this);
    }
    
    public Connect4Cell(Connect4Board parent, int myColumn,CellStatus status){
        this(parent,myColumn);
        this.status = status;
        this.setIcon(status.getImage());
    }

    public Connect4Cell(Connect4Board parent, int myColumn,CellStatus status, boolean isTurnBand){
        this(parent,myColumn,status);
        this.isTurnBand = isTurnBand;
    }    
    
    public void setCell(CellStatus status, boolean opaque){
        this.status = status;
        this.setIcon(status.getImage());
        this.setOpaque(opaque);    
        this.paint(this.getGraphics());
        }
    
    public void setCell(CellStatus status){
        setCell(status,false);
    }
    
    public void setCellStatusPlayed(CellStatus status){
        this.isPlayed = true;
        this.status=status;
    }
    
    public CellStatus getCellStatus(){
        return this.status;
    }
    
    public void play(CellStatus playedStatus){
        this.isPlayed = true;
        this.playedStatus = getTurnIn(playedStatus);
        this.setCell(this.playedStatus);
    }
    
    public boolean isPlayed(){
        return this.isPlayed;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(parent.isPCTurn()){
            return;
        }
        try {
            if(!parent.play(this.myColumn)){
                parent.setMessage("Not a valid play, please try again!");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Connect4Cell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(parent.isPCTurn()){
            return;
        }
        if(isTurnBand){
            setCell(parent.getTurn());
        }
        else if(!isPlayed){
            setCell(getTurnIn(),true);            
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(parent.isPCTurn()){
            return;
        }
        if(isTurnBand){
            setCell(CellStatus.EMPTYOUT);
        }
        else if(!isPlayed){
            setCell(CellStatus.EMPTY,false);            
        }
    }
    
    private CellStatus getTurnIn(){
        if(parent.getTurn()==CellStatus.REDOUT){
            return CellStatus.REDIN;
        }
        
        return CellStatus.PURPLEIN;
    }

    public static CellStatus getTurnIn(CellStatus turn){
        if(turn==CellStatus.REDOUT){
            return CellStatus.REDIN;
        }
        
        return CellStatus.PURPLEIN;
    }
}
