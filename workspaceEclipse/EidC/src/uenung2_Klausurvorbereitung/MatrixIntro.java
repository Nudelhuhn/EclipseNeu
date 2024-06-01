package uenung2_Klausurvorbereitung;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
public class MatrixIntro
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Matrix Intro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setLocation(100, 100);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		DrawingPanel dp = new DrawingPanel();
		frame.add(dp, BorderLayout.CENTER);

		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new BoxLayout(slider_panel, BoxLayout.Y_AXIS));
		frame.add(slider_panel, BorderLayout.EAST);

		// Create the sliders: Each slider takes a name, a min, max and default value as well as a
		//  method reference (by using the funtional interface MatrixUpdater), which is used to call the
		//  supplied function each time the slider's value changes
		slider_panel.add(new ComplexSlider("X-Rotation", -360, 360, 0, dp::updateXRotation));
		slider_panel.add(new ComplexSlider("Z-Rotation", -360, 360, 0, dp::updateZRotation));

		frame.setVisible(true);
	}
}

class DrawingPanel extends JPanel
{
	// Create the three points of our triangle
	private float[] p1 =
	{ -25, -25, 0, 1 };
	private float[] p2 =
	{ 25, -25, 0, 1 };
	private float[] p3 =
	{ 0, 25, 0, 1 };

	// Create member for our matrices (x and z rotation in this case)
	private float[][] x_rotation_;
	private float[][] z_rotation_;

	public DrawingPanel()
	{
		this.setBackground(Color.WHITE);

		// Initialize the two matrices as the identity matrix
		z_rotation_ = new float[4][4];
		x_rotation_ = new float[4][4];
		for (int i = 0; i < 4; ++i)
		{
			x_rotation_[i][i] = 1.0F;
			z_rotation_[i][i] = 1.0F;
		}
	}

	private static final int RADIUS = 5;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		// Translate the origin to the center of the drawing panel
		g2d.translate(this.getSize().width / 2, this.getSize().height / 2);
		// And invert the y axis
		g2d.scale(1, -1);

		// Calculate the complete transformation matrix here
		float[][] t_ges = Matrix.matMult(z_rotation_, x_rotation_);

		// Apply the transformations to the points
		float[] p1t = Matrix.matMult(t_ges, p1);
		float[] p2t = Matrix.matMult(t_ges, p2);
		float[] p3t = Matrix.matMult(t_ges, p3);

		// Draw the triangle, by first drawing the corners...
		g2d.fillOval((int) p1t[0] - RADIUS, (int) p1t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.fillOval((int) p2t[0] - RADIUS, (int) p2t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.fillOval((int) p3t[0] - RADIUS, (int) p3t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		// ... and then the connecting edges
		// The stroke defines the width of the lines
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p2t[0], (int) p2t[1]);
		g2d.drawLine((int) p2t[0], (int) p2t[1], (int) p3t[0], (int) p3t[1]);
		g2d.drawLine((int) p3t[0], (int) p3t[1], (int) p1t[0], (int) p1t[1]);
	}

	public void updateXRotation(float alpha)
	{
		// Build the x rotation matrix according to formula
		float cos = (float) Math.cos(Math.toRadians(alpha));
		float sin = (float) Math.sin(Math.toRadians(alpha));

		x_rotation_[1][1] = cos;
		x_rotation_[1][2] = -sin;
		x_rotation_[2][1] = sin;
		x_rotation_[2][2] = cos;

		this.repaint();
	}

	public void updateZRotation(float alpha)
	{
		// Build the z rotation matrix according to formula
		float cos = (float) Math.cos(Math.toRadians(alpha));
		float sin = (float) Math.sin(Math.toRadians(alpha));

		z_rotation_[0][0] = cos;
		z_rotation_[0][1] = -sin;
		z_rotation_[1][0] = sin;
		z_rotation_[1][1] = cos;

		this.repaint();
	}
}

class ComplexSlider extends JPanel
{
	// The conversion factor is used to convert from the sliders actual values to the float value we use in
	//  our matrix update calculations
	private static final float CONVERSION_FACTOR = 10.0F;

	public ComplexSlider(String name, float min_value, float max_value, float default_value, MatrixUpdater func)
	{
		// Create a new jslider
		// The min, max and default values are multiplied by the conversion factor to achieve a higher resolution,
		//  which gets later turned into the fraction after converting back to float
		JSlider slider = new JSlider((int) (min_value * CONVERSION_FACTOR), (int) (max_value * CONVERSION_FACTOR),
				(int) (default_value * CONVERSION_FACTOR));
		add(slider);

		// Create the border for this jpanel and use a combination of the name and the slider's current value as the title
		setBorder(
				BorderFactory.createTitledBorder(String.format("%s: %.1f", name, default_value)));

		slider.addChangeListener(e ->
		{
			// Get the sliders current value and convert it to float via the conversion factor
			float curr_value = ((JSlider) e.getSource()).getValue() / CONVERSION_FACTOR;
			// Create the border for this jpanel and use a combination of the name and the slider's current value as the title
			setBorder(
					BorderFactory
							.createTitledBorder(String.format("%s: %.1f", name, curr_value)));
			// Call the supplied function with the current value
			func.updateMatrix(curr_value);
		});
	}
}

// Functional interface that allows us to specify a function that is called when the value of a complex slider changes
interface MatrixUpdater
{
	public void updateMatrix(float value);
}