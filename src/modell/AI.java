/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 *
 * @author Jacob
 */
public class AI extends Player implements Observer {

    Strategy strat;
    GameRules gameRules;
    private ArrayList<Position> freePos;
    private ArrayList<Piece> boardPieces;
    private String position;
    private static int counter = 0;

    public AI(String name, boolean black) {
        super(name, black);
        //strat=new Strategy();
        gameRules = new GameRules();
        freePos = new ArrayList<>();
        boardPieces = new ArrayList<>();
        position = "";
    }

    public AI() {
        super("AI", true);
    }

    @Override
    public Piece placePiece() {
        return strat.placePiece();
    }

    public String removePiece(ArrayList<Piece> opponentPieces) {
        return Integer.toString(opponentPieces.get(0).getId());
    }

    public String selectPiece(boolean placingStage, ArrayList<Piece> myPiece) {
        String idNumber = "";
        ArrayList<String> options;
        if (placingStage) {
            counter = counter + 1;
            return (Integer.toString(myPiece.get(counter).getId()));

        } else {
            for (int i = 0; i < boardPieces.size(); i++) {
                if (super.isBlack() == boardPieces.get(i).isBlack()) {
                    options = gameRules.getOptionMove(freePos, boardPieces.get(i), this);
                    if (options.size() > 0) {
                        position = options.get(0).toString();
                        return Integer.toString(boardPieces.get(i).getId());
                    }
                }
            }
        }
        //returns id
        return null;
    }

    public String selectPosition(ArrayList<Position> freePos) {
        Random rand = new Random();
        int randInt;
        randInt = rand.nextInt(5);
        return freePos.get(randInt).name();
    }

    @Override
    public Piece placePiece(int pieceId, String newPos) {
        Position stringToPos = Position.NOPOS;

        for (Position p : Position.values()) {
            if (p.name().equals(newPos)) {
                stringToPos = p;
                break;
            }
        }

        return placePiece(pieceId, stringToPos);
    }

    @Override
    public Piece placePiece(int pieceId, Position newPos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(boardPieces + " called with: " + arg);
        if (!boardPieces.equals(arg)) {
            boardPieces = (ArrayList<Piece>) arg;
        }
    }

}
