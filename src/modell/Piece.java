/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

/**
 *
 * @author Jacob
 */
public class Piece {
    private Position pos;
    private boolean black;
    
    public Piece(boolean black, Position pos) {
        this.black = black;
        this.pos = pos;
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
        if (this.pos == otherPos) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isBlackEqual(boolean otherBlack) {
        if (this.black == otherBlack) {
            return true;
        } else {
            return false;
        }
    }
    
    public String toString() {
        String info;
        info = "Piece info \n" + "Is black: " + black + " Position: " + pos;
        return info;
    }
}
