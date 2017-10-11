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
public class GameTest {

    public static void main(String[] args) {
        Game game = new Game(false, "Jacob", "Erik");
        System.out.println(game.toString());
    }
}
