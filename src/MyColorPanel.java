import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyColorPanel extends JPanel implements ActionListener {

	class ColorButton extends JButton {

		private Color color;

		public ColorButton(Color c) {
			super();
			this.color = c;
			this.setPreferredSize(new Dimension(30, 30));
		}

		public Color getColor() {
			return color;
		}
	}

	private static final long serialVersionUID = -8640540954512227385L;
	private static Color b[] = { Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN,
			Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW, Color.PINK, Color.YELLOW, Color.LIGHT_GRAY,
			Color.DARK_GRAY, Color.BLUE, Color.RED };
	private int i;
	ColorButton[] buttons = new ColorButton[16];
	private ColorButton chosed;

	private MyPaintSetting setting;
	
	public MyColorPanel(MyPaintSetting setting) {
		super(new GridLayout(2, 8, 5, 5));
		chosed = null;
		this.setting = setting;
		this.setPreferredSize(new Dimension(300, 65));
		for (i = 0; i < buttons.length; i++) {
			buttons[i] = new ColorButton(b[i]);
			buttons[i].setSize(80, 80);
			buttons[i].setOpaque(true);
			buttons[i].setBorderPainted(false);
			buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			// buttons[i].setActionCommand(b[i].toString());
			buttons[i].setForeground(b[i]);
			buttons[i].setBackground(b[i]);
			buttons[i].addActionListener(this);
			this.add(buttons[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ColorButton b = (ColorButton)e.getSource();
		if(chosed != null)
			chosed.setBorderPainted(false);
		b.setBorderPainted(true);
		chosed = b;
		this.setting.color = b.getColor();
		//JOptionPane.showMessageDialog(null, "You have clicked: " + b.getColor().toString());
	}
}
