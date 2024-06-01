/*
 * shortcuts:
 * import "ctrl+shift+o"
 */
package splinesProfUebungOhneHermite;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Zeichenflaeche extends JPanel implements MouseListener, MouseMotionListener
{

	// x, y
	private double[] p0 =
	{ 100, 400 };
	private double[] p1 =
	{ 1300, 400 };
	private double[] t0 =
	{ 0, 100 };
	private double[] t1 =
	{ 0, 100 };
	private Punkte punkte;

	public Zeichenflaeche()
	{
		/*
		 * - Aufruf des Standardkonstruktors von JPanel => Zeichenflaeche wird als
		 * JPanel-Objekt initialisiert
		 */
		super();
		// Einfügen der Mouse* in die Zeichenflaeche
		addMouseListener(this);
		addMouseMotionListener(this);
		// einfügen der Punkte
		punkte = new Punkte();
	}

	public void paintComponent(Graphics g)
	{
		/*
		 * - vodefiniert in Swing-Bibliothek - automatischer Aufruf wenn Komponente neu
		 * gezeichnet werden - führt Oberklasse JPanel aus => Ausführung der
		 * Standardzeichnungen vor eigenem Code
		 */
		super.paintComponent(g);
		// g2d und g Variablen worüber man malen kann
		Graphics2D g2d = (Graphics2D) g;
		// BasicStroke = Strichstärke
		g2d.setStroke(new BasicStroke(3.0f));
		// g.drawLine(int x1, int y1, int x2, int y2)
		g.drawLine((int) p0[0], (int) p0[1], (int) p1[0], (int) p1[1]);
		/*
		 * Für jede Komponente die andersfarbig sein soll, muss die Farbe neu gesetzt
		 * werden, dabei wird es immer nach der Reihenfolge ausgeführt
		 */
		g.setColor(Color.GREEN);
		// erste Tangente
		g.drawLine((int) p0[0], (int) p0[1], (int) (p0[0] + t0[0]), (int) (p0[1] + t0[1]));
		// zweite Tangente
		g.drawLine((int) p1[0], (int) p1[1], (int) (p1[0] + t1[0]), (int) (p1[1] + t1[1]));
		g.setColor(Color.RED);
		g.fillOval((int) p0[0] - 5, (int) p0[1] - 5, 10, 10);

		// Farbe der Kurve setzen
		g.setColor(Color.BLUE);
		// Startpunkte der Kurve setzen
		int pAltX = (int) p0[0];
		int pAltY = (int) p0[1];
		// Strichlänge der Strichsegmente der Kurve
		double delta = 1d / 30d;
		// erstellen der Strichsegmente der Kurve
		// das "/2" bei delta in der Schleife sorgt nur für ein noch mehr
		// annäherendes Ende
		for (double t = delta; t < 1 - delta / 2; t += delta)
		{
			/*
			 * Der Schleifenfaltor t, welcher jedes mal neu in den Hermitefunktionen
			 * eingesetzt wird, sorgt für die Aktualisierung des Punktes (pNeuX, pNeuY)
			 * 
			 * Einlesen der Hermitefunktionen zum berechnen der Strichsegmente.
			 */
			double[] tmp = hermiteBlend(t);
			/*
			 * Um die Kurvenfunktion zu berechnen, müssen alle Bindefunktionen mit den
			 * Punkten (jeweils einmal mit x und y) multipliziert werden und die Ergebnisse
			 * addiert werden (Skript 2 (Splines) Seite 16.
			 */
			int pNeuX = (int) (tmp[0] * p0[0] + tmp[1] * p1[0] + tmp[2] * t0[0] + tmp[3] * t1[0]);
			int pNeuY = (int) (tmp[0] * p0[1] + tmp[1] * p1[1] + tmp[2] * t0[1] + tmp[3] * t1[1]);
			// zeichnen der neuen Linie
			g.drawLine(pAltX, pAltY, pNeuX, pNeuY);
			// neuen Startpunkt für neue Linie setzen
			pAltX = pNeuX;
			pAltY = pNeuY;
		}
		// Die Schleife zeichnet nicht ganz bis zum Endpunkt, hiermit schon
		g.drawLine(pAltX, pAltY, (int) p1[0], (int) p1[1]);
		
		// zeichnen der Punkte durch aufrufen der benötigen Methode
		drawPunkte(g);
	}

	// Methode zum zeichnen der Punkte
	private void drawPunkte(Graphics g)
	{
		for(Punkt p : punkte) {
			g.fillOval((int)p.getX(), (int)p.getY(), 10, 10);
		}
	}

	/**
	 * - Bindefunktionen der Hermitekurve - Dient zur Berechnung der Kurvenfunktion
	 * 
	 * @param t
	 * @return Werte der Bindefunktionen
	 */
	public double[] hermiteBlend(double t)
	{
		double t2 = t * t;
		double t3 = t2 * t;

		double h1 = 2 * t3 - 3 * t2 + 1;
		double h2 = -2 * t3 + 3 * t2;
		double h3 = t3 - 2 * t2 + t;
		double h4 = t3 - t2;

		return new double[]
		{ h1, h2, h3, h4 };
	}

	public static void main(String[] args)
	{
		JFrame jf = new JFrame("Splines und so");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(100, 100);
		jf.setSize(1500, 900);

		Zeichenflaeche zf = new Zeichenflaeche();
		jf.add(zf, BorderLayout.CENTER);

		jf.setVisible(true);
	}

	/*
	 * Alles vom MouseListener automatisch eingefügte Methoden
	 * -------------------------------------------------------
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		/*
		 * Tangentenverschiebung
		 * Damit die Tangente an die Mauszeiger anknüpft, müssen die
		 * jeweiligen Koordinaten des Punktes an die die Tangente anliegt
		 * abgezogen werden
		 */
		t0[0] = e.getX() - p0[0];
		t0[1] = e.getY() - p0[1];
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Nach dem Mausklick wird ein Neuer Punkt erstellt
		// notwendige Methode = drawPunkte()
		punkte.add(new Punkt(e.getX(), e.getY()));
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
	// -------------------------------------------------------

}
