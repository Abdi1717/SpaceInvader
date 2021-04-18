import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Background extends SpaceItem {
	public static final int WIDTH = 6000;
	public static final int LENGTH = 300;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int courtWidth = 600;
    public static final int courtHeight = 300;
    private BufferedImage image;
    private String location = "Images/download.jpg";
	

	public Background() {
		super(0, 0, INIT_POS_X, INIT_POS_Y, WIDTH, LENGTH, courtWidth, courtHeight);
		// TODO Auto-generated constructor stub
		image = null;
		 //"Images/download.jpg"
		
	}

	@Override
	public void draw(Graphics g) {
		 try {
			 image = ImageIO.read(new File(location));
		 } catch (IOException e) {
			 System.out.print("Not working");
		 }
		
		Image newImage = image.getScaledInstance((int)(image.getWidth()*1.9),(int)( image.getHeight()*1.9), Image.SCALE_SMOOTH);
		 g.drawImage(newImage, 0, 0, null);
		
	}
	
	public void gameOver() {
		location = "Images/blackBackground.jpg";
	}

	@Override
	public void death(Graphics g) {
		
	}

}
