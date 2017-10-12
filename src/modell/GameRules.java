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
public class GameRules {

    private boolean placeStage;

    public GameRules() {

    }

    private boolean isPlaceStage(ArrayList<Piece> playerOnePieces, ArrayList<Piece> playerTwoPieces) {
        return playerOnePieces.size() > 0 && playerTwoPieces.size() > 0;
    }

    public ArrayList<Position> availablePosition(Piece chosedPiece, ArrayList<Piece> boardPieces) {
        
            for (int i = 0; i < boardPieces.size(); i++) {

            }
            return null;
    }

    public boolean isItPlayerOneTurn() {
        return true;
    }
}
