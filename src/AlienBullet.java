import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlienBullet extends SpaceItem {
	public static final int WIDTH = 2;
	public static final int HEIGHT = 8;
    public static final int INIT_POS_X = 250;
    public static final int INIT_POS_Y = 250;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
	public static final int courtWidth = 600;
	public static final int courtHeight = 600;

    private Color color;
    public AlienBullet(int x, int y) {

    	super(0,3, x, y, WIDTH, HEIGHT, courtWidth, courtHeight);
    		
    	}
    
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(px, py, WIDTH, HEIGHT);
		
	}

	@Override
	public void death(Graphics g) {
	}
    
}
