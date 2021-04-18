import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends SpaceItem{
	public static final int WIDTH = 60;
	public static final int HEIGHT = 15;
	public static final int INIT_POS_X = 500;
	public static final int INIT_POS_Y = 7;
	public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int courtWidth = 600;
    public static final int courtHeight = 600;
    private int humanShipLives = 3;

	public HealthBar() {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		int w = 60;
		g.setColor(Color.WHITE);
		g.drawRect(500, 7, 60, 15);
		switch (humanShipLives) {
		case 3 : w = 60; break;
		case 2 : w = 40; break;
		case 1 : w = 20; break;
		case 0 : w = 0; break;
			
		}
		g.setColor(Color.RED);
		g.fillRect(500, 7, w, 15);
		
	}
	
	public void humanShipDeath() {
		humanShipLives-=1;
	}
	
	public int getLives() {
		return humanShipLives;
	}
	public void restHealth() {
		humanShipLives = 3;
	}

	@Override
	public void death(Graphics g) {

		
	}

}
