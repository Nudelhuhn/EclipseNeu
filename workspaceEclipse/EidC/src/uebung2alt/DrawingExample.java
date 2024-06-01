package uebung2alt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DrawingExample
{
	public static void main(String[] args)
	{
		// Create the JFrame
		JFrame frame = new JFrame("Drawing Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setResizable(true);
		// This time use a border layout instead of the box layout
		frame.setLayout(new BorderLayout());

		// Create a new JPanel, which is essentially a container for more ui elements
		//  and can use a new layout manager
		JPanel panel = new JPanel();
		// We use the flow layout instead of the box layout here, because it allows us to
		//  specify a nice visual gap between the elements we put in it
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		// Add the new jpanel to the frame, specifying the BorderLayout.NORTH constraint
		//  that tells the frame's border layout to put this jpanel into the north-region
		frame.add(panel, BorderLayout.NORTH);

		// Create two labels for the x- and y- coordinates and put them into the recently created jpanel
		JLabel x_coord = new JLabel("undef");
		panel.add(x_coord);
		JLabel y_coord = new JLabel("undef");
		panel.add(y_coord);

		// Create our custom drawing panel and add it to the frame, specifying the center region as its constraint
		DrawingPanel dp = new DrawingPanel();
		frame.add(dp, BorderLayout.CENTER);

		// Show the frame
		frame.setVisible(true);
	}
}

// The drawing panel is essentially a JPanel with a bit of added functionality
class DrawingPanel extends JPanel
{
	public DrawingPanel()
	{
		// Set the background color of the JPanel
		setBackground(Color.WHITE);
	}
}