/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class GameRules {

    private boolean placeStage;
    private ArrayList<Piece> boardPieces;
    private ArrayList<Position> possiblePositions;
    private final String connectedPos[];
    private ArrayList<Position> freePositions;

    public GameRules(boolean placeStage, ArrayList<Piece> boardPieces) {
        freePositions = new ArrayList<>();
        this.placeStage = placeStage;
        this.boardPieces = boardPieces;
        possiblePositions = new ArrayList<>();
        String tempString[] = {"#A1", "A4", "D1",
            "#A4", "A1", "A7", "B4",
            "#A7", "A4", "D7",
            "#B2", "B4", "D2",
            "#B4", "B2", "B6",
            "#B6", "B4", "D6",
            "#C3", "C4", "D3",
            "#C4", "B4", "C5", "C3",
            "#C5", "C4", "D5",
            "#D1", "D2", "A1", "G1",
            "#D2", "D1", "B2", "F2", "D3",
            "#D3", "C3", "E3", "D2",
            "#D5", "D6", "C5", "E5",
            "#D6", "B6", "F6", "D5", "D7",
            "#D7", "A7", "G7", "D6",
            "#E3", "D3", "E4",
            "#E4", "E3", "E5", "F4",
            "#E5", "D5", "E4",
            "#F2", "D2", "F4",
            "#F4", "F2", "F6", "E4", "G4",
            "#F6", "D6", "F4",
            "#G1", "D1", "G4",
            "#G4", "G1", "G7", "F4",
            "#G7", "D7", "G4"};
        connectedPos = tempString;
    }

    public ArrayList<String> getAdjPos(String pos) {
        ArrayList<String> adjPos = new ArrayList<>();
        for (int i = 0; i < connectedPos.length; i++) {
            if (connectedPos[i].equals("#" + pos)) {
                int j = i + 1;
                do {
                    adjPos.add(connectedPos[j]);
                    j++;
                } while (!connectedPos[j].contains("#"));
                return (ArrayList<String>) adjPos.clone();
            }
        }
        return (ArrayList<String>) adjPos.clone();
    }
    
    public ArrayList<Position> getOptionMove(ArrayList<Position> free, Piece selectedPiece) {
        ArrayList<Position> freePos = new ArrayList<>();
        ArrayList<String> adjPos = getAdjPos(selectedPiece.getPos().name());
        for (Position p: free) {
            for (String s: adjPos) {
                if (posToString(p).equals(s) && !freePos.contains(s)) {
                    freePos.add(p);
                }
            }
        }
        return freePos;
    }

    private String posToString(Position pos) {
        return pos.name();
    }

    public String getPos(int index) {
        return connectedPos[index];
    }

    private boolean isPieceOnPos(boolean black, Position pos) {
        for (int i = 0; i < boardPieces.size(); i++) {
            if (boardPieces.get(i).isBlack() == black && boardPieces.get(i).getPos() == pos) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /* public ArrayList<Position> moveOptionsPos(Piece selectedPiece, ArrayList<Piece> gameBoardPieces) {
        boardPieces = gameBoardPieces; //update instad
    }*/
}
