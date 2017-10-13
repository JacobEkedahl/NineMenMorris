/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.util.ArrayList;
import modell.*;

/**
 *
 * @author Jacob
 */
public class GameRulesTest {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        Player playerOne = new Player("Player one", false);
        gameBoard.addPiece(playerOne.placePiece(0, Position.A1));

        GameRules gameRules = new GameRules(true, gameBoard.getPieces());
        System.out.println(gameRules.getPos(4));

        ArrayList<String> getClosePos = new ArrayList<>();
        getClosePos = gameRules.getAdjPos("F2");
        for (String s : getClosePos) {
            System.out.println(s + " - ");
        }
        
        System.out.println("\n\n");

        Piece selectedPiece = new Piece(false, Position.A4, 4);
        ArrayList<Position> options = gameRules.getOptionMove(gameBoard.getEmptyPos(), selectedPiece);
        
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i)+ " - ");
        }
        //boolean placeStage, ArrayList<Piece> boardPieces
    }
}
