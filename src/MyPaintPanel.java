import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.CircularQueue;

public class MyPaintPanel extends JPanel {
	private static final Logger log = LogManager.getLogger(MyPainter.class);

	CircularQueue<Point> points = new CircularQueue<Point>(10000);
	MyPaintSetting setting;	
	public BufferedImage bufImg; //Create a buffered image for drawing
	private BufferedImage bufImg_data[];
	private BufferedImage bufImg_cut;
	private int draw_panel_width=600, draw_panel_height=600;
	
	public MyPaintPanel(MyPaintSetting setting) {
		this.setting = setting;
		bufImg_data = new BufferedImage[1000];
		bufImg = new BufferedImage(draw_panel_width, draw_panel_height,BufferedImage.TYPE_3BYTE_BGR);
		bufImg.getGraphics().setColor(Color.white);
		bufImg.getGraphics().fillRect(0, 0, draw_panel_width, draw_panel_height);
		this.setLayout(null);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent event) {
				if (!points.isFull()) {
					points.enqueue(event.getPoint());
					repaint();
				}
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		//log.info("paintComponent");
		super.paintComponent(g);
		
		Graphics gg = this.bufImg.createGraphics();
		Graphics2D g2d = ( Graphics2D ) gg;

		//draw dots
		g2d.setColor(new Color(setting.color.getRGB()));
		while(!points.isEmpty()){
			Point p = points.dequeue();
			g2d.fillOval(p.x, p.y, 4, 4);
		}
		g.drawImage(this.bufImg, 0, 0, this);
		
	}
}
