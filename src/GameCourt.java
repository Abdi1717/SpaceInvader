/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private AlienShip allienShip; // the Black Square, keyboard control
    private Bullet bullet; // the Golden Snitch, bounces
    private SpaceItem humanShip; // the Poison Mushroom, doesn't move
    private Background background;
    private SpaceItem greenLine;

    private HealthBar healthBar;
    private LinkedList <Bullet> bulletList = new LinkedList <Bullet>();
    private LinkedList <AlienBullet> alienBulletList = new LinkedList <AlienBullet>();
    private TreeMap <Integer,Barrier> barrierMap = new TreeMap <Integer, Barrier>();
    private TreeMap <String, AlienShip> alienMap = new TreeMap <String, AlienShip>();
    private ArrayList<String> aliensRemove = new ArrayList<String>();
    //private TreeMap <Integer, HumanShip> humanMap = new TreeMap<Integer, HumanShip>();
    //private ArrayList<Integer> humanRemove = new ArrayList<Integer>();
    private ArrayList<Integer> barrierRemove = new ArrayList<Integer>();
    private ArrayList <Bullet> bulletRemove = new ArrayList <Bullet>();
    private ArrayList <AlienBullet> alienBulletRemove = new ArrayList <AlienBullet>();

    private String currentUser = "";
    private List <String> addList = new ArrayList<String>();
  
    private int score;

    //private BufferedWriter br = new BufferedWriter(new FileWriter("File/HighScore.csv"));


    private boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    private boolean gameOver = false;

    // Game constants
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 300;
    public static final int Human_Ship_VELOCITY = 12;
    private boolean bulletMove = false;
    private int bulletCount = 0;
    private static final int HUMAN_BULLET_LIMIT = 10;
    

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 10;
    public static final int BULLETUNLOCKED = 10000;
    Timer timerBullet;
    Timer timer;
    Timer alienTimer;
    Timer moveTimer;


    public GameCourt(JLabel status, String user) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        currentUser = user;

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
      timerBullet = new Timer(BULLETUNLOCKED, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!aliensRemove.isEmpty()) {
              		for(String s: aliensRemove) {                      			
              			
                    		alienMap.remove(s);
              	}
              		
              	}
            	
            	bulletCount = 0;
            	alienShot();
               }
            });
        
        timerBullet.start();
        
      timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	
                tick();
                if(!bulletList.isEmpty() ) {
                	for(Bullet b: bulletList) {
                	b.move();
                	
                	  if(!alienMap.isEmpty()) {
                      	for (Map.Entry<String, AlienShip>   entry : alienMap.entrySet()) {
                      		  AlienShip value = entry.getValue();
                      		  String Key = entry.getKey();
                        		 
                        		  if(b.intersects(value)) {
                        			score++;
                        			bulletRemove.add(b);
                        			value.setDeath();
                       
                        			
                        			
                              		//bulletList.remove(b);
                              		aliensRemove.add(Key);
                              		//alienMap.remove(Key);
                              		
                              	}
                        		  
                        	   }
                     	
                      	
                      	

                      }
                	
                	
                	
                    if((!barrierMap.isEmpty())) {
                 	   for (Map.Entry<Integer,Barrier>  entry : barrierMap.entrySet()) {
                 		   Integer Key = entry.getKey();
                 		   Barrier value = entry.getValue();
                 		  if(b.intersects(value)) {
                 			
                      		value.setHitCount();
                      		 if(value.getHitCount() == 4) {
                      			barrierRemove.add(Key);
                      			 //barrierMap.remove(Key);
                       		   }
                      		
                      		bulletList.remove(b);	
                      	}
                 		   
                 	   }
                 	   
                 	   if(!barrierRemove.isEmpty()) {
                 		   for(Integer x: barrierRemove) {
                 			   barrierMap.remove(x);
                 		   }
                 	   }
                    }
                    
       
                	
                
                	
                	}
                	
                	   if(!bulletRemove.isEmpty() ) {
                       	for(Bullet b: bulletRemove) {
                       		
                      		bulletList.remove(b);
                       	  
                       	}
                	   
                	   
                	   }
                	
                }
                
                if(!alienMap.isEmpty()) {
                	for (Map.Entry<String, AlienShip>   entry : alienMap.entrySet()) {
                		  AlienShip value = entry.getValue();
                  		  value.move();
                  		  value.bounce(value.hitWall());
                  	   }

                }
                
                
                
            }
            
        } );
        timer.start(); // MAKE SURE TO START THE TIMER!
        
        alienTimer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	
              
                if(!alienBulletList.isEmpty() ) {
                	for(AlienBullet a: alienBulletList) {
                	a.move();
                	
                	 if(a.intersects(humanShip)) {
             			healthBar.humanShipDeath();
             			alienBulletRemove.add(a);
             			}
                	
                	/*
                	  if(!humanMap.isEmpty()) {
                      	for (Map.Entry<Integer, HumanShip>   entry : humanMap.entrySet()) {
                      		HumanShip value = entry.getValue();
                      		Integer Key = entry.getKey();
                        		 
                        		  if(a.intersects(value)) {
                        			healthBar.humanShipDeath();
                        			alienBulletRemove.add(a);
                              		//bulletList.remove(b);
                              		humanRemove.add(Key);
                              		//alienMap.remove(Key);
                              		
                              	}
                        		  
                        	   }
                      	
                      	if(!humanRemove.isEmpty()) {
                      		for(int s: humanRemove) {                      			
       //LOOK HERE             	
                      			if(healthBar.getLives() != 1) {
                      			humanMap.put(healthBar.getLives(), new HumanShip());
                      			}
                      			humanMap.remove(s);
                      	}
                      		
                      	}

                      }
                	  
                	 */ 
                	
                	
                	
                    if((!barrierMap.isEmpty())) {
                 	   for (Map.Entry<Integer,Barrier>  entry : barrierMap.entrySet()) {
                 		   Integer Key = entry.getKey();
                 		   Barrier value = entry.getValue();
                 		  if(a.intersects(value)) {
                 			
                      		value.setHitCount();
                      		 if(value.getHitCount() == 4) {
                      			barrierRemove.add(Key);
                      			 //barrierMap.remove(Key);
                       		   }
                      		
                      		alienBulletList.remove(a);	
                      	}
                 		   
                 	   }
                 	   
                 	   if(!barrierRemove.isEmpty()) {
                 		   for(Integer x: barrierRemove) {
                 			   barrierMap.remove(x);
                 		   }
                 	   }
                    }
                    
       
                	
                
                	
                	}
                	
                	   if(!alienBulletRemove.isEmpty() ) {
                       	for(AlienBullet b: alienBulletRemove) {
                       		
                       
                       		
                       		alienBulletList.remove(b);
                       	  
                       	}
                	   
                	   
                	   }
                	
                }
                
       
                
                
                
            }
            
        } );
        alienTimer.start(); 
       
        
        moveTimer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tick();
            }
        });
        moveTimer.start();
        


        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                	humanShip.setVx(-Human_Ship_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	humanShip.setVx(Human_Ship_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	//healthBar.humanShipDeath();
                
                	if(bulletCount <= HUMAN_BULLET_LIMIT)
                	{bulletList.add( new Bullet(humanShip.getPx() + 9,humanShip.getPy()) );
                	  bulletCount++;
                	}
                	
                	bulletMove = true;
                	
                }
                
               
                
            }

            public void keyReleased(KeyEvent e) {
            	humanShip.setVx(0);
            	humanShip.setVy(0);
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	//allienShip = new AlienShip(200,100 , -5);
    	alienMap = new TreeMap <String, AlienShip>();
    	addAliens();
        humanShip = new HumanShip();
        background = new Background();
        
        // bullet     = new Bullet(humanShip.getPx() + 9,humanShip.getPy());
         bulletMove = false;
         bulletList = new LinkedList <Bullet>();
         alienBulletList = new LinkedList <AlienBullet>();

        
         aliensRemove = new ArrayList<String>();
        
         barrierRemove = new ArrayList<Integer>();
         bulletRemove = new ArrayList <Bullet>();
         alienBulletRemove = new ArrayList <AlienBullet>();
         alienBulletList = new LinkedList <AlienBullet>();
         
         
         
         bulletCount = 0;
         
        
        //line
        greenLine = new GreenLine();
        
        
        //healthBar
        healthBar = new HealthBar();
        healthBar.restHealth();
        //addHuman();
        //barriers
       
        barrierMap = new TreeMap <Integer, Barrier>();
        addBarriers();
        
    
        
        score = 0;
        

        playing = true;
        status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    
     void addBarriers() {
    	
         for(int i = 0; i<4; i++) {
        	 
        	 barrierMap.put(i, new Barrier ( (80 + i*150) , 220));
         }
    	
    }
    void addAliens() {
    	int alienNumber = 0;
    	for(int row = 60; row < 180; row+= 40 ) {
    		for(int col = 200; col <420 ;col+=40) {
    			alienNumber++;
    			alienMap.put( ("Alien"+alienNumber),new AlienShip(col, row, -2));
    		}
    	}
    }
 
    void alienShot() {
    	Random rand = new Random();

    	

    	int randomNum = rand.nextInt((alienMap.size() - 1) + 1) + 1;
    	
    	
    	 if(!alienMap.isEmpty()) {
           	for (Map.Entry<String, AlienShip>   entry : alienMap.entrySet()) {
           		  AlienShip value = entry.getValue();
           		  String Key = entry.getKey();
             		 if(Key.equals("Alien"+randomNum)) {
             			 
             			alienBulletList.add( new AlienBullet(value.getPx() ,value.getPy()));
             		 }
             		
             		  
             	   }
    	 }
    }
    void tick() {
        if (playing) {
            
        	humanShip.move();
        	
        	
        	
        	if(alienMap.isEmpty()||healthBar.getLives() == 0) {
        	
        		gameOver = true;
                timer.stop();
                timerBullet.stop();
                alienTimer.stop(); 
                moveTimer.stop();
                
        	}
        	
            // update the display
            repaint();
        }
    }
    


    @Override
    public void paintComponent(Graphics g) {
    	 
    	super.paintComponent(g);
    	
    	
       
    	if(!gameOver) {
    	background.draw(g);
        greenLine.draw(g);
       if (healthBar.getLives() != 0) {
        humanShip.draw(g);
       } else {
    	   humanShip.death(g);
       }
        healthBar.draw(g);
         
        
       if( (!bulletList.isEmpty())) { 
    	   for(Bullet b: bulletList)
           	b.draw(g);
    	   }
       
       if( (!alienBulletList.isEmpty())) { 
    	   for(AlienBullet a: alienBulletList)
           	a.draw(g);
    	   }

       if((!barrierMap.isEmpty())) {
    	   for (Map.Entry<Integer,Barrier>  entry : barrierMap.entrySet()) {
    		   Barrier value = entry.getValue();
    		   value.draw(g);
    	   }
       }
       
       if(!alienMap.isEmpty()) {
       	for (Map.Entry<String, AlienShip>   entry : alienMap.entrySet()) {
       		  AlienShip value = entry.getValue();
         		if(value.getLifeStatus())  {
         			value.draw(g);
         			
         		}else {
         			value.death(g);
         		}
       		 
         	   }

       }
      
    
        g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
        g.drawString("Score: "+score, 30, 20);
        g.drawString("Health: ", 420, 20);
        
        
      
    }
      else {
    	  background.gameOver();
    	  background.draw(g);
    	  g.setFont(new Font("TimesRoman", Font.BOLD, 30)); 
    	  g.setColor(Color.WHITE);
    	  g.drawString("GameOver: ", 150, 150);
    	 addList.add(currentUser + "," + score);
        
        writeStringsToFile(addList, "File/HighScore.csv", true);
        status.setText("Game Over");
        playing = false;
    	}
       
    }






  
    static String extractColumn(String csvLine, int csvColumn) {
    	try {
    	String[] csvRowValues = csvLine.split(",");		
    	
    	if (csvRowValues[csvColumn] == null) {
    		 return null;}
    	 else {
    		 return csvRowValues[csvColumn];  	 
    	 }
    	 
    	} catch (IndexOutOfBoundsException e) {
    		return null;
        }	
     }
  static List<String> csvFileToTweets(String pathToCSVFile, int tweetColumn) {
    	 File fileReader = new File(pathToCSVFile);	    
         if (pathToCSVFile == null || !fileReader.exists()) { 
        	 throw new IllegalArgumentException("filePath is null or doesn't exist"); 
        	 }
     	List<String> TweetList = new LinkedList<String>();
    	FileLineIterator lineReader = new FileLineIterator(pathToCSVFile);	
    	
    	try {
    	while (lineReader.hasNext()){
    		 String tweetVal = extractColumn(lineReader.next(),tweetColumn);
    		 
    		 if(tweetVal != null) {
    			 TweetList.add(tweetVal) ;
    		 }
        	
        	}
   
          return TweetList;
    	} catch (IllegalArgumentException e) {
    		throw new IllegalArgumentException();
    	}
    	
    	
        	  
    }
  public void writeStringsToFile(List<String> stringsToWrite, String filePath, boolean append) {
      File file = Paths.get(filePath).toFile();
      BufferedWriter bw = null;
      
      try {
      	
      	FileWriter stringWriter = new FileWriter(file, append);
           bw = new BufferedWriter(stringWriter);
          
      	for(String currentVal : stringsToWrite) {
      		bw.write(currentVal);
      		bw.newLine();
      	}
      	
      	bw.close();
      	
		} catch (FileNotFoundException e) {
			
			System.out.print("File not found");
		} catch (IOException e) {
			System.out.print("File reached the end");
			
		
      
      }
  }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}