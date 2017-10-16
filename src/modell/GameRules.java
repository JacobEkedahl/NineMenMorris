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
    private final String[] millCombinations;

    /**
     * Constructor initializes all data members.
     */
    public GameRules() {
        freePositions = new ArrayList<>();
        this.placeStage = true;
        this.boardPieces = new ArrayList<Piece>();
        possiblePositions = new ArrayList<>();
        connectedPos = initAdjPos();
        millCombinations = initMilPos();
    }

    private String[] initMilPos() {
        String millString[] = {
            "#A1", "A4", "A7",
            "#A1", "D1", "G1",
            "#A4", "A1", "A7",
            "#A4", "B4", "C4",
            "#A7", "A1", "A4",
            "#A7", "D7", "G7",
            "#B2", "B4", "B6",
            "#B2", "D2", "F2",
            "#B4", "B2", "B6",
            "#B4", "A4", "C4",
            "#B6", "B4", "B2",
            "#B6", "D6", "F6",
            "#C3", "C4", "C5",
            "#C3", "D3", "E3",
            "#C4", "C5", "C3",
            "#C4", "B4", "A4",
            "#C5", "C3", "C4",
            "#C5", "D5", "E5",
            "#D1", "D2", "D3",
            "#D1", "A1", "G1",
            "#D2", "D1", "D3",
            "#D2", "B2", "F2",
            "#D3", "D1", "D2",
            "#D3", "C3", "E3",
            "#D7", "D6", "D5",
            "#D7", "A7", "G7",
            "#D6", "D7", "D5",
            "#D6", "B6", "F6",
            "#D5", "D6", "D7",
            "#D5", "C5", "E5",
            "#E3", "E4", "E5",
            "#E3", "D3", "C3",
            "#E4", "E5", "E3",
            "#E4", "F4", "G4",
            "#E5", "E3", "E4",
            "#E5", "D5", "C5",
            "#F2", "F4", "F6",
            "#F2", "D2", "B2",
            "#F4", "F2", "F6",
            "#F4", "E4", "G4",
            "#F6", "F4", "F2",
            "#F6", "D6", "B6",
            "#G1", "G4", "G7",
            "#G1", "D1", "A1",
            "#G4", "G1", "G7",
            "#G4", "F4", "E4",
            "#G7", "G1", "G4",
            "#G7", "D7", "A7#"};
        return millString;
    }

    private String[] initAdjPos() {
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

        return tempString;
    }

    /**
     * Returns an list of adjacent position to players selected Piece
     *
     * @param pos
     * @return
     */
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

    /**
     * Looks if there is free space on those adj Position, then return a list of
     * those positions
     *
     * @param free
     * @param selectedPiece
     * @return
     */
    public ArrayList<String> getOptionMove(ArrayList<Position> free, Piece selectedPiece) {
        ArrayList<String> freePos = new ArrayList<>();
        ArrayList<String> adjPos = getAdjPos(selectedPiece.getPos().name());
        for (Position p : free) {
            for (String s : adjPos) {
                if (posToString(p).equals(s) && !freePos.contains(s)) {
                    freePos.add(p.name());
                }
            }
        }
        return freePos;
    }

    /**
     * Takes a object Position and returns it in form of string
     * @param pos
     * @return 
     */
    private String posToString(Position pos) {
        return pos.name();
    }

    /**
     * 
     * @param index
     * @return One string with adjacent positions to the one indexed in the 
     * parameter
     */
    public String getPos(int index) {
        return connectedPos[index];
    }

    /**
     * Checks if a piece is currently placed on board
     * @param black
     * @param pos
     * @return 
     */
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
    
    public ArrayList<String> piecesAbleToRemove(ArrayList<Piece> boardPieces, ArrayList<Piece> playerPiece) {
        ArrayList<String> piecesToRemove = new ArrayList<String>();
        for (Piece p: boardPieces) {
            if (p.isBlack() == playerPiece.get(0).isBlack()) {
                piecesToRemove.add(Integer.toString(p.getId()));
            }
        }
        return (ArrayList<String>) piecesToRemove;
    }

    /**
     * Checks it a piece is places on a certain position and if its of a 
     * specific color
     * @param black
     * @param pos
     * @return true if there is a piece with the specified information
     */
    private boolean isPieceOnPos(boolean black, String pos) {
        for (int i = 0; i < boardPieces.size(); i++) {
            if (boardPieces.get(i).isBlack() == black && boardPieces.get(i).getPos().name().equals(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has won or not
     * @param otherPlayer
     * @param boardPieces
     * @param freePos
     * @return true if current player has won
     */
    public boolean haveCurrentPlayerWon(HumanPlayer otherPlayer, ArrayList<Piece> boardPieces, ArrayList<Position> freePos) { //need boardPieces update both boardPieces and freePos placeStage
        int totalPlayerPieces = 0;
        totalPlayerPieces += otherPlayer.getNoOfPieces();
        //System.out.println("totalPlayerPieces: " + totalPlayerPieces);
        totalPlayerPieces += getListPiecesOfColor(otherPlayer.isBlack(), boardPieces).size();

        return totalPlayerPieces < 3; //|| haveOtherNoMoves(otherPlayer, boardPieces, freePos)
    }

    /**
     * Checks if the other player can make any new moves
     * @param otherPlayer
     * @param boardPieces
     * @param freePos
     * @return returns true if otherPlayer can still make a move
     */
    private boolean haveOtherNoMoves(HumanPlayer otherPlayer, ArrayList<Piece> boardPieces, ArrayList<Position> freePos) { //only be called when placeStage is over need to test
        int noOfChoices = 0;
        ArrayList<Piece> piecesOnBoard = getListPiecesOfColor(otherPlayer.isBlack(), boardPieces);
        for (Piece p: piecesOnBoard) {
            noOfChoices += getOptionMove(freePos, p).size();
        }
        return noOfChoices == 0;
    }

    /**
     * 
     * @param black
     * @param boardPieces
     * @return all pieces of a specified boolean color
     */
    private ArrayList<Piece> getListPiecesOfColor(boolean black, ArrayList<Piece> boardPieces) {
        ArrayList<Piece> piecesOfColor = new ArrayList<>();
        for (Piece p : boardPieces) {
            if (p.isBlackEqual(black)) {
                piecesOfColor.add(p);
            }
        }
        return piecesOfColor;
    }

    /**
     * 
     * @param posString
     * @return Enumeration version of posString
     */
    private Position stringToPos(String posString) {
        return Position.valueOf(posString);
    }

    /**
     * Checks if a new mill has been formed
     * @param movedPiece recently moved piece
     * @param gameBoardUpdate updated version of gameBoard
     * @return true if a new mill has been formed
     */
    public boolean newMill(Piece movedPiece, ArrayList<Piece> gameBoardUpdate) {
        boardPieces = gameBoardUpdate;
        String piecePos = posToString(movedPiece.getPos());
        boolean pieceBlack = movedPiece.isBlack();
        ArrayList<String> millPos = new ArrayList<>();

        for (int i = 0; i < millCombinations.length; i++) {
            if (millCombinations[i].equals("#" + piecePos)) { //
                int j = i + 1;
                int mill = 0; //needs to be 2 in order to return true
                do {
                    millPos.add(millCombinations[j]);
                    j++;
                } while (!millCombinations[j].contains("#")); //now test if this mill is correct
                for (int k = 0; k < millPos.size(); k++) {
                    if (isPieceOnPos(pieceBlack, millPos.get(k))) {
                        mill++;
                    }
                }
                if (mill == 2) {
                    return true;
                }
                millPos.clear(); //lets the other mill combination
            }
        }
        return false;
    }
}
