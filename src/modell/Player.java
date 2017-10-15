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
public class Player {

    private final ArrayList<Piece> pieces;
    private final String name;
    private final boolean black;

    public Player(String name, boolean black) {
        this.name = name;
        this.black = black;
        pieces = new ArrayList<>();

        int idNumber = 0;
        if (black == true) {
            idNumber = 9;
        }

        for (int i = 0; i < 2; i++) {
            pieces.add(new Piece(black, Position.NOPOS, i + idNumber));
        }
    }

    /**
     * Place a piece on the board, removes it from player and add it to the
     * GameBoard
     *
     * @param pieceId
     * @param newPos
     * @return
     */
    public Piece placePiece(int pieceId, Position newPos) {
        Piece pieceForBoard;

        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).isIdEqual(pieceId)) {
                pieces.get(i).setPos(newPos);
                pieceForBoard = pieces.get(i);
                pieces.remove(i);
                return pieceForBoard;
            }
        }
        System.out.println("Return null piece");
        return null;
    }

    public Piece placePiece(int pieceId, String newPos) {
        Position stringToPos = Position.NOPOS;

        for (Position p : Position.values()) {
            if (p.name().equals(newPos)) {
                stringToPos = p;
                break;
            }
        }

        return placePiece(pieceId, stringToPos);
    }

    public boolean sameTeam(Piece p) {
        return p.isBlack() == this.black;
    }

    public Piece choosePiece(int pieceId) {
        for (Piece p : pieces) {
            if (p.isIdEqual(pieceId)) {
                return p;
            }
        }
        return null; //make sure that this can be handled in from where it has been called from
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

    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>) pieces.clone();
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
