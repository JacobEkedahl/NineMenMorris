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

    private static void playerPlacePiece(Player player, Game game) {
        String colorPlayer = "";
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

        for (int i = 0; i < playerOnePieces.size(); i++) {
            if (playerOnePieces.get(i).getId() == selectedId) {
                validChoice = true;
            }
        }

        while (validChoice == false) {

            System.out.println("Incorrect ID!");
            selectedId = sc.nextInt();

            for (int i = 0; i < playerOnePieces.size(); i++) {
                if (playerOnePieces.get(i).getId() == selectedId) {
                    validChoice = true;
                }
            }
        }

        ArrayList<Position> freePos = game.getFreePos();
        System.out.println("Available position: ");
        for (int i = 0; i < freePos.size(); i++) {
            System.out.print(freePos.get(i).name() + " - ");
        }
        System.out.println("\n");

        System.out.println("Please chooce available square: ");
        String chosenPos = sc.next();

        game.placePiece(selectedId, chosenPos);
        System.out.println("Player placed piece: " + selectedId + "At pos: " + chosenPos);

        ArrayList<Position> freePos2 = game.getFreePos();
        System.out.println("Available position: ");
        for (int i = 0; i < freePos2.size(); i++) {
            System.out.print(freePos2.get(i).name() + " - ");
        }
    }

    private static void mainLoop() {

        Game game = new Game(false, "Player 1", "Player 2");
        while (game.isGameRunning()) {
            if (game.getPlaceStage()) {
                playerPlacePiece(game.getCurrentPlayer(), game);
                //has the otherPlayer (less then 3 pieces/no possible moves)/ then notify that game is over and currentPlayer won
                //has currentPlayer a mill then current Player gets a list of possible pieces to remove
                //Player choose a piece to remove
                //System removes that piece from gameBoard
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