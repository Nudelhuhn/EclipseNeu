/**
 * 
 */
package splinesProfVerzeichnis;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Einfuehrung in die Computergrafik
 *
 * @author F. N. Rudolph (c) 2012
 *         15.04.2020
 */
public class Zeichenflaeche extends JPanel implements MouseListener,
		MouseMotionListener {
	private double[] p0 = { 200, 400 };
	private double[] p1 = { 1200, 400 };
	private double[] t0 = { 0, -100 };
	private double[] t1 = { -400, -500 };
	private Punkte punkte;
	private HermiteNatuerlich hmN;

	/**
	 * 
	 */
	public Zeichenflaeche() {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.punkte = new Punkte();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3.0f));
		g.setColor(Color.GREEN);


		drawPunkte(g);
		drawPolygon(g);
		// Highlighting
		if (punkte.isMarked()) {
			g.setColor(Color.RED);
			this.drawPunkt(punkte.getMarked(), g);
		}
		hmN = new HermiteNatuerlich(punkte);
		drawHermiteCurve(hmN, g);

	}

	private void drawPunkt(Punkt p, Graphics g) {
		g.fillOval((int) p.getX() - 5, (int) p.getY() - 5, 10, 10);

	}
	
	private void drawHermiteCurve(HermiteNatuerlich h, Graphics g){
		if (punkte.size()>2){
		g.setColor(Color.BLUE);
		int p0X = (int) h.getPx()[0];
		int p0Y = (int) h.getPy()[0];
		for (int i = 1; i < punkte.size(); i++) {
			double delta = 1d / 16d;
			for (double t = delta; t < 1d - delta / 2d; t += delta) {
				double[] tmp = hermiteBlend(t);
				int p1x = (int) (h.getPx()[i-1] * tmp[0] + h.getPx()[i] * tmp[1] +
						h.getpSx()[i-1] * tmp[2] + h.getpSx()[i] * tmp[3]);
				int p1y = (int) (h.getPy()[i-1] * tmp[0] + h.getPy()[i] * tmp[1] +
						h.getpSy()[i-1] * tmp[2] + h.getpSy()[i] * tmp[3]);
				g.drawLine(p0X, p0Y, p1x, p1y);
				p0X = p1x;
				p0Y = p1y;
			}
			g.drawLine(p0X, p0Y, (int) h.getPx()[i], (int) h.getPy()[i]);
		}
		}
	}

	private void drawPunkte(Graphics g) {
		for (Punkt p : punkte) {
			drawPunkt(p, g);
		}
	}

	private void drawPolygon(Graphics g) {
		if (punkte.size() > 1) {
			Punkt p0 = punkte.get(0);
			int x0 = (int) p0.getX();
			int y0 = (int) p0.getY();
			for (int i = 1; i < punkte.size(); i++) {
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
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("Splineeditor");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(100, 10);
		jf.setSize(1500, 500);
		Zeichenflaeche zf = new Zeichenflaeche();
		jf.add(zf, BorderLayout.CENTER);

		jf.setVisible(true);
	}

	/**
	 * Bindefunktionen der Hermitekurve
	 * 
	 * @param t
	 *            Parameter
	 * @return Werte der Bindefunktionen
	 */
	double[] hermiteBlend(double t ) {
		double t2 = t * t;
		double t3 = t2 * t;
		double h1 = 2 * t3 - 3 * t2 + 1;
		double h2 = -2 * t3 + 3 * t2;
		double h3 = t3 - 2 * t2 + t;
		double h4 = t3 - t2;

		return new double[] { h1, h2, h3, h4 };
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (punkte.isMarked()) {
			Punkt p = punkte.getMarked();
			p.move(new Punkt(e.getX(), e.getY()));
		}
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		punkte.markNearest(e.getX(), e.getY());
		// System.out.println("Nearest: " + nearest);
		// punkte.mark(nearest);
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// punkte.add(new Punkt(e.getX(), e.getY()));
		if (!punkte.isMarked()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				punkte.add(new Punkt(e.getX(), e.getY()));
				punkte.marked = punkte.size() - 1;
			} else {
			}
		} else {
			if (e.getButton() == MouseEvent.BUTTON3) {
				punkte.removeMarked();
			}
			//
		}

		System.out.println("Punkte.size " + punkte.size() + " Button "
				+ e.getButton());
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
