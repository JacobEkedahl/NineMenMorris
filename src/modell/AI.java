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
    private static int counter = -1;

    public AI(String name, boolean black) {
        super(name, black);
        //strat=new Strategy();
        gameRules = new GameRules();
        freePos = new ArrayList<>();
        boardPieces = new ArrayList<>();
        position = "";
    }

    public AI() {
        this("AI", true);
    }

    public void connectRules(GameRules rules) {
        this.gameRules = rules;
    }

    public String removePiece(ArrayList<Piece> opponentPieces) {
        return Integer.toString(opponentPieces.get((new Random()).nextInt(opponentPieces.size())).getId());
    }

    public String selectPiece(boolean placingStage, ArrayList<Piece> myPiece, ArrayList<Position> freePos, AI aiPlayer) {
        ArrayList<String> options = new ArrayList<>();
        System.out.println("selectPiece is called");
        System.out.println("freePos size: " + freePos.size());
        System.out.println("my pieces size: " + myPiece.size());
        Piece tempP = new Piece();

        // System.out.println("trying piece: " + myPiece.get(i).getId());
        if (!placingStage) {
            int randPiece = 0;
            do {
                randPiece = new Random().nextInt(myPiece.size());
                options = gameRules.getOptionMove(freePos, myPiece.get(randPiece), aiPlayer);
            } while (options.size() == 0);
            //System.out.println("This piece has options: " + options.size());
            position = options.get(0);
            return Integer.toString(myPiece.get(randPiece).getId());
        } else {
            tempP = myPiece.get(counter + 1);
            for (Position p : freePos) {
                tempP.setPos(p);
                // System.out.println("tempPos: " + tempP.getPos().name() + " " + gameRules.getNoPiece());
                if (gameRules.newMill(tempP)) {
                    System.out.println("there is a mill to be had");
                    position = p.name();
                    counter++;
                    return Integer.toString(myPiece.get(counter).getId());

                }

                options.add(p.name());
            }

            position = options.get((new Random()).nextInt(options.size()));
            counter++;
            return Integer.toString(myPiece.get(counter).getId());
        }
    }

    public String selectPosition(ArrayList<Position> freePos) {
        return position;
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

    public Piece placePiece(int pieceId, Position newPos) {
        Piece pieceForBoard;
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces = super.getPieces();

        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).isIdEqual(pieceId)) {
                pieces.get(i).setPos(newPos);
                pieceForBoard = pieces.get(i);
                pieces.remove(i);
                return pieceForBoard;
            }
        }
        System.out.println("Return null piece");
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!boardPieces.equals(arg)) {
            boardPieces = (ArrayList<Piece>) arg;
        }
    }

    @Override
    public Piece placePiece() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
