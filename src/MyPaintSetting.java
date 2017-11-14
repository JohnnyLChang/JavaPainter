import java.awt.Color;

public class MyPaintSetting {
	Color color;
	int big;
	EnumShape shape;
	
	public static enum EnumShape {
		Dot, Line, Circle, Rectangle
	}
	
	public MyPaintSetting() {
		color = new Color(0,0,0);
		big = 1;
		shape = EnumShape.Line;
	}
}
