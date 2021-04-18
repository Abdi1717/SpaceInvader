import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends SpaceItem{
	
	public static final int WIDTH = 2;
	public static final int HEIGHT = 8;
    public static final int INIT_POS_X = 250;
    public static final int INIT_POS_Y = 250;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
	public static final int courtWidth = 600;
	public static final int courtHeight = 600;
    private Color color;
	
	public Bullet(int x, int y) {

	super(0,-3, x, y, WIDTH, HEIGHT, courtWidth, courtHeight);
		// TODO Auto-generated constructor stub
	}




	

	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(px, py, WIDTH, HEIGHT);
		
	}








	@Override
	public void death(Graphics g) {
		
	}

}
