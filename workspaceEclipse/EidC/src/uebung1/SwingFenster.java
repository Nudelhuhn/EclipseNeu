package uebung1;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SwingFenster extends JFrame {

	public SwingFenster() {
		//GUI
		setTitle("Übung 1");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Button
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Menu
		JMenuBar menu = new JMenuBar();
		JMenu datei = new JMenu("Datei");
		JMenu hilfe = new JMenu("Hilfe");
		JMenuItem speichern = new JMenuItem("Speichern");
		JMenuItem beenden = new JMenuItem("Beenden");
		JMenuItem ueber = new JMenuItem("Über SwingFenster");
		beenden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ueber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hier steht entsprechender Text zur entsprechenden Funktion.");
			}
		});
		
		menu.add(datei);
		datei.add(speichern);
		datei.add(beenden);
		menu.add(hilfe);
		hilfe.add(ueber);
		setJMenuBar(menu);
		
		//Layout
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(button));
		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(button));
		gl.setAutoCreateContainerGaps(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SwingFenster a = new SwingFenster();
				a.setVisible(true);
			}
		});
	}
}
