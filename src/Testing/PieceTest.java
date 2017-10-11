/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import modell.Piece;
import modell.Position;

/**
 *
 * @author Jacob
 */
public class PieceTest {

    public static void main(String[] args) {
        Piece p1 = new Piece(true, Position.NOPOS);
        System.out.println(p1.toString() + "\n\n");

        p1.setPos(Position.A1);
        p1.setBlack(false);
        
        Position otherPos = Position.B4;
        Position noPos = Position.NOPOS;
        
        boolean isSamePos = p1.isPosEqual(otherPos);
        System.out.println("Is it equal: " + isSamePos);
        isSamePos = p1.isPosEqual(noPos);
        System.out.println("Is it equal: " + isSamePos);
        
        System.out.println(p1.toString());
    }
}
