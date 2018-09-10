/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java;

import com.connect4.java.ui.Connect4Cell;

/**
 *
 * @author Aaron
 */
public class BoardCandidate implements Comparable{
    
    private Connect4Cell[][] board;
    private int column;
    private CellStatus player;    
    private int grade;
    
    public BoardCandidate(){
        
    }
    
    public BoardCandidate(Connect4Cell[][] board, int column, CellStatus player){
        this.board = new Connect4Cell[6][];
        for(int x=0;x<6;x++){
            this.board[x]=new Connect4Cell[7];
            for(int y=0;y<7;y++){
                this.board[x][y]=new Connect4Cell(null,x,board[x][y].getCellStatus());
                if(board[x][y].isPlayed()){
                    this.board[x][y].setCellStatusPlayed(board[x][y].getCellStatus());
                }                
            }
        } 

        this.column=column;
        this.player=player;
        play(column);
        System.out.print("Col:"+column);
        this.grade = this.getStatusGrade(player);
    }
    
    public int getStatusGrade(CellStatus player){
        int grade=0;
        int opponentGrade=0;
        
        int[] consecutivesTotal=new int[]{0,0,0,0,0,0,0,0};
        int[] consecutives={0,0,0,0,0,0,0,0};
        
        int countNotPlayed=0;
        for(int y=5;y>=0;y--){
            countNotPlayed=0;
            for(int x=6;x>=0;x--){
                if(!board[y][x].isPlayed()){
                    countNotPlayed++;
                    continue;
                }

            consecutives = countConsecutives(y,x,player);
                
            if(board[y][x].getCellStatus()==player){                                        
                consecutivesTotal[0]+=consecutives[0]*2;
                consecutivesTotal[1]+=consecutives[1]*4;
                consecutivesTotal[2]+=consecutives[2]*7;
                consecutivesTotal[3]+=consecutives[3]*15;
            }
            consecutivesTotal[4]+=consecutives[4];
            consecutivesTotal[5]+=consecutives[5]*3;
            consecutivesTotal[6]+=consecutives[6]*8;
            consecutivesTotal[7]+=consecutives[7]*11;
                            
            }
            if(countNotPlayed==7){
                break;
            }
        }         
        
        grade = consecutivesTotal[7]+consecutivesTotal[6]+consecutivesTotal[5]+consecutivesTotal[4]+consecutivesTotal[3]+consecutivesTotal[2]+consecutivesTotal[1]+consecutivesTotal[0];        
        System.out.println(" GradeMine:"+grade);
        return grade;
    }
    
    private int countConsecitivesCase(int y, int x, CellStatus player,PossibleMoves move){
        int localCount=0;
        
        switch(move){
            case UP:
                if(player==board[y-1][x].getCellStatus()){
                    localCount++;
                    if(player==board[y-2][x].getCellStatus()){
                        localCount++;
                        if(player==board[y-3][x].getCellStatus()){
                            localCount++;
                        }            
                    }
                }
                break;
            case RIGHT:
                if(player==board[y][x+1].getCellStatus()){
                    localCount++;
                    if(player==board[y][x+2].getCellStatus()){
                        localCount++;
                        if(player==board[y][x+3].getCellStatus()){
                            localCount++;
                        }            
                    }
                }                
                break;
            case LEFT:
                if(player==board[y][x-1].getCellStatus()){
                    localCount++;
                    if(player==board[y][x-2].getCellStatus()){
                        localCount++;
                        if(player==board[y][x-3].getCellStatus()){
                            localCount++;
                        }                            
                    }
                }                
                break;
            case DIAGONAL_RIGHT:
                if(player==board[y-1][x+1].getCellStatus()){
                    localCount++;
                    if(player==board[y-2][x+2].getCellStatus()){
                        localCount++;
                        if(player==board[y-3][x+3].getCellStatus()){
                            localCount++;
                        }            
                    }
                }                
                break;
            case DIAGONAL_LEFT:
                if(player==board[y-1][x-1].getCellStatus()){
                    localCount++;
                    if(player==board[y-2][x-2].getCellStatus()){
                        localCount++;
                        if(player==board[y-3][x-3].getCellStatus()){
                            localCount++;
                        }            
                    }
                }                
                break;
            default:
                localCount=-1;
        }
        
        return localCount;
    }
    
    private int[] countConsecutives(int y, int x, CellStatus player){
        int[] consecutives = new int[]{0,0,0,0,0,0,0,0};
        int localCount=0;
        CellStatus opponent = player==CellStatus.REDIN?CellStatus.PURPLEIN:CellStatus.REDIN;
        //skip downwards
        if(y>2){
            //upwards
            consecutives[countConsecitivesCase(y, x, player, PossibleMoves.UP)]++;
            consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.UP)+4]++;
            
            //skip diagonals left and left
            if(x<3){
                //right diagonal                
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.DIAGONAL_RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.DIAGONAL_RIGHT)+4]++;

                //right
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.RIGHT)+4]++;
            } else if(x==3){ //all
                //right diagonal
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.DIAGONAL_RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.DIAGONAL_RIGHT)+4]++;
                //right
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.RIGHT)+4]++;

                //left diagonal
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.DIAGONAL_LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.DIAGONAL_LEFT)+4]++;

                //left
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.LEFT)+4]++;
            }             
            else{ //skip diagonals right and right
                //left diagonal
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.DIAGONAL_LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.DIAGONAL_LEFT)+4]++;

                //left
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.LEFT)+4]++;
            }
        } else{ //skip upwards and downwards, only laterals
            if(x<3){ //skip left
                //right
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.RIGHT)+4]++;

            } else if(x==3){ //left and right
                //right
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.RIGHT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.RIGHT)+4]++;

                //left
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.LEFT)+4]++;
            } 
            else{ //skip right
                //left
                consecutives[countConsecitivesCase(y, x, player, PossibleMoves.LEFT)]++;
                consecutives[countConsecitivesCase(y, x, opponent, PossibleMoves.LEFT)+4]++;
            }            
        }        
        
        return consecutives;
    }

    public boolean play(int playedColumn){
        for(int y=5;y>=0;y--){
            if(!board[y][playedColumn].isPlayed()){
                board[y][playedColumn].setCellStatusPlayed(player);
                break;
            }
        }        
        return true;
    }
            
    public int getColumn(){
        return this.column;
    }

    public int getGrade(){
        return grade;
    }
    
    @Override
    public int compareTo(Object o) {        
        int grade = this.getGrade();
        int gradeO = ((BoardCandidate)o).getGrade();
        
        if(grade==gradeO){
            return (int)(Math.random()*10-Math.random()*10);
        }
        return gradeO-grade;
    }
    
/*    @Override
    public boolean equals(Object o){        
        if(this.getStatusGrade(player)!=((Connect4BoardCandidate)o).getStatusGrade(player)){
            return false;
        }
        return false;
    
    }
    */
}
