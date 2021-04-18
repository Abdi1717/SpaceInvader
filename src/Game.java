/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;


import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
    	
    	
    	
    	
    	
        final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(300, 300);
        
        String inputName = JOptionPane.showInputDialog(frame, "Please enter a username: ", 
        		"Username", JOptionPane.PLAIN_MESSAGE);


		 ImageIcon image = new ImageIcon("Images/space.jpg");
		 final JLabel background = new JLabel(image);
		 background.setBounds(0, 0, 600, 300);;
		 frame.add(background,  BorderLayout.CENTER);
		 
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status, inputName);
        frame.add(court, BorderLayout.CENTER);
       
        
       List<String> scorefile = GameCourt.csvFileToTweets("File/HighScore.csv", 1);
       int [] finshed = new int[scorefile.size()];
       List<String> Names = GameCourt.csvFileToTweets("File/HighScore.csv", 0);
       List <String> finalList= new ArrayList<String>();
        TreeMap<Integer, String> start = new TreeMap <Integer, String>();
        
       
        for(int i = 0; i < scorefile.size(); i++){
           
          
            try {
                start.put(Integer.parseInt(scorefile.get(i)), Names.get(i));
                
                finshed[i] = Integer.parseInt(scorefile.get(i));
            }
            catch(NumberFormatException e) {
               
            }
          
            
        }

   
        Arrays.sort(finshed);
        
        for(int x: finshed) {
        	
            for (Map.Entry<Integer, String>   entry : start.entrySet()) {
                String value = entry.getValue();
                Integer Key = entry.getKey();
                
                if(x == Key) {
                    finalList.add("UserName: "+value+", "+"Score: "+Key);
                }
          
            
            }
        
        }
        
        
       

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        
        final JButton highscore = new JButton("High Score");
        highscore.addActionListener(new  ActionListener() {
        	public void  actionPerformed(ActionEvent e) {
        		Highscores high = new Highscores(finalList);
        		high.printHighscores();
        	}
        });
        
        control_panel.add(highscore);
        
        final JButton instruction = new JButton("Instructions");
        instruction.addActionListener(new  ActionListener() {
        	public void  actionPerformed(ActionEvent e) {
        		Instructions instr = new Instructions();
        		instr.printInstructions();
        	}
        });
        control_panel.add(instruction);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}