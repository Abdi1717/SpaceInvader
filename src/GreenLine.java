import java.awt.Color;
import java.awt.Graphics;

public class GreenLine extends SpaceItem {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 1;
	 public static final int INIT_POS_X = 250;
	    public static final int INIT_POS_Y = 240;
	    public static final int INIT_VEL_X = 0;
	    public static final int INIT_VEL_Y = 0;
		public static final int courtWidth = 600;
		public static final int courtHeight = 600;

	public GreenLine() {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(0, 290, 600, 290);
	}

	@Override
	public void death(Graphics g) {
		
	}

}
