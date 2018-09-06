/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Aaron
 */
public class Connect4Board extends javax.swing.JPanel {

    private Connect4Cell[] turnBand;    
    private Connect4Cell[][] board;
    
    private boolean redTurn=true;
    private CellStatus turn = CellStatus.REDOUT;
    
    private Connect4JFrame parent;
    
    public CellStatus getTurn(){
        return this.turn;
    }
            
    /**
     * Creates new form Connect4Board
     */
    public Connect4Board(Connect4JFrame parent) {
        initComponents();
        
        this.parent = parent;
        ((GridLayout)this.getLayout()).setHgap(0);
        ((GridLayout)this.getLayout()).setVgap(0);
        
        this.setMaximumSize(new Dimension(350,350));
        this.setMinimumSize(new Dimension(350,350));
        
        turnBand = new Connect4Cell[7];
        for(int i=0;i<7;i++){
            turnBand[i]=new Connect4Cell(this,i,CellStatus.EMPTYOUT,true);
            this.add(turnBand[i]);
        }
        
        //turnBand[0].setCell(CellStatus.PURPLEOUT);
        
        board=new Connect4Cell[6][];
        for(int x=0;x<6;x++){
            board[x]=new Connect4Cell[7];
            for(int y=0;y<7;y++){
                board[x][y]=new Connect4Cell(this,y,CellStatus.EMPTY);
                //board[x][y].setIcon(null);
                //board[x][y].setText(x+","+y);
                this.add(board[x][y]);
            }
        } 
        
    }
    
    public void setTurn(boolean redTurn){
        if(redTurn){
            this.redTurn = true;
            this.turn = CellStatus.REDOUT;
        }else{
            this.redTurn = false;
            this.turn = CellStatus.PURPLEOUT;
        }
    }

    public void setMessage(String message){
        parent.setMessage(message);
    }
    
    public boolean play(int playedColumn) throws InterruptedException{
        if(board[0][playedColumn].isPlayed()){
            return false;
        }        
        turnBand[playedColumn].setCell(turn);
        Thread.sleep(50);
        turnBand[playedColumn].setCell(CellStatus.EMPTYOUT);
        Thread.sleep(50);
        for(int y=0;y<6;y++){
            if(y==5 || (y<5 && board[y+1][playedColumn].isPlayed())){
                board[y][playedColumn].play(turn);
                break;
            }
            board[y][playedColumn].setCell(Connect4Cell.getTurnIn(turn));
            Thread.sleep(50);
            board[y][playedColumn].setCell(CellStatus.EMPTY);   
            Thread.sleep(50);
        }
        
        this.parent.turn(!redTurn);
        return true;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(7, 7));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
