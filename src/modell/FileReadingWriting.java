/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jacob
 */
public class FileReadingWriting {

    private String fileName;
    private ArrayList<HighScore> winners;

    public FileReadingWriting() {
        fileName = new String(System.getProperty("user.dir") + "highScore.ser");
        winners = new ArrayList();
    }

    public void addToHighScore(String winner) {
        boolean addWinner = true;
        for (int i = 0; i < winners.size(); i++) {
            if (winners.get(i).getName().equalsIgnoreCase(winner)) {
                winners.get(i).addToHighscore();
                addWinner = false;
            }
        }
        if (addWinner) {
            winners.add(new HighScore(winner));
        }
    }

    public int getWins(String name) {
        for (int i = 0; i < winners.size(); i++) {
            if (winners.get(i).getName().equalsIgnoreCase(name)) {
                return winners.get(i).getWins();
            }
        }
        return 0;
    }

    public void writeToFile() throws IOException {
        ObjectOutputStream out = null;
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("written to file " + file.toString());

        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(winners);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public void ReadFromFile() throws IOException {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            winners = (ArrayList<HighScore>) in.readObject();
        } catch (Exception IOExeption) {
            System.out.println("File not found!");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<HighScore> getTopTen() {
        ArrayList<HighScore> topTen = new ArrayList();
        Collections.sort(winners);
        if (winners.size() > 10) {
            for (int i = 0; i < 10; i++) {
                topTen.add(winners.get(i));
            }
        }else{
            for (int i = 0; i < winners.size(); i++) {
                topTen.add(winners.get(i));
            }
        }
        return topTen;
    }

}
