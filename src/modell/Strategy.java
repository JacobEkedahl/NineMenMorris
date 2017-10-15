/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jacob
 */
public class Strategy {
    private GameBoard gameBoard;
    private boolean black;
    Random random;
    
    public Strategy(GameBoard gb, boolean black){
        gameBoard = gb;
        random = new Random();
        this.black = black;
        int i = random.nextInt(8);
        System.out.println(i);
        
    }
    
    public Piece placePiece(){
        
        return null;
    }
    
    public Position findNextMove(){
        
        return null;
    }       
}
