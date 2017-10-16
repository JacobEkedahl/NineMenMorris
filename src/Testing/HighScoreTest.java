/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.io.IOException;
import modell.*;

/**
 *
 * @author Tobias
 */
public class HighScoreTest {

    public static void main(String[] args) throws IOException {
        HighScore fscore = new HighScore();
        fscore.addToHighScore("Tobias");
        fscore.addToHighScore("Tobias");
        fscore.addToHighScore("Tobias");
        fscore.addToHighScore("Jacob");
        fscore.addToHighScore("Jacob");
        fscore.addToHighScore("Svante");
        fscore.addToHighScore("Svante");
        fscore.addToHighScore("Sven");
        fscore.addToHighScore("Philip");
        fscore.addToHighScore("Göran");
        fscore.addToHighScore("Anders");
        fscore.addToHighScore("Philip");
        fscore.addToHighScore("Philip");
        fscore.addToHighScore("Svante");
        fscore.addToHighScore("Göran");
        fscore.addToHighScore("Jonte");
        fscore.addToHighScore("Max");
        fscore.addToHighScore("Max");
        fscore.addToHighScore("Jacob");
        fscore.addToHighScore("Jacob");
        fscore.addToHighScore("william");
        fscore.addToHighScore("William");
        fscore.addToHighScore("Tor");
        
        System.out.println("Tobias har vunnit: " + fscore.getWins("Tobias"));
        System.out.println("Jacob har vunnit: " + fscore.getWins("Jacob"));       
        System.out.println(fscore.toString());
        System.out.println(fscore.getTopTen());
        fscore.writeToFile();

    }
}
