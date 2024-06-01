package uebung1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderUebung2 extends JFrame implements ChangeListener{
	
	JSlider s;
	JLabel l;
	JFrame f;
	JPanel p;
	
	public SliderUebung2() {
		setLayout(new FlowLayout());
		
		JFrame f = new JFrame("FloatSlider");
		JPanel p = new JPanel();
		
		s = new JSlider(0,3600,1800);
		add(s);
		
//		s.setMinimum(0);
//		s.setMaximum(360);
		
		s.setPaintTicks(true);	//Ticks = Striche
		s.setPaintLabels(true);	//Label = Schrift
		
		s.setMajorTickSpacing(300);
		s.setMinorTickSpacing(1800);
		
		s.addChangeListener(this);
		//sorgt für Veränderung beim Verschieben des Reglers
		
		s.setPreferredSize(new Dimension(400, 100));
		//Breite des Sliders und dessen Höhe im Fesnter
		
		l = new JLabel();
		l.setText(""+s.getValue());
		add(l);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		p.add(s);
		p.add(l);
		f.add(p);
		f.setSize(440,180);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new SliderUebung2();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		l.setText(""+s.getValue());
	}

}
