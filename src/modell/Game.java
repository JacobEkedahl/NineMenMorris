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

    private Piece selectedPiece;
    private Position selectedPos;
    private GameState gameState;

    public Game(boolean isPlayerOneBlack, String playerOneName, String playerTwoName) {
        moves = new ArrayList<>();
        if (!isPlayerOneBlack) { //playerOne white
            playerOne = new HumanPlayer(playerOneName, isPlayerOneBlack);
            playerTwo = new HumanPlayer(playerTwoName, !isPlayerOneBlack);
        } else {
            playerOne = new HumanPlayer(playerTwoName, isPlayerOneBlack);
            playerTwo = new HumanPlayer(playerOneName, !isPlayerOneBlack);
        }
        playerOneTurn = !isPlayerOneBlack; //Player with white start
        gameBoard = new GameBoard();
        placeStage = true;
        gameRunning = true;
        gameRules = new GameRules();

        selectedPiece = new Piece();
        selectedPos = Position.NOPOS;
        gameState = new GameState();
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

    public void setSelectedPiece(int pieceId) {
        selectedPiece = getPieceInList(pieceId);
    }

    public String getSelectedPieceID() {
        return Integer.toString(selectedPiece.getId());
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPosition(String pos) {
        selectedPos = Position.valueOf(pos);
    }

    public String getSelectedPosition() {
        return selectedPos.name();
    }

    public int getState() {
        return gameState.getState();
    }

    public void next() {
        gameState.next();
    }

    public void again() {
        gameState.zero();
        playerOneTurn = !playerOneTurn;
    }

    public void over() {
        gameState.over();
    }

    private Piece getPieceInList(int idNumber) {
        ArrayList<Piece> boardPieces = gameBoard.getPieces();
        ArrayList<Piece> playerOnePieces = playerOne.getPieces();
        ArrayList<Piece> playerTwoPieces = playerTwo.getPieces();

        for (Piece p : boardPieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }

        for (Piece p : playerOnePieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }

        for (Piece p : playerTwoPieces) {
            if (p.isIdEqual(idNumber)) {
                return p;
            }
        }
        return null; //needs to handle this
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
     * @param selectedPiece
     * @return possible moves for selected piece
     */
    public ArrayList<String> getOption(Piece selectedPiece) {
        if (placeStage == true) {
            return (ArrayList<String>) gameBoard.getEmptyPosString().clone();
        } else {
            return gameRules.getOptionMove(gameBoard.getEmptyPos(), selectedPiece);
        }
    }

    /**
     *
     * @param playerPieces
     * @return selected players pieces
     */
    public ArrayList<Piece> getPlayerPieces(HumanPlayer playerPieces) {
        return playerPieces.getPieces();
    }

    public String getPlayerOneName() {
        return playerOne.getName();
    }

    public String getPlayerTwoName() {
        return playerTwo.getName();
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
     *
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
        if (playerOne.getNoOfPieces() + playerTwo.getNoOfPieces() == 0) {
            placeStage = false;
        } else {
            placeStage = true;
        }
    }

    /**
     * removes selected piece from the game board
     *
     * @param idNumber
     */
    public void removePiece(int idNumber) {
        gameBoard.removePiece(idNumber);
    }

    /**
     * Checks if the currently selected player has won the game
     *
     * @param otherPlayer
     * @param boardPieces
     * @param freePos
     * @return true if current player has won
     */
    public boolean haveCurrentPlayerWon() {
        return gameRules.haveCurrentPlayerWon(getOtherPlayer(), gameBoard.getPieces(), gameBoard.getEmptyPos());
    }

    /**
     * Place a new piece with selected id at selected position in form of
     * Position
     *
     * @param pieceId
     * @param newPos
     */
    public void placePiece(int pieceId, Position newPos) {
        gameBoard.addPiece(getCurrentPlayer().placePiece(pieceId, newPos));
    }

    /**
     * Checks if a mill has occurred with the last move
     *
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
    
    public Position getNoPos() {
        Position p = Position.NOPOS;
        return p;
    }
    
    public ArrayList<String> piecesToRemove() {
        return gameRules.piecesAbleToRemove(gameBoard.getPieces(), getOtherPlayerPieces());
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
     *
     * @param pieceId
     * @param newPos
     */
    public void movePiece(int pieceId, Position newPos) {
        gameBoard.movePiece(pieceId, newPos);
    }

    public void movePiece(int pieceId, String newPos) {
        gameBoard.movePiece(pieceId, Position.valueOf(newPos));
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
