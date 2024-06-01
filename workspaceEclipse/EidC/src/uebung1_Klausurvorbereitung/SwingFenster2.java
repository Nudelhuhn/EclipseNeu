package uebung1_Klausurvorbereitung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SwingFenster2
{

	static int screenWidth = 1920;
	static int screenHeight = 1080;

	private static JLabel xCoordsLabel;
	private static JLabel yCoordsLabel;
	
	private static int dotColorRed = 0;
	private static int dotColorGreen = 255;
	private static int dotColorBlue = 0;
	
	private static int scaling = 1;

	public static void main(String[] args)
	{
		new SwingFenster2();
	}

	public SwingFenster2()
	{
		int mainFrameWidth = 1200;
		int mainFrameHeight = 800;

		JFrame mainFrame = new JFrame("Dot Controller");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(mainFrameWidth, mainFrameHeight);
		mainFrame.setLocation(screenWidth / 2 - mainFrameWidth / 2, screenHeight / 2 - mainFrameHeight / 2);
		mainFrame.setLayout(new BorderLayout(/* margin */10, 10));

		DrawingPanel drawingPanel = new DrawingPanel();
		JPanel controlPanelTop = new JPanel(); // Margin
		JPanel controlPanelLeft = new JPanel(); // Margin
		JPanel coordinatesPanel = new JPanel();
		JPanel sliderPanel = new JPanel();

		drawingPanel.setBackground(Color.white);

		drawingPanel.setPreferredSize(new Dimension(0, 0));
		controlPanelTop.setPreferredSize(new Dimension(0, 0));
		controlPanelLeft.setPreferredSize(new Dimension(0, 0));
		coordinatesPanel.setPreferredSize(new Dimension(0, 25));
		sliderPanel.setPreferredSize(new Dimension(250, 0));

		mainFrame.add(drawingPanel, BorderLayout.CENTER);
		mainFrame.add(controlPanelTop, BorderLayout.NORTH);
		mainFrame.add(controlPanelLeft, BorderLayout.WEST);
		mainFrame.add(coordinatesPanel, BorderLayout.SOUTH);
		mainFrame.add(sliderPanel, BorderLayout.EAST);

		coordinatesPanel.setLayout(new GridLayout(0, 3));
		xCoordsLabel = new JLabel("\tX-Coords: " + drawingPanel.getPointX());
		yCoordsLabel = new JLabel("Y-Coords: " + drawingPanel.getPointY());
		coordinatesPanel.add(xCoordsLabel);
		coordinatesPanel.add(yCoordsLabel);

		Slider dotRGBRed = new Slider(0, 255, dotColorRed, "Red", drawingPanel::updateColor);
		Slider dotRGBGreen = new Slider(0, 255, dotColorGreen, "Green", drawingPanel::updateColor);
		Slider dotRGBBlue = new Slider(0, 255, dotColorBlue, "Blue", drawingPanel::updateColor);
		
		JSlider dotScaling = new JSlider(0,50,1);
		JPanel dotScalingFormatPanel = new JPanel();
		dotScalingFormatPanel.add(dotScaling);
		dotScaling.setPaintTicks(true);
		dotScaling.setMinorTickSpacing(10);
		dotScaling.setMajorTickSpacing(25);
		dotScaling.setPaintLabels(true);
		dotScalingFormatPanel.setBorder(BorderFactory.createTitledBorder(null, String.format("%s: %d", "Scaling", 1), 0, 0,
				(new Font("Arial", Font.PLAIN, 12)), Color.black));
		dotScaling.addChangeListener(e -> {
			int currValue = ((JSlider) e.getSource()).getValue();
			dotScalingFormatPanel.setBorder(BorderFactory.createTitledBorder(null, String.format("%s: %d", "Scaling", currValue), 0, 0,
					(new Font("Arial", Font.PLAIN, 12)), Color.black));
			drawingPanel.updateScaling(currValue);
		});
		
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		sliderPanel.add(dotRGBRed);
		sliderPanel.add(dotRGBGreen);
		sliderPanel.add(dotRGBBlue);
		sliderPanel.add(dotScalingFormatPanel);

		mainFrame.setVisible(true);
	}

	public static int getScreenWidth()
	{
		return screenWidth;
	}

	public static int getScreenHeight()
	{
		return screenHeight;
	}

	public static JLabel getXCoordsLabel()
	{
		return xCoordsLabel;
	}

	public static JLabel getYCoordsLabel()
	{
		return yCoordsLabel;
	}
	
	public static int getDotColorRed() {
		return dotColorRed;
	}
	public static int getDotColorGreen() {
		return dotColorGreen;
	}
	public static int getDotColorBlue() {
		return dotColorBlue;
	}
	public static void setDotColorRed(int value) {
		dotColorRed = value;
	}
	public static void setDotColorGreen(int value) {
		dotColorGreen = value;
	}
	public static void setDotColorBlue(int value) {
		dotColorBlue = value;
	}
	public static int getScaling() {
		return scaling;
	}
	public static void setScaling(int value) {
		scaling = value;
	}
}

class DrawingPanel extends JPanel implements MouseMotionListener, MouseListener
{
	int dotLocationX = SwingFenster2.getScreenWidth() / 4;
	int dotLocationY = SwingFenster2.getScreenHeight() / 4;
	boolean mouseIsPressed = false;

	public DrawingPanel()
	{
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (mouseIsPressed)
			movePoint(e.getX(), e.getY());
		updateCoordinatesLabel();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (mouseIsPressed)
			movePoint(e.getX(), e.getY());
		updateCoordinatesLabel();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		mouseIsPressed = true;
		movePoint(e.getX(), e.getY());
		updateCoordinatesLabel();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		mouseIsPressed = false;
	}

	private void movePoint(int x, int y)
	{
		dotLocationX = x;
		dotLocationY = y;
		repaint();
	}

	public void updateCoordinatesLabel()
	{
		SwingFenster2.getXCoordsLabel().setText("\tX-Coords: " + dotLocationX);
		SwingFenster2.getYCoordsLabel().setText("Y-Coords: " + dotLocationY);
	}

	Dot dot = new Dot(30);
	public void updateColor(int r, int g, int b) {
		dot.changeColor(r,g,b);
		repaint();
	}
	public void updateScaling(int value) {
		dot.setDotSize(value);
		repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(new Color(dot.getRedValue(),dot.getGreenValue(),dot.getBlueValue()));
		g.fillOval(dotLocationX - (dot.getDotSize() / 2), dotLocationY - (dot.getDotSize() / 2), dot.getDotSize(), dot.getDotSize());
	}

	public int getPointX()
	{
		return dotLocationX;
	}

	public int getPointY()
	{
		return dotLocationY;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}

class Slider extends JPanel
{
	public Slider(int min, int max, int defValue, String name, PlaceholderFunction func)
	{
		JSlider slider = new JSlider(min, max, defValue);

		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(max / 3);
		slider.setMajorTickSpacing(max);
		slider.setPaintLabels(true);
		setBorder(BorderFactory.createTitledBorder(null, String.format("%s: %d", name, defValue), 0, 0,
				(new Font("Arial", Font.PLAIN, 12)), Color.black));
		add(slider);
		slider.addChangeListener(e -> {
			int currValue = ((JSlider) e.getSource()).getValue();
			setBorder(BorderFactory.createTitledBorder(null, String.format("%s: %d", name, currValue), 0, 0,
					(new Font("Arial", Font.PLAIN, 12)), Color.black));
			if(name.equals("Red")) {
				SwingFenster2.setDotColorRed(currValue);
			}else if(name.equals("Green")) {
				SwingFenster2.setDotColorGreen(currValue);
			}else if(name.equals("Blue")) {
				SwingFenster2.setDotColorBlue(currValue);
			}
			func.updateColor(SwingFenster2.getDotColorRed(),SwingFenster2.getDotColorGreen(),SwingFenster2.getDotColorBlue());
		});
	}
}

class Dot {
	int dotSize;
	int red,green,blue;
	
	public Dot(int size, int r, int g, int b) {
		dotSize = size;
		red = r;
		green = g;
		blue = b;
	}
	public Dot() {
		this(10,0,255,0);
	}
	public Dot(int size) {
		this(size,0,255,0);
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
	public int getDotSize() {
		return dotSize;
	}
	public void setDotSize(int value) {
		dotSize = 1*value*value;
	}
}

// Only makes sense, if all Sliders will only use this one functional Method of the functional interface
interface PlaceholderFunction{
	public void updateColor(int r, int g, int b);
}