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
    
    public Piece(boolean black, Position pos, int id) {
        this.black = black;
        this.pos = pos;
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setBlack(boolean black) {
        this.black = black;
    }
    
    public boolean isBlack() {
        return this.black;
    }
    
    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    public Position getPos() {
        return this.pos;
    }
    
    public boolean isPosEqual(Position otherPos) {
        return this.pos == otherPos;
    }
    
    public boolean isIdEqual(int id) {
        return this.id == id;
    }
    
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
