/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class Piece {
    private Position pos;
    private boolean black;
    private final int id;
    
    /**
     * Parameterless constructor sets pieces to white and pos to nopos and id
     * to zero
     */
    public Piece() {
        this(false, Position.NOPOS, 0);
    }
    /**
     * Constructor for a piece where you can set the parameters
     * @param black
     * @param pos
     * @param id 
     */
    public Piece(boolean black, Position pos, int id) {
        this.black = black;
        this.pos = pos;
        this.id = id;
    }
    
    /**
     * 
     * @return the piece id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Sets the color of the piece
     * @param black 
     */
    public void setBlack(boolean black) {
        this.black = black;
    }
    
    /**
     * 
     * @return true if piece has color black
     */
    public boolean isBlack() {
        return this.black;
    }
    
    /**
     * Set the position for this piece
     * @param pos 
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    /**
     * 
     * @return 
     */
    public Position getPos() {
        return this.pos;
    }
    
    /**
     * Check if this position is the same as the parameters piece 
     * @param otherPos
     * @return true if the positions are the same one
     */
    public boolean isPosEqual(Position otherPos) {
        return this.pos == otherPos;
    }
    
    /**
     * Check if this id is the same as the parameters piece
     * @param id
     * @return true if the id are the same one
     */
    public boolean isIdEqual(int id) {
        return this.id == id;
    }
    
    /**
     * Checks if this piece has the same color as the parameter
     * @param otherBlack
     * @return returns true if the boolean color is the same
     */
    public boolean isBlackEqual(boolean otherBlack) {
        return this.black == otherBlack;
    }
    
    @Override
    public String toString() {
        String info;
        info = "Piece info (ID: " + this.id + ")\n" + "Is black: " + black + " Position: " + pos;
        return info;
    }
}
