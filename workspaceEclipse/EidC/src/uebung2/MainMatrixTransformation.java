package uebung2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainMatrixTransformation extends JFrame
{
	public static float[][] unitmatrix = { 
			{ 1, 0, 0, 0 }, 
			{ 0, 1, 0, 0 },
			{ 0, 0, 1, 0 }, 
			{ 0, 0, 0, 1 } };
	
	public static MatrixViewFloat translation_view = new MatrixViewFloat(unitmatrix, "Translation");
	public static MatrixViewFloat back_view = new MatrixViewFloat(unitmatrix, "Rück");
	public static MatrixViewFloat rotation_view = new MatrixViewFloat(unitmatrix, "Rotation");
	public static MatrixViewFloat scale_view = new MatrixViewFloat(unitmatrix, "Skalierung");
	public static MatrixViewFloat center_view = new MatrixViewFloat(unitmatrix, "Zentrum");
	public static MatrixViewFloat total_view = new MatrixViewFloat(unitmatrix, "Gesamt");
	
	public static MatrixViewFloat alpha_view = new MatrixViewFloat(unitmatrix, "alpha");
	public static MatrixViewFloat beta_view = new MatrixViewFloat(unitmatrix, "beta");
	public static MatrixViewFloat gamma_view = new MatrixViewFloat(unitmatrix, "gamma");

	public MainMatrixTransformation() {
		//Ertellung des JFrame (keine Objektbezeichnung vor den Methoden notwendig, da Main JFrame erweitert
		setTitle("AggressivGewaltigesMatrizenTransformationsUnternehmungsProgramm");
//		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		//Erstellung des Panels für die Zeichenfläche
		DrawingPanel dp = new DrawingPanel();
		add(dp, BorderLayout.CENTER);

		//Erstellung des Panels für die JSlider
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new BoxLayout(slider_panel, BoxLayout.Y_AXIS));
		add(slider_panel, BorderLayout.EAST);
		
		slider_panel.add(new ComplexSlider("X-Zentrum", -500, 500, 0, dp::xCenterTranslation));
		slider_panel.add(new ComplexSlider("Y-Zentrum", -500, 500, 0, dp::yCenterTranslation));
		slider_panel.add(new ComplexSlider("Z-Zentrum", -500, 500, 0, dp::zCenterTranslation));
		slider_panel.add(new ComplexSlider("Beta um X-Achse", 0, 360, 0, dp::xRotation));
		slider_panel.add(new ComplexSlider("Gamma um Y-Achse", 0, 360, 0, dp::yRotation));
		slider_panel.add(new ComplexSlider("Alpha um Z-Achse", 0, 360, 0, dp::zRotation));
		slider_panel.add(new ComplexSlider("Scale", 0, 10, 1, dp::scale));
		slider_panel.add(new ComplexSlider("X-Trans", -950, 950, 0, dp::xTranslation));
		slider_panel.add(new ComplexSlider("Y-Trans", -950, 950, 0, dp::yTranslation));
		slider_panel.add(new ComplexSlider("Z-Trans", -950, 950, 0, dp::zTranslation));
		
		//Erstellung des Panels für die Transformationsmatrizen
		JPanel tm = new JPanel();
		tm.setPreferredSize(new Dimension(tm.getWidth(), 100));
		tm.setLayout(new BoxLayout(tm, BoxLayout.X_AXIS));
		tm.add(translation_view);
		tm.add(back_view);
		tm.add(rotation_view);
		tm.add(scale_view);
		tm.add(center_view);
		tm.add(total_view);
		add(tm, BorderLayout.NORTH);
		
		//Erstellung des Panels für die Drehmatrizen
		JPanel dm = new JPanel();
		dm.setPreferredSize(new Dimension(150, dm.getHeight()));
		dm.setLayout(new BoxLayout(dm, BoxLayout.Y_AXIS));
		dm.add(alpha_view);
		dm.add(beta_view);
		dm.add(gamma_view);
		add(dm, BorderLayout.WEST);
		
		pack();
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MainMatrixTransformation();
	}

}
