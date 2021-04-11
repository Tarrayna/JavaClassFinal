/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tarrayna.finalExam;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarra
 */
public class RubiksCubeGameRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        int number = 0; 
        
        while(true)
        {
            System.out.println(number); 
            number++; 
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RubiksCubeGameRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearScreen(); 
            
        }
        
        
    }
    public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  
}
