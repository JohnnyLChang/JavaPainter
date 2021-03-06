import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyPainter extends JFrame implements ActionListener {

	private Container c = getContentPane();
	private String menuBar[] = { "File(F)", "About(H)" };
	private String menuItem[][] = { { "New File(F)|78", "Close File(X)|88" }, { "About(A)" } };
	private JMenuItem jMenuItem[][] = new JMenuItem[4][5];
	private JMenu jMenu[];
	private int i, j;
	private JPanel contentPanel;
	private JCheckBoxMenuItem jCheckBoxMenuItem[] = new JCheckBoxMenuItem[4];
	private static final Logger log = LogManager.getLogger(MyPainter.class);
	public MyPaintSetting setting;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPainter frame = new MyPainter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyPainter() {		
		/*JFrame functions*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 600);
		this.setJMenuBar(CreateMenu());
		this.setContentPane(CreatePanel());
		this.setTitle("Java Painter");
		setting = new MyPaintSetting();
		this.add( new MyPaintPanel(setting), BorderLayout.CENTER );
		this.add( new MyPanelTool(setting), BorderLayout.WEST);
		this.add( new MyPanelColor(setting), BorderLayout.SOUTH);
		
	}
	
	private JPanel CreatePanel() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(1, 1));
		
		//JToolBar toolBar = new JToolBar();
		//contentPanel.add(toolBar, BorderLayout.NORTH);
		return this.contentPanel;
	}
	
	private JMenuBar CreateMenu() {
		JMenuBar bar = new JMenuBar();
		jMenu = new JMenu[menuBar.length];
		for (i = 0; i < menuBar.length; i++) {
			log.info("add menu {}", menuBar[i]);
			jMenu[i] = new JMenu(menuBar[i]);
			jMenu[i].setMnemonic(menuBar[i].split("\\(")[1].charAt(0));
			bar.add(jMenu[i]);
		}

		for (i = 0; i < menuItem.length; i++) {
			for (j = 0; j < menuItem[i].length; j++) {
				if (i == 0 && j == 4 || i == 1 && j == 2)
					jMenu[i].addSeparator();
				log.info("add menu item {}", menuItem[i][j]);
				jMenuItem[i][j] = new JMenuItem(menuItem[i][j].split("\\|")[0]);
				if (menuItem[i][j].split("\\|").length != 1)
					jMenuItem[i][j].setAccelerator(KeyStroke
							.getKeyStroke(Integer.parseInt(menuItem[i][j].split("\\|")[1]), ActionEvent.CTRL_MASK));
				jMenuItem[i][j].addActionListener(this);
				jMenuItem[i][j].setMnemonic(menuItem[i][j].split("\\(")[1].charAt(0));

				jMenu[i].add(jMenuItem[i][j]);
			}
		}
		return bar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
