/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java.ui;

import com.connect4.java.CellStatus;
import com.connect4.java.BoardCandidate;
import com.connect4.java.DecisionTreeNode;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Class that represents a connect 4 board and its UI.
 *
 * @author Aaron Torrescano
 */
public class Connect4Board extends javax.swing.JPanel {

    private final Logger logger = Logger.getLogger("com.connect4.java");

    private Connect4Cell[] turnBand;    
    private Connect4Cell[][] board;
    
    private boolean redTurn=true;
    private CellStatus turn = CellStatus.REDOUT;
    
    private Connect4JFrame parent;
    
    private boolean isPCTurn;
    
    /**Method that indicates the cell status in turn
     *
     * @return the CellStatus that is in turn.
     */
    public CellStatus getTurn(){
        return this.turn;
    }
            
    /**
     * Creates new form Connect4Board
     * 
     * @param parent the owner of the JFrame.
     */
    public Connect4Board(Connect4JFrame parent) {
        initComponents();
        
        this.parent = parent;
        ((GridLayout)this.getLayout()).setHgap(0);
        ((GridLayout)this.getLayout()).setVgap(0);
        
        this.setMaximumSize(new Dimension(350,350));
        this.setMinimumSize(new Dimension(350,350));
        
        reset();
    }
    
    /**This method removes all the contained components and it creates an empty 
     * new board of Connect4Cells.
     */
    public void reset(){
        this.removeAll();
        turnBand = new Connect4Cell[7];
        for(int i=0;i<7;i++){
            turnBand[i]=new Connect4Cell(this,i,CellStatus.EMPTYOUT,true);
            this.add(turnBand[i]);
        }
        
        board=new Connect4Cell[6][];
        for(int x=0;x<6;x++){
            board[x]=new Connect4Cell[7];
            for(int y=0;y<7;y++){
                board[x][y]=new Connect4Cell(this,y,CellStatus.EMPTY);
                this.add(board[x][y]);
            }
        } 
    }

    /**Sets the current turn. It manages the level of the PC.
     *
     * @param redTurn true if it is a redTurn, false if it is a purple turn
     */
    public void setTurn(boolean redTurn){
        if(redTurn){
            this.redTurn = true;
            this.turn = CellStatus.REDOUT;
        }else{
            this.redTurn = false;
            this.turn = CellStatus.PURPLEOUT;
            if(isPCTurn){
                try {
                    play(getPCTurn(2));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Connect4Board.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**Method that gets the column that the computer decides to play at
     *
     *@param level 0 for completely Math.random, 1 or higher is the level of 
     * tree depth that it is constructed for the minmax algorithm
     * 
     * @return a value between 0-6 of the column to throw at
     */
    public int getPCTurn(int level){
        if(level==0){
            return randomPCThrow();
        }
        return minMaxPCThrow(level);
    }        
    
    /**The MinMax calculation algorithm
     *
     *@param level 1 or higher, it represents the depth of the decision tree
     * @return a value between 0-6 of the column to throw at
     */
    public int minMaxPCThrow(int level){
        int column=0;

        CellStatus player = CellStatus.PURPLEIN;
        DecisionTreeNode<BoardCandidate> decisionNodes = null;

        decisionNodes=buildTreeByLevel(0,level,null,player);
                        
        System.out.println(decisionNodes);
        //logger.info("Decided:"+decisionTree.last().getColumn());
        
        //returns the Max graded option column
        return decisionNodes.getColumnToPlay(level);
    }
    
    private DecisionTreeNode<BoardCandidate> buildTreeByLevel(int level, int maxLevel, DecisionTreeNode<BoardCandidate> parentDecisionNode, CellStatus player){                
        
        if(parentDecisionNode ==null){
            parentDecisionNode = new DecisionTreeNode<BoardCandidate>(-1,CellStatus.PURPLEIN,null);
        }
        
        DecisionTreeNode<BoardCandidate> decisionNode = null;
        for(int i=0;i<7;i++){
            if( (level==0 && !board[0][i].isPlayed()) ||
                  (level!=0 && !parentDecisionNode.isColumnPlayed(i))){

                decisionNode = new DecisionTreeNode<BoardCandidate>(level, player, parentDecisionNode);

                BoardCandidate temp = null;

                if(level==0){
                    temp = new BoardCandidate(board,i,player);
                } else {
                    temp = new BoardCandidate(parentDecisionNode.getCandidate().getBoard(),i,player);
                }                    

                decisionNode.add(temp);
                parentDecisionNode.addChild(decisionNode);
                if(level+1<maxLevel){
                    buildTreeByLevel(level+1,maxLevel,decisionNode,player==CellStatus.PURPLEIN ? CellStatus.REDIN : CellStatus.PURPLEIN);
                }
            }
        }            
        
        return parentDecisionNode;
    }
    
    /**Select a Math.random option
     * 
     * @return a value between 0-6 of the column to throw at
     */
    public int randomPCThrow(){
        int maxTries=14;
        int column = 0;
        do {
            maxTries--;
            column=((int)(Math.random()*10))%7;
        } while(board[0][column].isPlayed()&&maxTries>0);
        
        return column;
    }
    
    /**Stub method to update status bar
     *
     * @param message message to be set at the status bar
     */
    public void setMessage(String message){
        parent.setMessage(message);
    }
    
    /**Animated throw at a column in the board.
     * 
     * @param playedColumn the column that is being selected.
     * @return true if the action was successful, false if the column is full.
     * @throws java.lang.InterruptedException in case the sleep of the animation is interrupted
     */
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
    
    /**Method that evaluates if the current board has a winner.
     *
     *@return the winner of the match
     */
    public CellStatus getWinner(){
        int countNotPlayed=0;
        for(int y=5;y>=0;y--){
            countNotPlayed=0;
            for(int x=6;x>=0;x--){
                if(!board[y][x].isPlayed()){
                    countNotPlayed++;
                    continue;
                }
                
                CellStatus evaluating = board[y][x].getCellStatus();
                
                if(evaluateMasks(y, x, evaluating)){
                   return evaluating; 
                }
            }
            if(countNotPlayed==7){
                break;
            }
        } 
        return null;
    }
    
    private boolean evaluateMasks(int y, int x, CellStatus evaluating){
        //skip downwards
        if(y>2){
            //upwards
            if(evaluating==board[y-3][x].getCellStatus()&&
                    evaluating==board[y-2][x].getCellStatus()&& 
                    evaluating==board[y-1][x].getCellStatus() &&
                    evaluating==board[y][x].getCellStatus()){
                return true;
            }
            //skip diagonals left and left
            if(x<3){
                //right diagonal
                if(evaluating==board[y-3][x+3].getCellStatus()&&
                        evaluating==board[y-2][x+2].getCellStatus()&& 
                        evaluating==board[y-1][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }
                //right
                if(evaluating==board[y][x+3].getCellStatus()&&
                        evaluating==board[y][x+2].getCellStatus()&& 
                        evaluating==board[y][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                
            } else if(x==3){ //all
                //right diagonal
                if(evaluating==board[y-3][x+3].getCellStatus()&&
                        evaluating==board[y-2][x+2].getCellStatus()&& 
                        evaluating==board[y-1][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }
                //right
                if(evaluating==board[y][x+3].getCellStatus()&&
                        evaluating==board[y][x+2].getCellStatus()&& 
                        evaluating==board[y][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                
                //left diagonal
                if(evaluating==board[y-3][x-3].getCellStatus()&&
                        evaluating==board[y-2][x-2].getCellStatus()&& 
                        evaluating==board[y-1][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }
                //left
                if(evaluating==board[y][x-3].getCellStatus()&&
                        evaluating==board[y][x-2].getCellStatus()&& 
                        evaluating==board[y][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                                                
            }             
            else{ //skip diagonals right and right
                //left diagonal
                if(evaluating==board[y-3][x-3].getCellStatus()&&
                        evaluating==board[y-2][x-2].getCellStatus()&& 
                        evaluating==board[y-1][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }
                //left
                if(evaluating==board[y][x-3].getCellStatus()&&
                        evaluating==board[y][x-2].getCellStatus()&& 
                        evaluating==board[y][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                                
            }
        } else{ //skip upwards and downwards, only laterals
            if(x<3){ //skip left
                //right
                if(evaluating==board[y][x+3].getCellStatus()&&
                        evaluating==board[y][x+2].getCellStatus()&& 
                        evaluating==board[y][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                
            } else if(x==3){ //left and right
                //right
                if(evaluating==board[y][x+3].getCellStatus()&&
                        evaluating==board[y][x+2].getCellStatus()&& 
                        evaluating==board[y][x+1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                
                //left
                if(evaluating==board[y][x-3].getCellStatus()&&
                        evaluating==board[y][x-2].getCellStatus()&& 
                        evaluating==board[y][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                                                
            } 
            else{ //skip right
                //left
                if(evaluating==board[y][x-3].getCellStatus()&&
                        evaluating==board[y][x-2].getCellStatus()&& 
                        evaluating==board[y][x-1].getCellStatus() &&
                        evaluating==board[y][x].getCellStatus()){
                    return true;
                }                                                
            }            
        }
        return false;
    }
        
    /**Verifies if there is any option to throw available at the board.
     *
     * @return true if there is at least one option, false otherwise.
     */
    public boolean isAnyOptionAvailable(){
        for(int i=0;i<7;i++){
            if(!board[0][i].isPlayed()){
                return true;
            }
        }
        return false;
    }
    
    /**Sets the value for the computer taking a turn.
     * 
     * @param isPCTurn whether it is the computers turn or not
     */
    public void setPCTurn(boolean isPCTurn){
        this.isPCTurn = isPCTurn;
    }
    
    /**Gets the value of whether it is the computers turn, or not.
     * 
     * @return true if it is the computers turn, false otherwise
     */
    public boolean isPCTurn(){
        return isPCTurn;
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
