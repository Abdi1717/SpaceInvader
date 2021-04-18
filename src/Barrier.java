import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Barrier extends SpaceItem {
	public static final int WIDTH = 37;
	public static final int HEIGHT = 27;
	 //public static final int INIT_POS_X = 250;
	   // public static final int INIT_POS_Y = 250;
	    public static final int INIT_VEL_X = 0;
	    public static final int INIT_VEL_Y = 0;
		public static final int courtWidth = 600;
		public static final int courtHeight = 600;
		private BufferedImage image;
		private int hitCount = 0;
		private String path = "";

	public Barrier(int xLocation, int yLocation) {
		super(INIT_VEL_X, INIT_VEL_Y, xLocation, yLocation, WIDTH, HEIGHT, courtWidth, courtHeight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		switch (hitCount) {
		case 0 : path = "Images/barrier.gif"; break;
		case 1 : path = "Images/barrier2.gif"; break;
		case 2 : path = "Images/barrier3.gif"; break;
		case 3 : path = "Images/barrier4.gif"; break;
		}
		
		 try {
			 image = ImageIO.read(new File(path));
			 //w:44 h:32
			
			 Image newImage = image.getScaledInstance((int)(image.getWidth()*0.85), (int)(image.getHeight()*0.85), Image.SCALE_DEFAULT);
			 g.drawImage(newImage, px, py, null);
		 } catch (IOException e) {
			 
		 }
		
		
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public void setHitCount() {
		hitCount++;
	}
	public void resetHitCount() {
		hitCount = 0;
	}

	@Override
	public void death(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
