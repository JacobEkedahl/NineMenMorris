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
public class GameBoard {

    private ArrayList<Piece> boardPieces;

    public GameBoard() {
        boardPieces = new ArrayList<>();
    }

    public void addPiece(Piece pieceFromPlayer) {
        boardPieces.add(pieceFromPlayer);
    }

    public void removePiece(int idNumber) { //needs to be handle if idNumber is incorrect
        boardPieces.remove(getPieceInList(idNumber));
    }

    public void movePiece(int pieceId, Position newPos) {
        for (int i = 0; i < boardPieces.size(); i++) {
            if (boardPieces.get(i).isIdEqual(pieceId)) {
                boardPieces.get(i).setPos(newPos);
                return;
            }
        }
    }

    private Piece getPieceInList(int idNumber) {
        for (Piece p : boardPieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }
        return null; //needs to handle this
    }
    
    @Override
    public String toString() {
        String info = "Number of pieces: " + boardPieces.size() + "\n";

        for (int i = 0; i < boardPieces.size(); i++) {
            info += boardPieces.get(i).toString();
        } 
        return info;
    }
}
