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

    private boolean placeStage;

    public Game(boolean isPlayerOneBlack, String playerOneName, String playerTwoName) {
        moves = new ArrayList<>();
        playerOne = new Player(playerOneName, isPlayerOneBlack);
        playerOneTurn = !isPlayerOneBlack; //Player with white start
        playerTwo = new Player(playerTwoName, !isPlayerOneBlack);
        gameBoard = new GameBoard();
        placeStage = true;
    }

    public void reset() {
        moves = new ArrayList<>();
        playerOne = new Player(playerOne.getName(), playerOne.isBlack());
        playerOneTurn = !playerOne.isBlack();
        playerTwo = new Player(playerTwo.getName(), playerTwo.isBlack());
        gameBoard = new GameBoard();
        placeStage = true;
    }

    public ArrayList<Position> getFreePos() {
        return (ArrayList<Position>) gameBoard.getEmptyPos().clone();
    }

    public ArrayList<Piece> getPlayerPieces(Player playerPieces) {
        return playerPieces.getPieces();
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public boolean getPlaceStage() {
        return placeStage;
    }

    public boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    public void placePiece(int pieceId, String newPos) {
        if (playerOneTurn) {
            playerOneTurn = false;
            gameBoard.addPiece(playerOne.placePiece(pieceId, newPos));
        } else {
            playerOneTurn = true;
            gameBoard.addPiece(playerTwo.placePiece(pieceId, newPos));
        }
    }

    public void placePiece(int pieceId, Position newPos) {
        if (playerOneTurn) {
            playerOneTurn = false;
            gameBoard.addPiece(playerOne.placePiece(pieceId, newPos));
        } else {
            playerOneTurn = true;
            gameBoard.addPiece(playerTwo.placePiece(pieceId, newPos));
        }
    }

    public void movePiece(int pieceId, Position newPos) {
        gameBoard.movePiece(pieceId, newPos);
    }

    public String toString() {
        String info = "";
        info += playerOne.toString() + "\n" + playerTwo.toString() + "\n" + gameBoard.toString();
        return info;
    }
}
