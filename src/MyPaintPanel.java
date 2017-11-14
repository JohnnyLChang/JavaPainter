import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
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
	public BufferedImage bufImg; // Create a buffered image for drawing
	private BufferedImage bufImg_data[];
	private BufferedImage bufImg_cut;
	private int draw_panel_width = 600, draw_panel_height = 600;
	private Point pressStart, pressEnd;
	private boolean mousepressed;
	private boolean drawed;
	private MyShape shape;
	private MyShape stroke;
	private MyShape mouse;
	private Point p;

	public MyPaintPanel(MyPaintSetting setting) {
		this.setting = setting;
		bufImg_data = new BufferedImage[1000];
		bufImg = new BufferedImage(draw_panel_width, draw_panel_height, BufferedImage.TYPE_3BYTE_BGR);
		bufImg.getGraphics().setColor(Color.white);
		bufImg.getGraphics().fillRect(0, 0, draw_panel_width, draw_panel_height);
		this.setLayout(null);
		p = new Point(0,0);
		drawed = true;

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				switch(setting.shape) {
				case Line:
					if (setting.shape == MyPaintSetting.EnumShape.Line && mousepressed) {
						log.info("mouse pressed for line");
						stroke = new MyLine(pressStart.x, pressStart.y, e.getX(), e.getY(),
								new Color(setting.color.getRGB()), setting.big);
						repaint();
					}
					break;
				case Circle:
					if (setting.shape == MyPaintSetting.EnumShape.Circle && mousepressed) {
						log.info("mouse pressed for Circle");
						stroke = new MyCircle(pressStart.x, pressStart.y, e.getX(), e.getY(),
								new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), false, setting.big);
						repaint();
					}
					break;
				case Rectangle:
					if (setting.shape == MyPaintSetting.EnumShape.Rectangle && mousepressed) {
						log.info("mouse pressed for Rectangle");
						stroke = new MyRect(pressStart.x, pressStart.y, e.getX(), e.getY(),
								new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), false, setting.big);
						repaint();
					}
					break;
				}
			}

			public void mouseMoved(MouseEvent e) {
				if(!mousepressed) {
					p = e.getPoint();
					repaint();
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				log.info("mouseClicked");
			}

			public void mousePressed(MouseEvent e) {
				log.info("mousePressed" + e.getPoint());
				pressStart = e.getPoint();
				mousepressed = true;
				drawed = false;
			}

			public void mouseReleased(MouseEvent e) {
				log.info("mouseReleased" + e.getPoint());
				pressEnd = e.getPoint();
				switch (setting.shape) {
				case Line:
					shape = new MyLine(pressStart.x, pressStart.y, pressEnd.x, pressEnd.y,
							new Color(setting.color.getRGB()), setting.big);
					mousepressed = false;
					repaint();
					break;
				case Circle:
					shape = new MyCircle(pressStart.x, pressStart.y, pressEnd.x, pressEnd.y,
							new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), false, setting.big);
					mousepressed = false;
					repaint();
					break;
				case Rectangle:
					shape = new MyRect(pressStart.x, pressStart.y, pressEnd.x, pressEnd.y,
							new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), new Color(setting.color.getRGB()), false, setting.big);
					mousepressed = false;
					repaint();
					break;
				}
			}

			public void mouseEntered(MouseEvent e) {
				log.info("mouseEntered");
			}

			public void mouseExited(MouseEvent e) {
				log.info("mouseExited");
			}

			public void mouseWheelMoved(MouseEvent e) {
				log.info("mouseWheelMoved");
			}
		});
	}

	public void paintComponent(Graphics g) {
		// log.info("paintComponent");
		super.paintComponent(g);

		Graphics gg = this.bufImg.createGraphics();

		// draw dots
		/*
		 * g2d.setColor(new Color(setting.color.getRGB())); while (!points.isEmpty()) {
		 * Point p = points.dequeue(); g2d.fillOval(p.x, p.y, 4, 4); }
		 */
		switch (setting.shape) {
		case Line:
		case Rectangle:
		case Circle:
			if (this.mousepressed == false && !drawed) {
				log.info("confirm draw shape");
				if (shape != null) {
					shape.draw(gg);
					g.drawImage(this.bufImg, 0, 0, this);
					drawed = true;
					return;
				}
			} else {
				if (this.mousepressed == true) {
					log.info("tracking draw line");
					g.drawImage(this.bufImg, 0, 0, this);
					stroke.draw(g);
					return;
					// g.drawLine(pressStart.x, pressStart.y, pressStart.x+100, pressStart.y+100);
				}
			}
			break;
		default:
			break;
		}
		g.drawImage(this.bufImg, 0, 0, this);
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.setColor(new Color(setting.color.getRGB()));
		g2d.fillOval(p.x, p.y, setting.big, setting.big);
	}
}
