/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import modell.*;

/**
 *
 * @author Jacob
 */
public class GameBoardTest {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        HumanPlayer playerOne = new HumanPlayer("Player One", false);
        
        gameBoard.addPiece(playerOne.placePiece(0, Position.A1));
        System.out.println(gameBoard.toString());
        
        gameBoard.movePiece(0, Position.B4);
        System.out.println(gameBoard.toString());
        
        gameBoard.removePiece(0);
        System.out.println(gameBoard.toString());
                
    }
}
