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
public class AI {
    private String name;
    private boolean black;
    
    public AI () {
        black = true; 
        name = "AI";
    }
    
    public AI (String name, boolean black) {
        this.name = name;
        this.black = black;        
    }
}
