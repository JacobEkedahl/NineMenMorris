/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class GameBoard {

    private ArrayList<Piece> boardPieces;
    private ArrayList<Position> freePosition;

    public GameBoard() {
        boardPieces = new ArrayList<>();
        freePosition = new ArrayList<Position>();
        for (Position p : Position.values()) {
            freePosition.add(p);
        }
        freePosition.remove(Position.NOPOS);
    }
    
    public ArrayList<Piece> getPlayerPieces(Player player) {
        ArrayList<Piece> playerPieces = new ArrayList<Piece>();
        for (Piece p: boardPieces) {
            if (p.isBlackEqual(player.isBlack())) {
                playerPieces.add(p);
            }
        }
        return playerPieces;
    }

    public void addPiece(Piece pieceFromPlayer) {
        updateEmptyPos(false, pieceFromPlayer.getPos());
        boardPieces.add(pieceFromPlayer);
    }

    public void removePiece(int idNumber) { //needs to be handle if idNumber is incorrect
        updateEmptyPos(true, getPieceInList(idNumber).getPos());
        boardPieces.remove(getPieceInList(idNumber));
    }

    public void movePiece(int pieceId, Position newPos) {
        for (int i = 0; i < boardPieces.size(); i++) {
            if (boardPieces.get(i).isIdEqual(pieceId)) {
                updateEmptyPos(true, boardPieces.get(i).getPos());
                boardPieces.get(i).setPos(newPos);
                updateEmptyPos(false, newPos);
                return;
            }
        }
    }

    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>) boardPieces.clone();
    }

    public ArrayList<Position> getEmptyPos() {
        return freePosition;
    }

    private Piece getPieceInList(int idNumber) {
        for (Piece p : boardPieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }
        return null; //needs to handle this
    }

    private void updateEmptyPos(boolean add, Position pos) {
        if (add) {
            freePosition.add(pos);
        } else {
            freePosition.remove(pos);
        }
    }

    @Override
    public String toString() {
        String info = "Number of pieces: " + boardPieces.size() + "\n";

        for (int i = 0; i < boardPieces.size(); i++) {
            info += boardPieces.get(i).toString();
        }

        info += "\nFree pos \n";
        for (int i = 0; i < freePosition.size(); i++) {
            info += freePosition.get(i).name() + " - ";
        }
        return info;
    }
}
