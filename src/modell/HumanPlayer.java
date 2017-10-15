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
public class HumanPlayer extends Player {

    
    public HumanPlayer(String name, boolean black) {
        super(name,black);
    }

    /**
     * Place a piece on the board, removes it from player and add it to the
     * GameBoard
     *
     * @param pieceId
     * @param newPos
     * @return
     */
    @Override
    public Piece placePiece(int pieceId, Position newPos) {
        Piece pieceForBoard;

        for (int i = 0; i < super.getPieces().size(); i++) {
            if (super.getPieces().get(i).isIdEqual(pieceId)) {
                super.getPieces().get(i).setPos(newPos);
                pieceForBoard = super.getPieces().get(i);
                super.getPieces().remove(i);
                return pieceForBoard;
            }
        }
        System.out.println("Return null piece");
        return null;
    }

    @Override
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

    @Override
    public Piece placePiece() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
