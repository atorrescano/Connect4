/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect4.java;

import javax.swing.ImageIcon;

/**
 * This class represents the different status that a cell could have for the game of Connect4.
 * 
 * It also provides the related images for them, pulled out from the resources.
 * 
 * @author Aaron Torrescano
 */
public enum CellStatus {
    /**
     * An empty cell within the board
     */
    EMPTY("/com/connect4/java/ui/images/Empty.png"), 
    /**
     * An empty cell within on top of the board (aka the turn band)
     */
    EMPTYOUT("/com/connect4/java/ui/images/EmptyOut.png"), 
    /**
     * A red chip inside the board
     */
    REDIN("/com/connect4/java/ui/images/RedChipIn.png"), 
    /**
     * A purple chip inside the board
     */
    PURPLEIN("/com/connect4/java/ui/images/PurpleChipIn.png"),
    /**
     * A red chip on top of the board, at the turn band
     */
    REDOUT("/com/connect4/java/ui/images/RedChipOut.png"),
    /**
     * A purple chip on top of the board, at the turn band
     */
    PURPLEOUT("/com/connect4/java/ui/images/PurpleChipOut.png");
    
    private final String getImage;
    
    private CellStatus(String getImage){
        this.getImage = getImage;
    }
    
    /**
     * Method that returns a new image for the cell.
     * 
     *@return The corresponding <code>ImageIcon</code>
     */
    public ImageIcon getImage(){
        return new ImageIcon(getClass().getResource(this.getImage));
    }
}
