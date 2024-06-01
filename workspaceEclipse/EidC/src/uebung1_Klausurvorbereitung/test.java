package uebung1_Klausurvorbereitung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class test extends JPanel
{
	
	public static void main(String[] args) {
		new test();
		Dot dot = new Dot();
		System.out.println(dot.getRedValue());
		System.out.println(dot.getGreenValue());
		System.out.println(dot.getBlueValue());
	}
	
	int redValue = 0;
	int greenValue = 255;
	int blueValue = 0;
	public test() {
		JFrame frame = new JFrame("test");
		frame.setSize(600,600);
		frame.setLocation(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel sliderPanel = new JPanel();
		DrawingPanel2 drawingPanel = new DrawingPanel2();
		
		drawingPanel.setBackground(Color.white);
		
		frame.add(sliderPanel, BorderLayout.EAST);
		frame.add(drawingPanel, BorderLayout.CENTER);
		
		JSlider red = new JSlider(0,255,0);
		JSlider green = new JSlider(0,255,255);
		JSlider blue = new JSlider(0,255,0);
		
		sliderPanel.setLayout(new GridLayout(3,0));
		sliderPanel.add(red);
		sliderPanel.add(green);
		sliderPanel.add(blue);
		
		red.addChangeListener(e -> {
			redValue = ((JSlider) e.getSource()).getValue();
			drawingPanel.updateColor(redValue, greenValue, blueValue);
		});
		green.addChangeListener(e -> {
			greenValue = ((JSlider) e.getSource()).getValue();
			drawingPanel.updateColor(redValue, greenValue, blueValue);
		});
		blue.addChangeListener(e -> {
			blueValue = ((JSlider) e.getSource()).getValue();
			drawingPanel.updateColor(redValue, greenValue, blueValue);
		});
		
		frame.setVisible(true);
	}
}

class DrawingPanel2 extends JPanel {
	Dot2 dot = new Dot2();
	public void updateColor(int r, int g, int b) {
		dot.changeColor(r,g,b);
		repaint();
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(dot.getRedValue(),dot.getGreenValue(),dot.getBlueValue()));
        g.fillOval(250, 250, 30, 30);
    }
}

class Dot2 {
	int dotSize;
	int red,green,blue;
	
	public Dot2(int size, int r, int g, int b) {
		dotSize = size;
		red = r;
		green = g;
		blue = b;
	}
	public Dot2() {
		this(10,0,255,0);
	}
	public void changeColor(int r, int g, int b) {
		red = r;
		green = g;
		blue = b;
	}
	
	public int getRedValue() {
		return red;
	}
	public int getGreenValue() {
		return green;
	}
	public int getBlueValue() {
		return blue;
	}
}