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
    
    /**
     * Constructor that's Initializing the boards pieces,
     * and the free positions on the board.
     */
    public GameBoard() {
        boardPieces = new ArrayList<>();
        freePosition = new ArrayList<Position>();
        for (Position p : Position.values()) {
            freePosition.add(p);
        }
        freePosition.remove(Position.NOPOS);
    }
    
    /**
     * Takes a player as parameter and returns all the pieces that player
     * has placed on the board
     * @param player
     * @return current pieces on board
     */
    public ArrayList<Piece> getPlayerPieces(HumanPlayer player) {
        ArrayList<Piece> playerPieces = new ArrayList<Piece>();
        for (Piece p: boardPieces) {
            if (p.isBlackEqual(player.isBlack())) {
                playerPieces.add(p);
            }
        }
        return playerPieces;
    }

    /**
     * Takes a piece from the player and adds it to the game board.
     * @param pieceFromPlayer 
     */
    public void addPiece(Piece pieceFromPlayer) {
        updateEmptyPos(false, pieceFromPlayer.getPos());
        boardPieces.add(pieceFromPlayer);
    }

    /**
     * Removes chosen piece after its id number
     * @param idNumber 
     */
    public void removePiece(int idNumber) { //needs to be handle if idNumber is incorrect
        updateEmptyPos(true, getPieceInList(idNumber).getPos());
        boardPieces.remove(getPieceInList(idNumber));
    }

    /**
     * Moves a piece to selected new position
     * @param pieceId id for piece to to move
     * @param newPos new position for selected piece
     */
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

    /**
     * 
     * @return a clone of all pieces on the board
     */
    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>) boardPieces.clone();
    }

    /**
     * 
     * @return a clone of all free positions on the board
     */
    public ArrayList<Position> getEmptyPos() { 
        return (ArrayList<Position>) freePosition.clone();
    }
    
    public ArrayList<String> getEmptyPosString() {
        ArrayList<String> tempPos = new ArrayList<>();
        for (Position p: freePosition) {
            tempPos.add(p.name());
        }
        return tempPos;
    }

    /**
     * Goes through all the pieces to find the piece with selected id number
     * @param idNumber
     * @return null if the piece is not on board else it returns the piece
     */
    private Piece getPieceInList(int idNumber) {
        for (Piece p : boardPieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }
        return null; //needs to handle this
    }

    /**
     * Adds or remove position from the list of free positions
     * @param add true for add false for remove
     * @param pos 
     */
    private void updateEmptyPos(boolean add, Position pos) {
        if (add) {
            freePosition.add(pos);
        } else {
            freePosition.remove(pos);
        }
    }

    /**
     * 
     * @return how many pieces are on the board, and the info from pieces in
     * form of string, it also returns all free positions
     */
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
