package uebung1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SwingFenster2{
	
	public SwingFenster2() {
		JFrame jf = new JFrame("SwingFenster2");
		jf.setLocation(200,100);
		jf.setSize(800,600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		JMenu datei = new JMenu("Datei");
		JMenu hilfe = new JMenu("Hilfe");
		JMenuItem speichern = new JMenuItem("Speichern");
		JMenuItem beenden = new JMenuItem("Beenden");
		JMenuItem ueber = new JMenuItem("ueber SwingFenster2");
		
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
				System.out.println("Das ist eine Konsolenausgabe");
			}
		});
		speichern.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Datei wurde nicht gespeichert");
			}
		});
		
		menu.add(datei);
		datei.add(speichern);
		datei.add(beenden);
		menu.add(hilfe);
		hilfe.add(ueber);
		
		jf.setJMenuBar(menu);
		
		
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		SwingFenster2 test = new SwingFenster2();
	}
}