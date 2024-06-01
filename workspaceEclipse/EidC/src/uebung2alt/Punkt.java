/**
 * 
 */
package uebung2alt;

import java.awt.Color;

/**
 * Punktklasse fuer Splines
 * Einfuehrung in die Computergrafik
 *@author F. N. Rudolph (c) 2020
 *15.04.2020
 */
public class Punkt {
	public static Color defaultColor = Color.BLUE;
	private Color color = defaultColor;
	
	private double [] koords = {0, 0, 1};
	/**
	 * @return the color
	 */
	public synchronized Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public synchronized void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 
	 */
	public Punkt() {
		this.color = Punkt.defaultColor;
	}
	public Punkt( double x, double y) {
		koords[0] = x;
		koords[1] = y;
	}
	public Punkt( Punkt p) {
		koords[0] = p.getKoords()[0];
		koords[1] = p.getKoords()[1];
	}
	/**
	 * @return the koords
	 */
	public synchronized double[] getKoords() {
		return koords;
	}
	/**
	 * @param koords the koords to set
	 */
	public synchronized void setKoords(double[] koords) {
		this.koords = koords;
	}
	
	/**
	 * Berechnet den Abstand zweier Punkte
	 * @param p
	 * @return
	 */
	public double getDistance(Punkt p){
		double dx = koords[0] - p.getKoords()[0];
		double dy = koords[1] - p.getKoords()[1];
		return Math.sqrt(dx*dx + dy*dy);
	}
	/**
	 * Berechnet den quadratischen Abstand zweier Punkte
	 * @param p
	 * @return
	 */
	public double getQDistance(Punkt p){
		double dx = koords[0] - p.getKoords()[0];
		double dy = koords[1] - p.getKoords()[1];
		System.out.println("dx, dy: " + dx + ", " + dy + ", " + (dx*dx + dy*dy));

		return (dx*dx + dy*dy);
	}
	
	public double getX(){
		return koords[0];
	}
	public double getY(){
		return koords[1];
	}
	public void move(Punkt p){
		koords[0] = p.getX();
		koords[1] = p.getY();
	}

}
