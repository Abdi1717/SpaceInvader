import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class AlienShip extends SpaceItem {
	    public static final int SIZE = 40;
	   // public static final int INIT_POS_X = 0;
	   // public static final int INIT_POS_Y = 0;
	   // public static final int INIT_VEL_X = 0;
	   public static final int INIT_VEL_Y = 0;
	    public static final int courtWidth = 600;
	    public static final int courtHeight = 300;
	    
	    private BufferedImage image;
	
	
	public AlienShip (int INIT_POS_X,int INIT_POS_Y, int INIT_VEL_X) {
		 super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
		 image = null;
		 
		 try {
			 image = ImageIO.read(new File("Images/Space-Invaders-PNG-Pic.png"));
		 } catch (IOException e) {
			 
		 }

		 
	}
	
	public int getVX() {
		return vx;
	}
	
	public void move() {
		super.move();
	}
	
	public void setVX(int Velocity) {
		vx = Velocity;
	}
	

	@Override
	public void draw(Graphics g) {
		if(alive) {
			//image.getHeight();
			//image.getWidth();
			Image newImage = image.getScaledInstance(image.getWidth()/20, image.getHeight()/20, Image.SCALE_DEFAULT);
			g.drawImage(newImage, px, py, null);
		} 
		
		
	}

	@Override
	public void death(Graphics g) {
		
		AlienShip leftUp = new alienShipDestruction (px,py, -3, 3);
		AlienShip rightUp = new alienShipDestruction (px,py, 3, 3);
		AlienShip leftDown = new alienShipDestruction (px,py, -3, -3);
		AlienShip rightDown = new alienShipDestruction (px,py, 3, -3);
		
		leftUp.draw(g);
		rightUp.draw(g);
		leftDown.draw(g);
		rightDown.draw(g);
		
	Timer mover = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	leftUp.move();
        		rightUp.move();
        		leftDown.move();
        		rightDown.move();
        		leftUp.draw(g);
        		rightUp.draw(g);
        		leftDown.draw(g);
        		rightDown.draw(g);
               }
            });
        
	mover.start();
	
		
		
		
		
		
		
	}
	
}
