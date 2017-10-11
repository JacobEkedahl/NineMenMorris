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
public class PlayerTest {

    public static void main(String[] args) {
        Player playerOne = new Player("Player One", false);
        Player playerTwo = new Player("Player Two", true);
        Piece boardPiece = playerOne.placePiece(0, Position.A1);
        
        System.out.println(boardPiece.toString() + "\n");

        Piece p = new Piece(true, Position.A4, 5);

        boolean sameTeam = playerTwo.sameTeam(p);
        System.out.println("Does piece belong to player: " + sameTeam);
        System.out.println("Is player black: " + playerOne.isBlack());
        System.out.println("No of pieces: " + playerOne.getNoOfPieces());

        Piece chosedPiece = playerOne.choosePiece(1); //id
        System.out.println(chosedPiece.toString());


        System.out.println(playerOne.toString());
        System.out.println(playerTwo.toString());
    }
}
