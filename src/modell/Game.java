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
public class Game {

    private ArrayList<Piece> moves;
    private boolean playerOneTurn;
    private Player playerOne;
    private Player playerTwo;
    private GameBoard gameBoard;

    public Game(boolean isPlayerOneBlack, String playerOneName, String playerTwoName) {
        moves = new ArrayList<>();
        playerOne = new Player(playerOneName, isPlayerOneBlack);
        playerOneTurn = !isPlayerOneBlack;
        playerTwo = new Player(playerTwoName, !isPlayerOneBlack);
        gameBoard = new GameBoard();
    }

    public void reset() {
        moves = new ArrayList<>();
        playerOne = new Player(playerOne.getName(), playerOne.isBlack());
        playerOneTurn = !playerOne.isBlack();
        playerTwo = new Player(playerTwo.getName(), playerTwo.isBlack());
        gameBoard = new GameBoard();
    }
    

    public String toString() {
        String info = "";
        info += playerOne.toString() + "\n" + playerTwo.toString() + "\n" + gameBoard.toString();
        return info;
    }
}
