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

    /**
     * Reinitializes all data members for a new game
     */
    public void reset() {
        moves = new ArrayList<>();
        playerOne = new HumanPlayer(playerOne.getName(), playerOne.isBlack());
        playerOneTurn = !playerOne.isBlack();
        playerTwo = new HumanPlayer(playerTwo.getName(), playerTwo.isBlack());
        gameBoard = new GameBoard();
        placeStage = true;
        gameRunning = true;
    }

    /**
     * 
     * @return if game is still running
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Sets the game as over
     */
    public void gameOver() {
        gameRunning = false;
    }

    /**
     * 
     * @return empty positions in gameBoard
     */
    public ArrayList<Position> getFreePos() {
        return (ArrayList<Position>) gameBoard.getEmptyPos().clone();
    }

    /**
     * 
     * @param selectedPiece
     * @return possible moves for selected piece
     */
    public ArrayList<Position> getOption(Piece selectedPiece) {
        return gameRules.getOptionMove(gameBoard.getEmptyPos(), selectedPiece);
    }
    
    /**
     * 
     * @param playerPieces
     * @return selected players pieces
     */
    public ArrayList<Piece> getPlayerPieces(HumanPlayer playerPieces) {
        return playerPieces.getPieces();
    }
    
    /**
     * 
     * @param player
     * @return Selected players pieces that's been placed on the board
     */
    public ArrayList<Piece> getPlayerPiecesFromBoard(HumanPlayer player) {
        return gameBoard.getPlayerPieces(player);
    }

    /**
     * 
     * @return All pieces that's been placed on the board
     */
    public ArrayList<Piece> getGameBoardPieces() {
        return gameBoard.getPieces();
    }

    /**
     * 
     * @return the player whose turn it is
     */
    public HumanPlayer getCurrentPlayer() {
        if (playerOneTurn) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    /**
     * 
     * @return true if not all pieces has been placed on the board
     */
    public boolean getPlaceStage() {
        return placeStage;
    }

    /**
     * 
     * @return true if its player ones turn otherwise false
     */
    public boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    /**
     * Place a new piece with selected id at selected position in form of string
     * @param pieceId
     * @param newPos 
     */
    public void placePiece(int pieceId, String newPos) {
        gameBoard.addPiece(getCurrentPlayer().placePiece(pieceId, newPos));
        updatePlaceStage();
    }
    
    /**
     * checks if players have exited the placing phase
     */
    private void updatePlaceStage() {
        if (playerOne.getNoOfPieces() + playerTwo.getNoOfPieces() == 0)
            placeStage = false;
        else {
            placeStage = true;
        }
    }

    /**
     * removes selected piece from the game board
     * @param idNumber 
     */
    public void removePiece(int idNumber) {
        gameBoard.removePiece(idNumber);
    }

    /**
     * Checks if the currently selected player has won the game
     * @param otherPlayer
     * @param boardPieces
     * @param freePos
     * @return true if current player has won
     */
    public boolean haveCurrentPlayerWon(HumanPlayer otherPlayer, ArrayList<Piece> boardPieces, ArrayList<Position> freePos) {
        return gameRules.haveCurrentPlayerWon(getOtherPlayer(), gameBoard.getPieces(), gameBoard.getEmptyPos());
    }

    /**
     * Place a new piece with selected id at selected position in form of Position
     * @param pieceId
     * @param newPos 
     */
    public void placePiece(int pieceId, Position newPos) {
        gameBoard.addPiece(getCurrentPlayer().placePiece(pieceId, newPos));
    }

    /**
     * Checks if a mill has occurred with the last move
     * @param selectedPiece
     * @param gameBoardPieces
     * @return true if new mill's formed
     */
    public boolean isMill(Piece selectedPiece, ArrayList<Piece> gameBoardPieces) {
        return gameRules.newMill(selectedPiece, gameBoardPieces);
    }

    /**
     * 
     * @return player whose turn it is not
     */
    public HumanPlayer getOtherPlayer() {
        if (playerOneTurn) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    /**
     * 
     * @return pieces of the player whose turn it is not
     */
    public ArrayList<Piece> getOtherPlayerPieces() {
        return gameBoard.getPlayerPieces(getOtherPlayer());
    }

    /**
     * Moves selected piece to the selected new position
     * @param pieceId
     * @param newPos 
     */
    public void movePiece(int pieceId, Position newPos) {
        gameBoard.movePiece(pieceId, newPos);
    }

    /**
     * Changes whose turn it is
     */
    public void changePlayerTurn() {
        playerOneTurn = !playerOneTurn;
    }
    
    /**
     * 
     * @return a string with information for player one, two and the gameBoard
     */
    public String toString() {
        String info = "";
        info += playerOne.toString() + "\n" + playerTwo.toString() + "\n" + gameBoard.toString();
        return info;
    }
}
