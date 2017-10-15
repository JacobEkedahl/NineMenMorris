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
public class AI extends Player {
    Strategy strat;
    
    public AI(String name, boolean black) {
        super(name, black);
        //strat=new Strategy();
    }
    
    public AI () {
        super("AI", true);
    }

    @Override
    public Piece placePiece() {
        return strat.placePiece();
    }
    
    public Position movePiece(){
        return strat.findNextMove();
    }

    @Override
    public Piece placePiece(int pieceId, Position newPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Piece placePiece(int pieceId, String newPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
