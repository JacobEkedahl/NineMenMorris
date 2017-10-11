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
        Piece p1 = new Piece(false, Position.NOPOS, 0);
        System.out.println(p1.toString() + "\n");

        p1.setPos(Position.A1);
        p1.setBlack(true);
        
        Position otherPos = Position.B4;
        Position noPos = Position.A1;
        
        boolean isSamePos = p1.isPosEqual(otherPos);
        System.out.println("Is pos equal to B4: " + isSamePos);
        isSamePos = p1.isPosEqual(noPos);
        System.out.println("Is pos equal to A1: " + isSamePos + "\n");
        
        boolean black = true;
        boolean isSameTeam = p1.isBlackEqual(black);
        System.out.println("Is it black: " + isSameTeam);
        
        System.out.println(p1.toString());
    }
}
