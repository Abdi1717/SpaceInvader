import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HumanShip extends SpaceItem {
	public static final int WIDTH = 20;
	public static final int LENGTH = 12;
    public static final int INIT_POS_X = 250;
    public static final int INIT_POS_Y = 250;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int courtWidth = 600;
    public static final int courtHeight = 300;
    private BufferedImage image;
    private int hitCount = 0;
    private String path = "";
    private Color color;
    
    public HumanShip() {
    	
    	
    	 super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, LENGTH, courtWidth, courtHeight);
    //((courtWidth - INIT_POS_X)/2)
          image = null;
		 
		 try {
			 image = ImageIO.read(new File("Images/player.png"));
		 } catch (IOException e) {
			 
		 }
    
    }
    
    
	
	@Override
	public void draw(Graphics g) {
		//g.setColor(Color.GREEN);
		
		if(alive) {
	
			
			Image newImage = image.getScaledInstance(image.getWidth()/20, image.getHeight()/20, Image.SCALE_DEFAULT);
			g.drawImage(newImage, px, py, null);
		}
		
		
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public void setHitCount() {
		hitCount++;
	}



	@Override
	public void death(Graphics g) {
		 BufferedImage  img;
		 try {
			  img = ImageIO.read(new File("Images/explosion.png"));
			  Image newImage = image.getScaledInstance(img.getWidth()/20, img.getHeight()/20, Image.SCALE_DEFAULT);
			  g.drawImage(newImage, px, py, null);
		 } catch (IOException e) {
			 
		 }
		 
		
	}
	
	
	

}
