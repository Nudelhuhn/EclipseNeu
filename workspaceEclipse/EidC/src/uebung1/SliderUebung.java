package uebung1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

public class SliderUebung {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JButton button = new JButton("Click me");
//		button.addActionListener(e ->{ //Lambda = Methode ohne Kopf
//			System.out.println(String.format("Button with Text %s pressed", ((JButton) e.getSource()).getText()));
		//%s Platzhalter für Strings
//		});
		button.addActionListener(new ButtonActionHandler());
		frame.add(button);
		
		JSlider slider = new JSlider(0,100,10);
		frame.add(slider);
		slider.addChangeListener(e ->{
			int current_value = ((JSlider) e.getSource()).getValue();
			System.out.println(String.format("Slider value is %d", current_value)); 
		//%d Platzhalter für Dezimalzahlen
		});

		JLabel label = new JLabel("Hallo ECG");
		frame.add(label);
		
		JMenuBar menu_bar = new JMenuBar();
		frame.setJMenuBar(menu_bar);
		
		JMenu menu = new JMenu("Menu 1");
		menu_bar.add(menu);
		
		JMenuItem menu_item = new JMenuItem("Menu Item");
		menu_item.addActionListener(e -> System.out.println("Menuitem clicked!"));
		menu.add(menu_item);
		
		JMenu menu2 = new JMenu("Menu 2");
		menu_bar.add(menu2);
		JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem("Enable me");
		checkbox.addActionListener(e -> {
			boolean is_selected = ((JCheckBoxMenuItem) e.getSource()).isSelected();
			System.out.println(String.format("Checkbox is %b", is_selected));
		});
		menu2.add(checkbox);

		frame.pack(); // Schrumpft Fenster auf Inhalt
		frame.setVisible(true);
	}

}

class ButtonActionHandler implements ActionListener {

	private int counter = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		counter++;
		button.setText(String.format("Button has been clicked %d times", counter));
	}
}
