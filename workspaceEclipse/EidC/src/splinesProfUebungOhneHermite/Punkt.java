package splinesProfUebungOhneHermite;

import java.awt.Color;

public class Punkt
{
	public static Color defaultColor = Color.BLUE;
	private Color color = defaultColor;
	private double[] koords = {0, 0, 1};
	/*
	 * koords ist immer der Punkt der gerade in Verwendung steht
	 * also der der als letztes verändert und hinzugefügt wurde
	 */
	
	public Punkt() {
		this.color = Punkt.defaultColor;
	}
	// Punkt durch x und y bestimmen
	public Punkt(double x, double y) {
		koords[0] = x;
		koords[1] = y;
	}
	// Punkt durch Punkt bestimmen
	public Punkt(Punkt p) {
		koords[0] = p.getKoords()[0];
		koords[1] = p.getKoords()[1];
	}

	public synchronized Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public synchronized void setColor(Color color) {
		this.color = color;
	}
	
	public synchronized double[] getKoords()
	{
		return koords;
	}

	public synchronized void setKoords(double[] koords)
	{
		this.koords = koords;
	}
	
	/*
	 * Berechnung des Abstands zweier Punkte
	 */
	public double getDistance(Punkt p) {
		double dx = koords[0] - p.getKoords()[0];
		double dy = koords[1] - p.getKoords()[1];
		return Math.sqrt(dx*dx + dy*dy);
	}
	/*
	 * Berechnung des quadratischen Abstands zweier Punkte
	 */
	public double getQDistance(Punkt p) {
		double dx = koords[0] - p.getKoords()[0];
		double dy = koords[1] - p.getKoords()[1];
		return (dx*dx + dy*dy);
	}
	
	public double getX() {
		return koords[0];
	}
	public double getY() {
		return koords[1];
	}
	public void move(Punkt p) {
		koords[0] = p.getX();
		koords[1] = p.getY();
	}
}
