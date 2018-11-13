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
 * This class represents a UI connect 4 cell. It has also events for the mouse to 
 * select the move.
 * @author Aaron Torrescano
 */
public class Connect4Cell extends JLabel implements MouseListener {
    private CellStatus status;
    private boolean isTurnBand=false;
    private boolean isPlayed=false;
    private CellStatus playedStatus;
    private Connect4Board parent;
    private int myColumn;
    
    /** Creates a cell with the parent container an the corresponding column it belongs to. 
     * It is an Empty cell inside the board.
     *
     * @param parent the board that contains the cell
     * @param myColumn it is the column the cell is part of
     */
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
    
    /** Creates a cell with the parent container an the corresponding column it 
     * belongs to and the status of the cell (inside the board)
     *
     * @param parent the board that contains the cell
     * @param myColumn it is the column the cell is part of
     * @param status the status of the cell
     */
    public Connect4Cell(Connect4Board parent, int myColumn,CellStatus status){
        this(parent,myColumn);
        this.status = status;
        this.setIcon(status.getImage());
    }

    /** Creates a cell with the parent container an the corresponding column it 
     * belongs to and the status of the cell. Inside or outside the the board
     *
     * @param parent the board that contains the cell
     * @param myColumn it is the column the cell is part of
     * @param status the status of the cell
     * @param isTurnBand true if the cell is outside the board (on top of it), false otherwise.
     */
    public Connect4Cell(Connect4Board parent, int myColumn,CellStatus status, boolean isTurnBand){
        this(parent,myColumn,status);
        this.isTurnBand = isTurnBand;
    }    

    /**It sets the Cell UI and status -wise.
     *
     *@param  status the Cell new status
     * @param opaque whether the cell is set to opaque or not
     */
    public void setCell(CellStatus status, boolean opaque){
        this.status = status;
        this.setIcon(status.getImage());
        this.setOpaque(opaque);    
        this.paint(this.getGraphics());
        }
    
    /**It sets the Cell UI and status -wise, not opaque.
     *
     *@param  status the Cell new status
     */
    public void setCell(CellStatus status){
        setCell(status,false);
    }

    /**Marks a cell as played by status player. This method disregards the UI part.
     *
     *@param status the player of the cell
     */
    public void setCellStatusPlayed(CellStatus status){
        this.isPlayed = true;
        this.status=status;
    }
    
    /**Gets the current status of the cell
     *
     * @return The cell status
     */
    public CellStatus getCellStatus(){
        return this.status;
    }
    
    /**Marks a cell as played by status player and updates the UI.
     *
     *@param playedStatus the player of the cell
     */    
    public void play(CellStatus playedStatus){
        this.isPlayed = true;
        this.playedStatus = getTurnIn(playedStatus);
        this.setCell(this.playedStatus);
    }
    
    /**Validates if the cell is played or not.
     *
     *@return true if the cell is played, false otherwise
     */
    public boolean isPlayed(){
        return this.isPlayed;
    }
    
    /**Event of the cell being clicked. Disregards the event during a computer turn.
     * It validates if the column is full so the same player can attempt a different 
     * column
     *
     *@param e the MouseEvent object
     */
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

    /**Event of the cell being hove. Shows the user the chip that would be played 
     * at the corresponding cell. It disregards during computers turn.
     *
     *@param e the MouseEvent object
     */
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

    /**Event of the cell being left. Empties the cell if it has not being played. 
     * It disregards during computers turn.
     *
     *@param e the MouseEvent object
     */
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
    
    @Override
    public String toString(){
        if(status==CellStatus.PURPLEIN){
            return "P";
        }else if(status == CellStatus.REDIN){
            return "R";
        }else{
            return "E";
        }            
    }

    /**Method that transforms a chip from being out to be inside the board.
     *
     * @param turn the player taking the turn.
     * @return the player inside version of the status
     */
    public static CellStatus getTurnIn(CellStatus turn){
        if(turn==CellStatus.REDOUT){
            return CellStatus.REDIN;
        }
        
        return CellStatus.PURPLEIN;
    }
}
