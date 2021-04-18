import java.awt.*;
import javax.swing.*;

public class Instructions extends JFrame{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Instructions(){
        super("Space Invader Instructions");   
    }
    
    public void printInstructions() {
        setLocation(1000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600,300));
        final JPanel panel = new JPanel();
        final JTextArea instructions = new JTextArea("\nSpace Invaders\nTo Play Space Invaders: Use the the right arrow to move right.\n Use the left arrow to move left\n Use the Up arrow to shoot.\n Use the HighScore button to see the highscores after you finshed the game and reload it.\n the objective of the game is to kill all of the aliens on the screen\n don't get shoot by the aliens!");
        instructions.setEditable(false);
        panel.add(instructions);
        add(panel, BorderLayout.CENTER);
        
        pack();
        
        setVisible(true);
    }
}