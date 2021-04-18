import java.awt.*;

import java.util.List;

import javax.swing.*;

public class Highscores extends JFrame{

  
    private static final long serialVersionUID = 1L;
    
    private List <String> valueList;
    private String  buList = "";

    public Highscores(List <String> inlist){
        super("Highscores");   
        this.valueList =  inlist;
    }
    
    public void printHighscores() {
        setLocation(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600,300));
        final JPanel panel = new JPanel();
        
        for(String s: valueList) {
            buList += s + "\n";
            
        }
        
        
        final JTextArea highscores = new JTextArea(buList);
        
        highscores.setEditable(false);
        
        panel.add(highscores);
        
        add(panel, BorderLayout.CENTER);
        
        pack();
        
        setVisible(true);
    }
}