package uebung1;

import java.awt.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.*;

public class SliderUebung3 implements ChangeListener{
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	JSlider slider;
	
	SliderUebung3(){
		
		frame = new JFrame("FloatSlider");	//erstellt das Fenster mit Namen
		panel = new JPanel();				//erstellt 
		label = new JLabel();
		slider = new JSlider(0,3600,1800);
		
		slider.setPreferredSize(new Dimension(400,100));
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(300);
		slider.setMajorTickSpacing(1800);
		
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put(0,  new JLabel("0.0"));
		table.put(1800,  new JLabel("180.0"));
		table.put(3600,  new JLabel("360.0"));
		slider.setLabelTable(table);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		
		label.setText("180.0");
		
		panel.add(slider);
		panel.add(label);
		frame.add(panel);
		frame.setSize(435,180);
		frame.setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		float x = slider.getValue()/10f;
		label.setText(""+x);
		
	}
	public static void main(String[] args) {
		SliderUebung3 sliderDemo = new SliderUebung3();
	}
}
