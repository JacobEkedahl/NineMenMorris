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
    private HumanPlayer playerOne;
    private HumanPlayer playerTwo;
    private GameBoard gameBoard;
    private boolean gameRunning;
    private GameRules gameRules;

    private boolean placeStage;

    public Game(boolean isPlayerOneBlack, String playerOneName, String playerTwoName) {
        moves = new ArrayList<>();
        playerOne = new HumanPlayer(playerOneName, isPlayerOneBlack);
        playerOneTurn = !isPlayerOneBlack; //Player with white start
        playerTwo = new HumanPlayer(playerTwoName, !isPlayerOneBlack);
        gameBoard = new GameBoard();
        placeStage = true;
        gameRunning = true;
        gameRules = new GameRules();
    }

    public void reset() {
        moves = new ArrayList<>();
        playerOne = new HumanPlayer(playerOne.getName(), playerOne.isBlack());
        playerOneTurn = !playerOne.isBlack();
        playerTwo = new HumanPlayer(playerTwo.getName(), playerTwo.isBlack());
        gameBoard = new GameBoard();
        placeStage = true;
        gameRunning = true;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void gameOver() {
        gameRunning = false;
    }

    public ArrayList<Position> getFreePos() {
        return (ArrayList<Position>) gameBoard.getEmptyPos().clone();
    }

    public ArrayList<Position> getOption(Piece selectedPiece) {
        return gameRules.getOptionMove(gameBoard.getEmptyPos(), selectedPiece);
    }
    public ArrayList<Piece> getPlayerPieces(HumanPlayer playerPieces) {
        return playerPieces.getPieces();
    }
    
    public ArrayList<Piece> getPlayerPiecesFromBoard(HumanPlayer player) {
        return gameBoard.getPlayerPieces(player);
    }

    public ArrayList<Piece> getGameBoardPieces() {
        return gameBoard.getPieces();
    }

    public HumanPlayer getCurrentPlayer() {
        if (playerOneTurn) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public boolean getPlaceStage() {
        return placeStage;
    }

    public boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    public void placePiece(int pieceId, String newPos) {
        //if the OtherPlayer has less then 3 pieces then return 0 = GameOver Current HumanPlayer Won
        //if currentPlayer has no possible moves return 1 = GameOver Other player lost
        gameBoard.addPiece(getCurrentPlayer().placePiece(pieceId, newPos));
        //if currentPlayer has a new mill then return 2 = CurrentPlayer gets to remove piece
        //else updatePlayerTurn
        updatePlaceStage();
        //changePlayerTurn();
    }
    
    private void updatePlaceStage() {
        if (playerOne.getNoOfPieces() + playerTwo.getNoOfPieces() == 0)
            placeStage = false;
        else {
            placeStage = true;
        }
    }

    public void removePiece(int idNumber) {
        gameBoard.removePiece(idNumber);
    }

    public boolean haveCurrentPlayerWon(HumanPlayer otherPlayer, ArrayList<Piece> boardPieces, ArrayList<Position> freePos) {
        return gameRules.haveCurrentPlayerWon(getOtherPlayer(), gameBoard.getPieces(), gameBoard.getEmptyPos());
    }

    public void placePiece(int pieceId, Position newPos) {
        gameBoard.addPiece(getCurrentPlayer().placePiece(pieceId, newPos));
    }

    public boolean isMill(Piece selectedPiece, ArrayList<Piece> gameBoardPieces) {
        return gameRules.newMill(selectedPiece, gameBoardPieces);
    }

    public HumanPlayer getOtherPlayer() {
        if (playerOneTurn) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public ArrayList<Piece> getOtherPlayerPieces() {
        return gameBoard.getPlayerPieces(getOtherPlayer());
    }

    public void movePiece(int pieceId, Position newPos) {
        gameBoard.movePiece(pieceId, newPos);
    }

    public void changePlayerTurn() {
        playerOneTurn = !playerOneTurn;
    }

    public String toString() {
        String info = "";
        info += playerOne.toString() + "\n" + playerTwo.toString() + "\n" + gameBoard.toString();
        return info;
    }
}
