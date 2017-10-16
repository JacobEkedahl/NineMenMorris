/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

/**
 *
 * @author Jacob
 */

//Select Piece
//Select Pos
//If mill select Piece to remove

//if state == 0
//getCurrentTurn
//if black only black pieces can be selected else white
//setselectedPiece
//state.next
//if state == 1
//get a list of available pos for that piece, all pos if placingStage is true
//only be able to hover those positions, only able to click those positons
//choose one of those positions
//check for mills or winning, if mill (if win = state = 10):
//state.next
//if state == 2
//getOpponentsPieces on board only able to click them
//selectAPiece, then remove piece
//check for win, if win = state = 10
//state.zero()

public class GameState {
    private int state;
    public GameState() {
        state = 0;
    }
    
    public int getState() {
        return state;
    }
    
    public void next() {
        state ++;
    }
    
    public void zero() {
        state = 0;
    }
}
