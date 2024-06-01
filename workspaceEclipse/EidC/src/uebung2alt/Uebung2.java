package uebung2alt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

public class Uebung2 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        int x = getWidth() / 2 - 5; // x-Koordinate des grünen Punkts
        int y = getHeight() / 2 - 5; // y-Koordinate des grünen Punkts
        g.fillOval(x, y, 10, 10); // zeichnet einen grünen Punkt in der Mitte
    }

    public static void main(String[] args) {
    	int frameWidth = 1400;
    	int frameHeight = 760;
    	
    	//Fenster
        JFrame frame = new JFrame("Inneres Fenster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(frameWidth+20, frameHeight+20));
        frame.setResizable(false);

        //Baufläche des Fensters
        JPanel grundGeruest = new JPanel(); // Haupt-JPanel
        grundGeruest.setPreferredSize(new Dimension(frameWidth, frameHeight)); // Größe des Hauptfensters
        grundGeruest.setLayout(new BorderLayout());
        frame.add(grundGeruest); // fügt das Haupt-JPanel zum JFrame hinzu
        
        //Baufläche für die Transformationsmatrizen
        JPanel panelTraMatr = new JPanel();
        panelTraMatr.setLayout(new BoxLayout(panelTraMatr, BoxLayout.X_AXIS));
        panelTraMatr.setBorder(BorderFactory.createTitledBorder("Transformationsmatrizen"));
        panelTraMatr.setPreferredSize(new Dimension((int)(frameWidth*0.992), (int)(frameHeight*0.195)));
        grundGeruest.add(panelTraMatr, BorderLayout.NORTH); // fügt das innere JPanel zum äußeren JPanel hinzu
        
        float[][] a = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(a));
		float[][] b = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(b));
		float[][] c = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(c));
		float[][] d = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(d));
		float[][] f = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(f));
		float[][] g = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelTraMatr.add(new MatrixViewFloat(g));
             
        //Baufläche für Drehmatrizen
        JPanel panelDrehMatr = new JPanel();
        panelDrehMatr.setLayout(new BoxLayout(panelDrehMatr, BoxLayout.Y_AXIS));
        panelDrehMatr.setBorder(BorderFactory.createTitledBorder("Drehmatrizen"));
        panelDrehMatr.setPreferredSize(new Dimension((int)(frameWidth*0.16), (int)(frameHeight*0.765)));
        grundGeruest.add(panelDrehMatr, BorderLayout.WEST);
        
        float[][] h = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelDrehMatr.add(new MatrixViewFloat(h));
        float[][] i = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelDrehMatr.add(new MatrixViewFloat(i));
		float[][] j = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		panelDrehMatr.add(new MatrixViewFloat(j));
        
        //Zeichenfläche
        JPanel drawingSpace = new Uebung2();
        drawingSpace.setBorder(BorderFactory.createTitledBorder(
        	    BorderFactory.createTitledBorder(""), "Zeichenfläche",
        	    TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
        	    new Font("Arial", Font.BOLD, 9)));
        grundGeruest.add(drawingSpace, BorderLayout.CENTER);
        
        //Baufläche für Controller
        JPanel panelCon = new JPanel();
        panelCon.setLayout(new BoxLayout(panelCon, BoxLayout.Y_AXIS));
        panelCon.setBorder(BorderFactory.createTitledBorder("Controller"));
        grundGeruest.add(panelCon, BorderLayout.EAST);
        
        JSlider s1 = new JSlider(-5000,5000,0);
        s1.setBorder(BorderFactory.createTitledBorder("X-Zentrum = 0"));
        panelCon.add(s1);
        s1.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s1.setBorder(BorderFactory.createTitledBorder("X-Zentrum = " + curr_value));
		});
        
        JSlider s2 = new JSlider(-5000,5000,0);
        s2.setBorder(BorderFactory.createTitledBorder("Y-Zentrum = 0"));
        panelCon.add(s2);
        s2.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s2.setBorder(BorderFactory.createTitledBorder("Y-Zentrum = " + curr_value));
		});
        
        JSlider s3 = new JSlider(-5000,5000,0);
        s3.setBorder(BorderFactory.createTitledBorder("Z-Zentrum = 0"));
        panelCon.add(s3);
        s3.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s3.setBorder(BorderFactory.createTitledBorder("Z-Zentrum = " + curr_value));
		});
        
        JSlider s4 = new JSlider(0,3600,1800);
        s4.setBorder(BorderFactory.createTitledBorder("Alpha um Z-Achse = 180.0"));
        panelCon.add(s4);
        s4.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s4.setBorder(BorderFactory.createTitledBorder("Alpha um Z-Achse = " + curr_value));
		});
        
        JSlider s5 = new JSlider(0,3600,1800);
        s5.setBorder(BorderFactory.createTitledBorder("Beta um X-Achse = 180.0"));
        panelCon.add(s5);
        s5.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s5.setBorder(BorderFactory.createTitledBorder("Beta um X-Achse = " + curr_value));
		});
        
        JSlider s6 = new JSlider(0,3600,1800);
        s6.setBorder(BorderFactory.createTitledBorder("Gamme um Y-Achse = 180.0"));
        panelCon.add(s6);
        s6.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s6.setBorder(BorderFactory.createTitledBorder("Gamme um Y-Achse = " + curr_value));
		});
        
        JSlider s7 = new JSlider(0,100,50);
        s7.setBorder(BorderFactory.createTitledBorder("Scale = 5.0"));
        panelCon.add(s7);
        s7.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s7.setBorder(BorderFactory.createTitledBorder("Scale = " + curr_value));
		});
        
        JSlider s8 = new JSlider(0,19000,9500);
        s8.setBorder(BorderFactory.createTitledBorder("X-Trans = 950.0"));
        panelCon.add(s8);
        s8.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s8.setBorder(BorderFactory.createTitledBorder("X-Trans = " + curr_value));
		});
        
        JSlider s9 = new JSlider(-0,19000,9500);
        s9.setBorder(BorderFactory.createTitledBorder("Y-Trans = 950.0"));
        panelCon.add(s9);
        s9.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s9.setBorder(BorderFactory.createTitledBorder("Y-Trans = " + curr_value));
		});
        
        JSlider s10 = new JSlider(0,19000,9500);
        s10.setBorder(BorderFactory.createTitledBorder("Z-Trans = 950.0"));
        panelCon.add(s10);
        s10.addChangeListener(e ->
		{
			float curr_value = ((JSlider) e.getSource()).getValue()/10;
			s10.setBorder(BorderFactory.createTitledBorder("Z-Trans = " + curr_value));
		});
        
        //Schiebt Alle Flächen ein Stück vom unteren Rand weg
        JPanel bottomSpaceGeruest = new JPanel();
        bottomSpaceGeruest.setPreferredSize(new Dimension(frameWidth-20, 20));
        grundGeruest.add(bottomSpaceGeruest, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // platziert das Fenster in der Mitte des Bildschirms
        frame.setVisible(true);
    }
}


