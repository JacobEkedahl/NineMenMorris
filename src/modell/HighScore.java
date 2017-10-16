/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Tobias
 */
class HighScore implements Serializable, Comparable<HighScore> {
    private final String winner;
    private int winTimes;
    
    public HighScore(String winnerName){
        winner = new String(winnerName);
        winTimes=1;
    }
    
    public void addToHighscore(){
        winTimes++;
    }
    
    public String getName(){
        return winner;
    }
    
    public int getWins(){
        return winTimes;
    }
    
    public void setWins(int wins){
        winTimes=wins;
    }
    
    @Override
    public int compareTo(HighScore a)
    {
        return a.getWins() - winTimes;
    }
    
    public String toString(){
        return "" + winner + " has won: " + winTimes + " times";
    }
   
}