/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import modell.*;

/**
 *
 * @author Tobias
 */
public class textUI {
    
    public static void main(String[] args) {
        Game game = new Game(false, "Player 1", "Player 2");
        System.out.println(game.placePiece(0, Position.A1));
        System.out.println(game.placePiece(9, Position.D1));
        game.movePiece(0, Position.A4);
        
        
        System.out.println(game.toString());
    }
}
