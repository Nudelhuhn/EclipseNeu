package uebung2;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

public class DrawingPanel extends JPanel
{
	// Initialisierung der Punkte der Grafik
	float p1_x = -40;
	float p1_y = -40;
	float p1_z = -40;
	float p1_1 = 1;
	
	float p2_x = 40;
	float p2_y = -40;
	float p2_z = -40;
	float p2_1 = 1;
	
	float p3_x = 0;
	float p3_y = 20;
	float p3_z = 0;
	float p3_1 = 1;
	
	float p4_x = 0;
	float p4_y = -40;
	float p4_z = 40;
	float p4_1 = 1;
	
	private float[] p1 =
	{ p1_x, p1_y, p1_z, p1_1 };
	private float[] p2 =
	{ p2_x, p2_y, p2_z, p2_1 };
	private float[] p3 =
	{ p3_x, p3_y, p3_z, p3_1 };
	private float[] p4 =
	{ p4_x, p4_y, p4_z, p4_1 };

	// Deklaration Punkte für Matrix-Multiplikation
	float[] p1t;
	float[] p2t;
	float[] p3t;
	float[] p4t;

	// Deklaration der Transformationsmatrizen
	private float[][] translation_;
	
	private float[][] cent_back_translation_;
	
	private float[][] x_rotation_;
	private float[][] y_rotation_;
	private float[][] z_rotation_;
	
	private float[][] cent_translation_;
		
	private float[][] scaling_;
	
	// Methode zum Zeichnen der Grafik
	public DrawingPanel()
	{
		// Farbe und Größe der Zeichenfläche
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(1000,0));
		
		// Initialisierung der Transformationsmatrizen
		translation_ = new float[4][4];
		
		cent_translation_ = new float[4][4];

		x_rotation_ = new float[4][4];
		y_rotation_ = new float[4][4];
		z_rotation_ = new float[4][4];
		
		scaling_ = new float[4][4];
		
		cent_back_translation_ = new float[4][4];
		
		for (int i = 0; i < 4; ++i)
		{
			// Anfangswert als Einheitsmatrizen
			translation_[i][i] = 1.0F;
			
			cent_back_translation_[i][i] = 1.0F;
			
			x_rotation_[i][i] = 1.0F;
			y_rotation_[i][i] = 1.0F;
			z_rotation_[i][i] = 1.0F;
			
			scaling_[i][i] = 1.0F;
			
			cent_translation_[i][i] = 1.0F;
		}
	}

	private static final int RADIUS = 5;

	@Override
	protected void paintComponent(Graphics g)
	{
		//Zeichnen aller benötigten Standardkomponente
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//Setzt das Zentrum zum angegebenen Wert
		g2d.translate(getSize().width/2, getSize().height/2);
		// Invertierung der Y-Achse
		g2d.scale(1, -1);
		
		// Transformationsmatrix der Rotationen
		float[][] rot_mat = Matrix.matMult(x_rotation_, Matrix.matMult(y_rotation_, z_rotation_));
		// Gesamte Transformationsmatrix
		float[][] t_ges = Matrix.matMult(translation_, Matrix.matMult(cent_back_translation_, Matrix.matMult(rot_mat, 
				Matrix.matMult(scaling_, cent_translation_))));
		
		// Anwendung der Transformation auf Punkten der Grafik
		p1t = Matrix.matMult(t_ges, p1);
		p2t = Matrix.matMult(t_ges, p2);
		p3t = Matrix.matMult(t_ges, p3);
		p4t = Matrix.matMult(t_ges, p4);

		// Zeichnen der Punkte
		g2d.setColor(Color.black);
		g2d.fillOval((int) p1t[0] - RADIUS, (int) p1t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.setColor(Color.black);
		g2d.fillOval((int) p2t[0] - RADIUS, (int) p2t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.setColor(Color.black);
		g2d.fillOval((int) p3t[0] - RADIUS, (int) p3t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		g2d.setColor(Color.black);
		g2d.fillOval((int) p4t[0] - RADIUS, (int) p4t[1] - RADIUS, RADIUS * 2, RADIUS * 2);
		//Punkt für zentrum
		g2d.setColor(Color.RED);
		g2d.fillOval((int) -cent_translation_[0][3], (int) -cent_translation_[1][3], RADIUS, RADIUS);
		// Zeichnen der Linien zwischen den Punkten, zur Bildung des Tetraeders
		g2d.setStroke(new BasicStroke(5));	// Stroke = Breite der Linien
		g2d.setColor(Color.blue);
		g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p2t[0], (int) p2t[1]);
		g2d.setColor(Color.red);
		g2d.drawLine((int) p3t[0], (int) p3t[1], (int) p2t[0], (int) p2t[1]);
		g2d.setColor(Color.yellow);
		g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p3t[0], (int) p3t[1]);
		g2d.setColor(Color.green);
		g2d.drawLine((int) p2t[0], (int) p2t[1], (int) p4t[0], (int) p4t[1]);
		g2d.setColor(Color.black);
		g2d.drawLine((int) p3t[0], (int) p3t[1], (int) p4t[0], (int) p4t[1]);
		g2d.setColor(Color.orange);
		g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p4t[0], (int) p4t[1]);
		
		// Transformationsmatrizen nach jeder Veränderung neu schreiben
		Main.translation_view.setMatrix(translation_);
		Main.back_view.setMatrix(cent_back_translation_);
		Main.rotation_view.setMatrix(rot_mat);
		Main.scale_view.setMatrix(scaling_);
		Main.center_view.setMatrix(cent_translation_);
		Main.total_view.setMatrix(t_ges);
		
		Main.alpha_view.setMatrix(x_rotation_);
		Main.beta_view.setMatrix(y_rotation_);
		Main.gamma_view.setMatrix(z_rotation_);
		
	}
	
	public void xCenterTranslation(float slider_curr_value) {
		cent_translation_[0][3] = slider_curr_value;
		cent_back_translation_[0][3] = cent_translation_[0][3] * (-1);
		
		repaint();
	}
	public void yCenterTranslation(float slider_curr_value) {
		cent_translation_[1][3] = slider_curr_value;
		cent_back_translation_[1][3] = cent_translation_[1][3] * (-1);
		
		repaint();
	}
	public void zCenterTranslation(float slider_curr_value) {
		cent_translation_[2][3] = slider_curr_value;
		cent_back_translation_[2][3] = cent_translation_[2][3] * (-1);
		
		repaint();
	}
	
	public void xTranslation(float slider_curr_value) {
		
		translation_[0][3] = p1_x + slider_curr_value;
		
		repaint();
	}
	
	public void yTranslation(float slider_curr_value) {
		
		translation_[1][3] = p2_y + slider_curr_value;
		
		repaint();
	}
	
	public void zTranslation(float slider_curr_value) {
		
		translation_[2][3] = p3_z + slider_curr_value;
		
		repaint();
	}
	
	public void scale(float slider_curr_value) {

		
		scaling_[0][0] = 1*slider_curr_value;
		scaling_[1][1] = 1*slider_curr_value;
		scaling_[2][2] = 1*slider_curr_value;
		scaling_[3][3] = 1;
		
		repaint();
		
	}
	
	public void xRotation(float slider_curr_value)
	{
		float cos = (float) Math.cos(Math.toRadians(slider_curr_value));
		float sin = (float) Math.sin(Math.toRadians(slider_curr_value));

		x_rotation_[1][1] = cos;
		x_rotation_[1][2] = -sin;
		x_rotation_[2][1] = sin;
		x_rotation_[2][2] = cos;

		repaint();
	}

	public void yRotation(float slider_curr_value)
	{
		float cos = (float) Math.cos(Math.toRadians(slider_curr_value));
		float sin = (float) Math.sin(Math.toRadians(slider_curr_value));

		y_rotation_[0][0] = cos;
		y_rotation_[0][2] = sin;
		y_rotation_[2][0] = -sin;
		y_rotation_[2][2] = cos;

		repaint();
	}
	
	public void zRotation(float slider_curr_value)
	{
		float cos = (float) Math.cos(Math.toRadians(slider_curr_value));
		float sin = (float) Math.sin(Math.toRadians(slider_curr_value));

		z_rotation_[0][0] = cos;
		z_rotation_[0][1] = -sin;
		z_rotation_[1][0] = sin;
		z_rotation_[1][1] = cos;

		repaint();
	}
}

class ComplexSlider extends JPanel
{
	// CONVERSION_FACTOR wandelt die Sliderwerte in Float-Werte um
	private static final float CONVERSION_FACTOR = 10.0F;

	public ComplexSlider(String name, float min_value, float max_value, float default_value, MatrixUpdater func)
	{
		// Erstellung der JSlider
		JSlider slider = new JSlider((int) (min_value * CONVERSION_FACTOR), (int) (max_value * CONVERSION_FACTOR),
				(int) (default_value * CONVERSION_FACTOR));
		
		Font sliderFont = new Font("Arial", Font.PLAIN, 12); // Schriftart und Schriftgröße anpassen
	    slider.setFont(sliderFont);
		
		slider.setMinorTickSpacing(400);
		slider.setMajorTickSpacing(800);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put((int)(min_value*CONVERSION_FACTOR),  new JLabel(Float.toString(min_value)));
		//(min_value+max_value)/2)*CONVERSION_FACTOR dadurch wird nicht der Default-wert als Mittelwert benutzt
		// dadurch kann der Default-Wert als Startpunkt genommen werden
		// => Zeiger des Sliderfensters fängt an gewünschtem Startpunkt an und nicht zwingend wie beim Default-wert in der Mitte des SliderFensters
		table.put((int)(((min_value+max_value)/2)*CONVERSION_FACTOR),  new JLabel(Float.toString((min_value+max_value)/2)));
		table.put((int)(max_value*CONVERSION_FACTOR),  new JLabel(Float.toString(max_value)));
	    // Änderung der Schriftgröße aller Labels
	    Font labelFont = new Font("Arial", Font.BOLD, 10);
	    Enumeration<JLabel> labels = table.elements();
	    while (labels.hasMoreElements()) {
	        JLabel label = labels.nextElement();
	        label.setFont(labelFont);
	    }
		slider.setLabelTable(table);
		add(slider);
		
		// Erstellung des Slider-Fensters am Start und bei Bearbeitung
		// Der Titel passt sich je nach Slider-wert an
		setBorder(BorderFactory.createTitledBorder(null, 
				String.format("%s: %.1f", name, default_value), 0, 0, labelFont, Color.BLACK));
		slider.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue() / CONVERSION_FACTOR;
			setBorder(BorderFactory.createTitledBorder(null, 
					String.format("%s: %.1f", name, curr_value), 0, 0, labelFont, Color.BLACK));
			func.updateMatrix(curr_value);
		});
	}
}

// Interface zur Funktionsdefinition und -aufruf
interface MatrixUpdater
{
	public void updateMatrix(float value);
}