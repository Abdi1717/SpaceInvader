import java.awt.Color;
import java.awt.Graphics;

public class alienShipDestruction extends AlienShip {

	    public static  int POS_X = 0 ;
	    public static  int POS_Y = 0;
	    public static  int VEL_X = 0;
	    public static  int VEL_Y = 0;
	
	public alienShipDestruction(int INIT_POS_X, int INIT_POS_Y, int INIT_VEL_X,  int INIT_VEL_Y) {
		super(INIT_POS_X, INIT_POS_Y, INIT_VEL_X);
		// TODO Auto-generated constructor stub
		    POS_X = INIT_POS_X;
		    POS_Y = INIT_POS_Y;
		    VEL_X = INIT_VEL_X;
		    VEL_Y = INIT_VEL_Y;
	}
	
	@Override
	 public void move() {
		POS_X += VEL_X;
		POS_Y += VEL_Y;

    }
	
	@SuppressWarnings("unused")
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		if(VEL_X == -3 && INIT_VEL_Y == 3) {
		g.drawLine(POS_X, POS_Y, POS_X-10, POS_Y+10);
		} else if(VEL_X == 3 && VEL_Y == 3) {
		g.drawLine(POS_X, POS_Y, POS_X+10, POS_Y+10);
		} else if(VEL_X == -3 && VEL_Y == -3) {
		g.drawLine(POS_X, POS_Y, POS_X-10, POS_Y-10);
		} else  {
		g.drawLine(POS_X, POS_Y, POS_X+10, POS_Y-10);
		}
		
		//(VEL_X == 3 && VEL_Y == -3)
		
	}

}
