import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanelTool extends JPanel implements ActionListener {

	private static final long serialVersionUID = -8640540954512227385L;
	private static String b[] = {"Select", "Rectangle", "Line", "Circle", "Rect", "Bigger", "Smaller"};
	private int i;
	private JButton chosed1 = null;
	JButton[] buttons = new JButton[b.length];

	private MyPaintSetting setting;
	
	public MyPanelTool(MyPaintSetting setting) {
		super(new FlowLayout());
		this.setting = setting;
		this.setPreferredSize(new Dimension(100, 65));
		for (i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setSize(80, 80);
			buttons[i].setOpaque(true);
			buttons[i].setText(b[i]);
			buttons[i].setBorderPainted(false);
			buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			buttons[i].addActionListener(this);
			this.add(buttons[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(chosed1 != null)
			chosed1.setBorderPainted(false);
		b.setBorderPainted(true);
		chosed1 = b;
		switch(b.getText()) {
		case "Line":
			setting.shape = MyPaintSetting.EnumShape.Line;
			break;
		case "Circle":
			setting.shape = MyPaintSetting.EnumShape.Circle;
			break;	
		case "Rectangle":
			setting.shape = MyPaintSetting.EnumShape.Rectangle;
			break;	
		case "Bigger":
			if(setting.big <= 20)
				setting.big++;
			break;
		case "Smaller":
			if(setting.big >= 2)
				setting.big--;
			break;
		}
	}
}
