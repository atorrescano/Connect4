/*
 * The MIT License
 *
 * Copyright 2018 Aaron.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.connect4.java;

import java.util.TreeSet;

/** This class encapsulates a Node in a multilevel tree of <code>BoardCandidate</code> items. 
 * The idea is that you could create multilevel trees.
 *
 * @author Aaron Torrescano
 */
public class DecisionTreeNode<E extends BoardCandidate> extends TreeSet implements Comparable {
    private DecisionTreeNode<E> parent= null;
    private DecisionTreeNode<E> children = null;
    private int level;
    private CellStatus player;
    private E e;
    
    public DecisionTreeNode(int level, CellStatus player, DecisionTreeNode<E> parent){
        super();
        this.parent=parent;
        this.level = level;
        this.player = player;
        children = new DecisionTreeNode<E>();
    }            
    
    public DecisionTreeNode(){
        super();
    }
    
    public boolean add(E e){
        this.e =e;
        return super.add(e);
    }
    
    public void addChild(DecisionTreeNode<E> child){
        children.add(child);
    }
    
    public boolean isColumnPlayed(int column){
        return e.isColumnPlayed(column);
    }
    
    public E getCandidate(){
        return e;
    }

    @Override
    public int compareTo(Object o) {
        return e.compareTo(((DecisionTreeNode)o).e);
    }
    
    public int getColumnToPlay(int maxLevel){
        int maxGrade=Integer.MIN_VALUE;
        int minGrade=Integer.MAX_VALUE;
        int tempGrade=0;
        int selectedColumn=0;
        
        if(maxLevel==1){
            return ((DecisionTreeNode)children.last()).e.getColumn();
        }
        
        //root level
        if(level==-1){
            for(Object i : children){
                DecisionTreeNode<BoardCandidate> temp = ((DecisionTreeNode)(i));
                tempGrade = temp.getColumnToPlay(maxLevel);
                if(tempGrade>maxGrade){
                    maxGrade=tempGrade;
                    selectedColumn=temp.getCandidate().getColumn();
                }
            }
            System.out.println(";("+level+","+selectedColumn+","+maxGrade+")");            
            return selectedColumn;
        }
        //last level with children
        else if(maxLevel-level==2){ 
            DecisionTreeNode<BoardCandidate> temp = null;
            //It means the children refere to the PURPLEIN
            if(player==CellStatus.REDIN){
                temp = ((DecisionTreeNode)((DecisionTreeNode)children.last()));                
            }else{
                temp = ((DecisionTreeNode)((DecisionTreeNode)children.first()));
            }
            tempGrade = e.getGrade()+temp.getCandidate().getGrade();
            selectedColumn = temp.getCandidate().getColumn();
            System.out.print(";("+level+","+selectedColumn+","+tempGrade+")");                
            return tempGrade;
        }
        //intermidiate level
        else {            
            //maximun value
            if(level%2!=0){
                for(Object i : children){
                    DecisionTreeNode<BoardCandidate> temp = ((DecisionTreeNode)(i));
                    tempGrade = temp.getColumnToPlay(maxLevel);
                    if(tempGrade>maxGrade){
                        maxGrade=tempGrade;
                        selectedColumn=temp.getCandidate().getColumn();
                    }
                }
                tempGrade=maxGrade;
            }
            //minimum value
            else{
                for(Object i : children){
                    DecisionTreeNode<BoardCandidate> temp = ((DecisionTreeNode)(i));
                    tempGrade = temp.getColumnToPlay(maxLevel);
                    if(tempGrade<minGrade){
                        selectedColumn=temp.getCandidate().getColumn();
                        minGrade=tempGrade;
                    }
                }
                tempGrade=minGrade;
            }
            System.out.print(";("+level+","+selectedColumn+","+tempGrade+")");                
            return tempGrade+e.getGrade();
        }        
    }
    
    @Override
    public String toString(){
        String temp="";
        if(level>=0){
            temp = "Level:"+level+"\tPlayer:"+player+"\t";
        }
        String tabs="";
        for(int i=0;i<=level;i++){
            tabs+="|\t";
        }
        /*if(e!=null){
            temp+=e.toString()+"\n";
        } */
        if(e!=null){
            temp+=tabs+e.getGrade()+"("+e.getColumn()+")\n";
        }
        if(children!=null && children.size()>0){
            for(Object o : children){
                temp+=o.toString();
            }            
        }
        return temp;
    }
}
