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
        GameRules gameRules = new GameRules();

        HumanPlayer playerOne = new HumanPlayer("Player one", false);
        HumanPlayer playerTwo = new HumanPlayer("Player two", true);

        for (int i = 10; i < 17; i++) {
            playerTwo.placePiece(i, Position.A1);
        }

        if (gameRules.haveCurrentPlayerWon(playerTwo, gameBoard.getPieces(), gameBoard.getEmptyPos())) //   gameBoard.addPiece(playerOne.placePiece(0, Position.A1));
        {
            System.out.println("Player one has won");
        } else {
            System.out.println("Player one has not won yet");
        }
        
        System.exit(0);

        ArrayList<String> getClosePos = new ArrayList<>();
        getClosePos = gameRules.getAdjPos("F2");
        for (String s : getClosePos) {
            System.out.println(s + " - ");
        }

        System.out.println("\n\n");

        Piece selectedPiece = new Piece(false, Position.A7, 4);

        Piece pieceForMill1 = new Piece(false, Position.G7, 5);
        Piece pieceForMill2 = new Piece(false, Position.D7, 2);

        gameBoard.addPiece(pieceForMill1);
        gameBoard.addPiece(pieceForMill2);
        gameBoard.addPiece(selectedPiece);

        if (gameRules.newMill(selectedPiece, gameBoard.getPieces())) {
            System.out.println("A new mill!");
        } else {
            System.out.println("No mill!");
        }
        //boolean placeStage, ArrayList<Piece> boardPieces
    }
}
