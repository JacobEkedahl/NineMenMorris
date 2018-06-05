/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.util.ArrayList;
import java.util.Scanner;
import modell.*;

/**
 *
 * @author Tobias
 */
public class textUI {

    private static Scanner sc = new Scanner(System.in);

    private static void playerPlacePiece(Game game) {
        String colorPlayer = "";
        HumanPlayer player = (HumanPlayer) game.getCurrentPlayer();
        if (player.isBlack()) {
            colorPlayer = "Black";
        } else {
            colorPlayer = "White";
        }
        System.out.println("It is " + player.getName() + " turn!");
        ArrayList<Piece> playerOnePieces = new ArrayList<>();
        playerOnePieces = game.getCurrentPlayer().getPieces();
        for (int i = 0; i < playerOnePieces.size(); i++) {
            System.out.println("Piece ID: " + playerOnePieces.get(i).getId() + " - " + colorPlayer);
        }
        System.out.println(player.getName() + "Please select piece (INPUT ID): ");
        boolean validChoice = false;

        int selectedId = sc.nextInt();
        Piece selectPiece = new Piece();

        for (int i = 0; i < playerOnePieces.size(); i++) {
            if (playerOnePieces.get(i).getId() == selectedId) {
                validChoice = true;
                selectPiece = playerOnePieces.get(i);
            }
        }

        while (validChoice == false) {

            System.out.println("Incorrect ID!");
            selectedId = sc.nextInt();

            for (int i = 0; i < playerOnePieces.size(); i++) {
                if (playerOnePieces.get(i).getId() == selectedId) {
                    validChoice = true;
                    selectPiece = playerOnePieces.get(i);
                }
            }
        }

        ArrayList<String> freePos = game.getOption(selectPiece);
        System.out.println("Available position: ");
        for (int i = 0; i < freePos.size(); i++) {
            System.out.print(freePos.get(i) + " - ");
        }
        System.out.println("\n");

        System.out.println("Please chooce available square: ");
        String chosenPos = sc.next();

        game.placePiece(selectedId, chosenPos);
        System.out.println("Player placed piece: " + selectedId + "At pos: " + chosenPos);

        Piece p = new Piece(isPieceBlack(selectedId), stringToPos(chosenPos), selectedId);
        if (game.isMill(p)) {
            System.out.println("You have a mill, choose one of these pieces to remove:");
            ArrayList<Piece> otherPlayPieces = game.getOtherPlayerPieces();
            for (Piece otherPiece : otherPlayPieces) {
                System.out.println(otherPiece.toString());
            }

            int chosenPiece = sc.nextInt();
            game.removePiece(chosenPiece);
        }

        game.changePlayerTurn();
    }

    private static void playerMovePiece(Game game) {
        HumanPlayer player = (HumanPlayer) game.getCurrentPlayer();
        System.out.println("Move piece!!");
        String colorPlayer = "";
        if (player.isBlack()) {
            colorPlayer = "Black";
        } else {
            colorPlayer = "White";
        }
        System.out.println("It is " + player.getName() + " turn!");
        ArrayList<Piece> playerOnePieces = new ArrayList<>();
        playerOnePieces = game.getPlayerPiecesFromBoard(player);
        for (int i = 0; i < playerOnePieces.size(); i++) {
            System.out.println("Piece ID: " + playerOnePieces.get(i).getId() + " - " + colorPlayer);
        }
        System.out.println(player.getName() + "Please select piece (INPUT ID): ");
        boolean validChoice = false;
        Piece tempP = new Piece();

        int selectedId = sc.nextInt();

        for (int i = 0; i < playerOnePieces.size(); i++) {
            if (playerOnePieces.get(i).getId() == selectedId) {
                validChoice = true;
                tempP = playerOnePieces.get(i);
            }
        }

        while (validChoice == false) {

            System.out.println("Incorrect ID!");
            selectedId = sc.nextInt();

            for (int i = 0; i < playerOnePieces.size(); i++) {
                if (playerOnePieces.get(i).getId() == selectedId) {
                    validChoice = true;
                    tempP = playerOnePieces.get(i);
                }
            }
        }

        ArrayList<String> freePos = game.getOption(tempP);
        System.out.println("Available position: ");
        for (int i = 0; i < freePos.size(); i++) {
            System.out.print(freePos.get(i) + " - ");
        }
        System.out.println("\n");

        System.out.println("Please chooce available square: ");
        String chosenPos = sc.next();

        game.movePiece(selectedId, stringToPos(chosenPos));
        System.out.println("Player placed piece: " + selectedId + "At pos: " + chosenPos);

        Piece p = new Piece(isPieceBlack(selectedId), stringToPos(chosenPos), selectedId);
        if (game.isMill(p)) {
            System.out.println("You have a mill, choose one of these pieces to remove:");
            ArrayList<Piece> otherPlayPieces = game.getOtherPlayerPieces();
            for (Piece otherPiece : otherPlayPieces) {
                System.out.println(otherPiece.toString());
            }

            int chosenPiece = sc.nextInt();
            game.removePiece(chosenPiece);
        }

        //have currentPlayer won?
        if (game.haveCurrentPlayerWon()) {
            System.out.println(game.getCurrentPlayer().getName() + "has won!");
            game.gameOver(); //isnt really needed
        }

        game.changePlayerTurn();
    }

    private static Position stringToPos(String stringPos) {
        return Position.valueOf(stringPos);
    }

    private static boolean isPieceBlack(int id) {
        return !(id >= 0 || id < 9);
    }

    private static void mainLoop() {

        Game game = new Game(false, "Player 1", "Player 2", true);
        while (game.isGameRunning()) {
            if (game.getPlaceStage()) {
                playerPlacePiece(game);
                //has the otherPlayer (less then 3 pieces/no possible moves)/ then notify that game is over and currentPlayer won
                //has currentPlayer a mill then current HumanPlayer gets a list of possible pieces to remove
                //Player choose a piece to remove
                //System removes that piece from gameBoard
            } else {
                playerMovePiece(game);
            }
        }
    }

    public static void main(String[] args) {
        //Tells who turns it is;
        //If playerStage is place then only placePiecefunction can be called
        //System makes sure only those pieces can be selected with correct color
        //Player choose a piece
        //System tells player which positions are free
        //Player select one valid position
        //System calls placePiecemethod
        //Player gets info that a piece with id was place on a position
        mainLoop();
    }
}
