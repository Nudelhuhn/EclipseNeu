package uebung1_Klausurvorbereitung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SwingFenster extends JPanel
{
	private int centFrameWid = 800;
	private int centFrameHei = 600;
	private int dotSize = 10;
	
	public SwingFenster()
	{		
		JFrame mainFrame = new JFrame("Übung1");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout(8, 8));//set layout and margin 
		mainFrame.setSize(centFrameWid, centFrameHei);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int frameX = centerX - mainFrame.getWidth() / 2;
		int frameY = centerY - mainFrame.getHeight() / 2;
		mainFrame.setLocation(frameX, frameY);

		JMenuBar menuBar = new JMenuBar();

		JMenu options = new JMenu("Options");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		options.add(save);
		options.add(exit);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("about Übung1");
		help.add(about);

		menuBar.add(options);
		menuBar.add(help);

		mainFrame.setJMenuBar(menuBar);

		createSaveButtonWindow(centerX, centerY, save);
		exitWithExitMenuItem(exit);
		consoleoutputWhenAboutButtonIsPressed(about);
		CreateDrawingPanelWidthMargin(mainFrame);
		JFrame coordFrame = createCoordFrameAndCoupleItToMainFrame(mainFrame);
		
		mainFrame.setVisible(true);
		coordFrame.setVisible(true);
	}

	private JFrame createCoordFrameAndCoupleItToMainFrame(JFrame mainFrame)
	{
		JFrame coordFrame = new JFrame("Coordinates");
		coordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		coordFrame.setSize(centFrameWid, centFrameHei/6);
		
		Point locaMainFrame = mainFrame.getLocation();
		Point locaCoordFrame = new Point(locaMainFrame.x, locaMainFrame.y + mainFrame.getHeight());
		coordFrame.setLocation(locaCoordFrame);
		
		JLabel xCoords = new JLabel("  X-Coords: ");
		JLabel yCoords = new JLabel("  Y-Coords: ");
		coordFrame.add(xCoords);
		coordFrame.add(yCoords);
		coordFrame.setLayout(new GridLayout(0,2));
		return coordFrame;
	}

	private void CreateDrawingPanelWidthMargin(JFrame jF)
	{
		JPanel drawingPanel = new JPanel();
		drawingPanel.setBackground(Color.white);

		JPanel marginPanelTop = new JPanel();
		JPanel marginPanelLeft = new JPanel();
		JPanel marginPanelBot = new JPanel();
		JPanel marginPanelRight = new JPanel();

		marginPanelTop.setPreferredSize(new Dimension(0, 0));
		marginPanelLeft.setPreferredSize(new Dimension(0, 0));
		marginPanelBot.setPreferredSize(new Dimension(0, 0));
		marginPanelRight.setPreferredSize(new Dimension(0, 0));

		jF.add(drawingPanel, BorderLayout.CENTER);
		jF.add(marginPanelTop, BorderLayout.NORTH);
		jF.add(marginPanelLeft, BorderLayout.WEST);
		jF.add(marginPanelBot, BorderLayout.EAST);
		jF.add(marginPanelRight, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = getWidth() / 2 + (dotSize/2);
		int y = getHeight() / 2 + (dotSize/2);
		g.setColor(Color.green);
		g.fillOval(x, y, dotSize, dotSize);
	}
	
	private void consoleoutputWhenAboutButtonIsPressed(JMenuItem about)
	{
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Hier kommt was hin, was man über sein Programm erzählen könnte");
			}
		});
	}

	private void exitWithExitMenuItem(JMenuItem exit)
	{
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void createSaveButtonWindow(int centerX, int centerY, JMenuItem save)
	{
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFrame saveWindow = new JFrame("File saved!");
				saveWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				saveWindow.setSize(300, 100);

				int windowFrameX = centerX - saveWindow.getWidth() / 2;
				int windowFrameY = centerY - saveWindow.getHeight() / 2;
				saveWindow.setLocation(windowFrameX, windowFrameY);

				JButton okButton = new JButton("ok");
				okButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						saveWindow.dispose();
					}
				});

				JPanel buttonContainer = new JPanel();
				buttonContainer.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
				buttonContainer.add(okButton);
				saveWindow.add(buttonContainer);

				saveWindow.setVisible(true);
				;
			}
		});
	}

	public static void main(String[] args)
	{
		new SwingFenster();
	}
}
