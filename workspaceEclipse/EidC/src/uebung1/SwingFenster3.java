package uebung1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SwingFenster3 extends JPanel
{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillOval(100, 100, 10, 10);//(positionX,positionY,Breite,Höhe)
	}

	
	
	public static void main(String[] args)
	{
		JFrame frameController = new JFrame("Übung 1");
		frameController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameController.setSize(300, 600);
		frameController.setLocation(900, 100);

		JMenuBar menuBar = new JMenuBar();
		JMenu datei = new JMenu("Datei");
		JMenu hilfe = new JMenu("Hilfe");

		JMenuItem speichern = new JMenuItem("Speichern");
		JMenuItem beenden = new JMenuItem("Beenden");
		JMenuItem ueber = new JMenuItem("Ueber SwingFenster3");

		menuBar.add(datei);
		menuBar.add(hilfe);
		datei.add(speichern);
		datei.add(beenden);
		hilfe.add(ueber);
		frameController.setJMenuBar(menuBar);

		beenden.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		ueber.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Information über dieses Swing Fenster");
			}
		});
		
		frameController.setVisible(true);
		
		int frameWidth = 600;
    	int frameHeight = 380;
    	
        JFrame framePoint = new JFrame("Inneres Fenster");
        framePoint.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel outerPanel = new JPanel(); // Haupt-JPanel
        outerPanel.setPreferredSize(new Dimension(frameWidth, frameHeight)); // Größe des Hauptfensters
        framePoint.getContentPane().add(outerPanel); // fügt das Haupt-JPanel zum JFrame hinzu

        JPanel innerPanel1 = new InneresFenster(); // JPanel für das innere Fenster
        innerPanel1.setBackground(Color.WHITE);
        innerPanel1.setPreferredSize(new Dimension(frameWidth-10, frameHeight-10));
        outerPanel.add(innerPanel1, BorderLayout.NORTH); // fügt das innere JPanel zum äußeren JPanel hinzu

        framePoint.pack();
        framePoint.setLocation(frameController.getLocationOnScreen().x - (2*frameController.getWidth()+2), frameController.getLocationOnScreen().y); // platziert das Fenster in der Mitte des Bildschirms
        framePoint.setVisible(true);
	}
}
