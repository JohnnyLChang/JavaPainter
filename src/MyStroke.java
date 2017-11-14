import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class MyStroke extends MyShape{
	final static float dash1[] = {5.0f};
    final static BasicStroke dashed =
        new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
    
	public MyStroke(){
		this( 0, 0, 0, 0, Color.BLACK,0);
	}
	public MyStroke( int x1, int y1, int x2, int y2,Color color,int big){
		super(x1,y1,x2,y2,color,big);
	}
	public void setbig( int big ){ super.setbig(big); }
	public void setcolor( Color color ){ super.setcolor(color); }
	public void draw( Graphics g ){
		Graphics2D g2d = ( Graphics2D ) g; 
		g2d.setPaint( super.getcolor() );    
		g2d.setStroke( dashed );
		g2d.draw( new Line2D.Double(  super.getx1(),  super.gety1(),  super.getx2(),  super.gety2() ));
	}
}