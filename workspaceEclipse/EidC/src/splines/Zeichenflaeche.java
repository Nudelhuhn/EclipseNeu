/*
 * shortcuts:
 * import "ctrl+shift+o"
 */
package splines;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 * Ich habe diese Klasse anhand der Vorlesungen auf Panopto (ECGSS2020) erstellt.
 * Die Methoden "convertListToMatrix" und "calculateBezier" wurden von mir erstellt.
 * Ansonsten habe ich bei den anderen Methoden lediglich kleine Änderungen vorgenommen und für mich kommentiert.
 */

public class Zeichenflaeche extends JPanel implements MouseListener, MouseMotionListener
{
	public static final int punktRadius = 5;
	public static Color highLightColor = Color.RED;
	private Punkte punkte;
	private HermiteNatuerlich hmN;
	private HermiteGeschlossen hmG;
	private HermiteEingespannteEnden hmEE;
	private HermiteParabolischeEnden hmPE;
	private boolean splineRecalc = true;

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
		 * - vordefiniert in Swing-Bibliothek - automatischer Aufruf wenn Komponente neu
		 * gezeichnet werden - führt Oberklasse JPanel aus => Ausführung der
		 * Standardzeichnungen vor eigenem Code
		 */
		super.paintComponent(g);
		// g2d und g Variablen zum zeichnen der Grafik
		Graphics2D g2d = (Graphics2D) g;
		// BasicStroke = Strichstärke
		g2d.setStroke(new BasicStroke(4.0f));
		g.setColor(Color.GREEN);

		drawPunkte(g);
		drawPolygon(g);
		// Hervorheben der Punkte
		if (punkte.isMarked())
		{
			g.setColor(highLightColor);
			this.drawPunkt(punkte.getMarked(), g);
		}
		// Spline zeichnen
		g.setColor(Color.BLUE);
		if (punkte.size() > 2)
		{
			// Frage ob HermiteNatürlich angezeigt werden soll
			if (MainSplines.getHermiteNat())
			{
				if (splineRecalc)
				{
					hmN = new HermiteNatuerlich(punkte);
				} else
				{
					if (hmN != null)
					{
						hmN.recalcDerivate(punkte);
					}
				}
				drawHermiteCurve(hmN, false, g);
			}
			// Frage ob HermiteGeschlossen angezeigt werden soll
			g.setColor(Color.ORANGE);
			if (MainSplines.getHermiteGes())
			{
				if (splineRecalc)
				{
					hmG = new HermiteGeschlossen(punkte);
				} else
				{
					if (hmG != null)
					{
						hmG.recalcDerivate(punkte);
					}
				}
				drawHermiteCurve(hmG, true, g);
			}
			// Frage ob HermiteEingespannteEnden angezeigt werden soll
			g.setColor(Color.YELLOW);
			if (MainSplines.getHermiteEingeEnden())
			{
				if (splineRecalc)
				{
					hmEE = new HermiteEingespannteEnden(punkte);
				} else
				{
					if (hmEE != null)
					{
						hmEE.recalcDerivate(punkte);
					}
				}
				drawHermiteCurve(hmEE, false, g);
			}
			// Frage ob HermiteParabolischeEnden angezeigt werden soll
			g.setColor(Color.CYAN);
			if (MainSplines.getHermiteParaEnden())
			{
				if (splineRecalc)
				{
					hmPE = new HermiteParabolischeEnden(punkte);
				} else
				{
					if (hmPE != null)
					{
						hmPE.recalcDerivate(punkte);
					}
				}
				drawHermiteCurve(hmPE, false, g);
			}
			// Frage ob Bezier angezeigt werden soll
			if (MainSplines.getBezier())
			{
//				// Berechne und zeichne die Bezierkurve
				g2d.setColor(Color.BLACK);
//				// Grad der Feinheit der Kurve (je niedriger desto feiner)
				double delta = 1d / 32d;

				Punkt p0 = punkte.get(0);
				int p0X = (int) p0.getX();
				int p0Y = (int) p0.getY();

				double[] p1 = null;
				int p1X = 0;
				int p1Y = 0;

				for (double t = 0; t <= 1; t += delta)
				{
					p1 = calculateBezier(convertListToMatrix(), t);
					p1X = (int) p1[0];
					p1Y = (int) p1[1];
					g2d.drawLine(p0X, p0Y, p1X, p1Y);
					p0X = p1X;
					p0Y = p1Y;
				}
			}
		}
	}

	public double[][] convertListToMatrix()
	{
		double[][] matrixForList = new double[punkte.size()][punkte.size()];
		for (int i = 0; i < punkte.size(); i++)
		{
			for (int j = 0; j < 1; j++)
			{
				matrixForList[i][j] = punkte.get(i).getX();
				matrixForList[i][j + 1] = punkte.get(i).getY();
			}
		}
		return matrixForList;
	}

	public double[] calculateBezier(double[][] matrix, double t)
	{
		if (matrix.length == 1)
		{
			return matrix[0];
		} else
		{
			/*
			 * Damti man copyOfRange verwenden kann, muss man vorher die Punktliste in
			 * converListToMatrix in eine Matrix umwandeln
			 */
			double[] P1 = calculateBezier(Arrays.copyOfRange(matrix, 0, matrix.length - 1), t);
			double[] P2 = calculateBezier(Arrays.copyOfRange(matrix, 1, matrix.length), t);
			double nt = 1.0 - t;
			double x = nt * P1[0] + t * P2[0];
			double y = nt * P1[1] + t * P2[1];
			return new double[]
			{ x, y };
		}
	}

	// Methoden zum zeichnen der Punkte
	private void drawPunkt(Punkt p, Graphics g)
	{
		g.fillOval((int) p.getX() - punktRadius, (int) p.getY() - punktRadius, punktRadius * 2, punktRadius * 2);
	}

	private void drawPunkte(Graphics g)
	{
		for (Punkt p : punkte)
		{
			drawPunkt(p, g);
		}
	}

	private void drawHermiteCurve(HermiteCurve h, boolean closed, Graphics g)
	{
		if (punkte.size() > 2)
		{
			// Punkte
			float[] pX = h.getPx();
			float[] pY = h.getPy();
			// Ableitungen
			float[] pSX = h.getpSx();
			float[] pSY = h.getpSy();
			// erster Punkte aus Hermite
			int p0X = (int) pX[0];
			int p0Y = (int) pY[0];
			int iMax = pSX.length;
			if (closed)
			{
				++iMax;
			}
			for (int i = 1; i < iMax; i++)
			{
				int im1 = i - 1;
				int im = i % pSX.length;
				// Feinheit der gezeichneten Kurve
				double delta = 1d / 32d;
				for (double t = 0; t < 1; t += delta)
				{
					double[] tmp = hermiteFunctions(t);
					int p1X = (int) (pX[im1] * tmp[0] + pX[im] * tmp[1] + pSX[im1] * tmp[2] + pSX[im] * tmp[3]);
					int p1Y = (int) (pY[im1] * tmp[0] + pY[im] * tmp[1] + pSY[im1] * tmp[2] + pSY[im] * tmp[3]);
					g.drawLine(p0X, p0Y, p1X, p1Y);
					p0X = p1X;
					p0Y = p1Y;
				}
				g.drawLine(p0X, p0Y, (int) pX[im], (int) pY[im]);
				p0X = (int) pX[im];
				p0Y = (int) pY[im];

				Color old = g.getColor();
				g.setColor(Color.RED);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(3.0f));
				/*
				 * Steigung ausrechnen für Tangenten. Steigungswert wird auf jeweiligen Punkt
				 * addiert, damit Steigung nicht im Nullpunkt sondern in Realtion zum jeweiligen
				 * Punkt liegt
				 * 
				 * /2 damit die Tangenten nicht so lang sind
				 */
				int spx = (int) (pSX[im] / 2 + pX[im]);
				int spy = (int) (pSY[im] / 2 + pY[im]);
				g.drawLine(spx, spy, (int) pX[im], (int) pY[im]);
				g2d.setStroke(new BasicStroke(4.0f));
				// für Tangente in beide Richtungen
//				spx = (int) (-pSX[im] / 2 + pX[im]);
//				spy = (int) (-pSY[im] / 2 + pY[im]);
//				g.drawLine(spx, spy, (int) pX[im], (int) pY[im]);
				g.setColor(old);
			}
		}
	}

	private void drawPolygon(Graphics g)
	{
		if (punkte.size() > 1)
		{
			Punkt p0 = punkte.get(0);
			int x0 = (int) p0.getX();
			int y0 = (int) p0.getY();
			for (int i = 1; i < punkte.size(); i++)
			{
				Punkt p1 = punkte.get(i);
				int x1 = (int) p1.getX();
				int y1 = (int) p1.getY();
				g.drawLine(x0, y0, x1, y1);
				x0 = x1;
				y0 = y1;
			}
		}
	}

	/**
	 * - Bindefunktionen der Hermitekurve - Dient zur Berechnung der Kurvenfunktion
	 * 
	 * @param t
	 * @return Werte der Bindefunktionen
	 */
	public double[] hermiteFunctions(double t)
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

	/*
	 * Alles vom MouseListener automatisch eingefügte Methoden
	 * -------------------------------------------------------
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (punkte.isMarked())
		{
			Punkt p = punkte.getMarked();
			p.move(new Punkt(e.getX(), e.getY()));
		}
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		punkte.markNearest(e.getX(), e.getY());
		// System.out.println("Nearest: " + nearest);
		// punkte.mark(nearest);
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

		// punkte.add(new Punkt(e.getX(), e.getY()));
		if (!punkte.isMarked())
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				punkte.add(new Punkt(e.getX(), e.getY()));
				punkte.marked = punkte.size() - 1;
				splineRecalc = true;
			} else
			{
			}
		} else
		{
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				punkte.removeMarked();
				splineRecalc = true;
			}
			//
		}

		System.out.println("Punkte.size " + punkte.size() + " Button " + e.getButton());
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}
	// -------------------------------------------------------

}
