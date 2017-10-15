/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
abstract public class Player {
    private ArrayList<Piece> pieces;
    private String name;
    private boolean black;
    
    /**
     *
     * @param pieceId
     * @param newPos
     */
    public Player(String name, boolean black){
        this.name=name;
        this.black=black;
        pieces = new ArrayList<>();

        int idNumber = 0;
        if (black == true) {
            idNumber = 9;
        }

        for (int i = 0; i < 9; i++) {
            pieces.add(new Piece(black, Position.NOPOS, i + idNumber));
        }
    }

    abstract public Piece placePiece(int pieceId, Position newPos);
    
    abstract public Piece placePiece(int pieceId, String newPos);
    
    public Piece choosePiece(int pieceId){
        for (Piece p : pieces) {
            if (p.isIdEqual(pieceId)) {
                return p;
            }
        }
        return null; //make sure that this can be handled in from where it has been called from
    }
    
    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>) pieces.clone();
    }
    
    public int getNoOfPieces() {
        return pieces.size();
    }

    public boolean isBlack() {
        return this.black;
    }

    public String getName() {
        return this.name;
    }
    

    @Override
    public String toString() {
        String info;
        info = "Name: " + this.name + "noOfPieces: " + pieces.size() + " isBlack: " + this.black + "\n";
        for (int i = 0; i < pieces.size(); i++) {
            info += pieces.get(i).toString() + "\n";
        }
        return info;
    }
}
