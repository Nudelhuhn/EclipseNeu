/**
 * 
 */
package splinesProfVerzeichnis;

import java.util.ArrayList;

/**
 * Einfuehrung in die Computergrafik
 *@author F. N. Rudolph (c) 2012
 *15.04.2020
 */
public class Punkte extends ArrayList<Punkt> {
	public static float box = 5;
	public static float qBox = box * box;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int marked = -1;

	public Punkt getMarked(){
		if (marked >-1){
			return this.get(marked);
		}
		return null;
	}
	public void setMarked(int index){
		if (index >= this.size()){
			marked = -1;
		} else {
			marked = index;
		}
	}
	
	public void mark(Punkt p){
		marked = -1;
		for (int i = 0; i < this.size(); i++){
			if (this.get(i) == p){
				marked = i;
				i = this.size();
			}
		}
	}
	/**
	 * Markiere den naechsten Punkt, der innerhalb einer Abstandsbox liegt
	 * @param x
	 * @param y
	 * @return der naechste Punkt
	 */
	public Punkt markNearest(float x, float y){
		marked = -1;
		double dist = Double.MAX_VALUE;
		Punkt p = new Punkt(x, y);
		Punkt nearest = null;
		for (int i = 0; i < this.size(); i++){
			Punkt pt = this.get(i);
			double dt = pt.getQDistance(p);
			if(dt < qBox & dt < dist){
				marked = i; 
				dist = dt;
				nearest = pt;
			}		
		}
		return nearest;
	}
	public boolean isMarked() {
		return marked > -1;
	}

	public void removeMarked() {
		super.remove(this.get(marked));
		marked = -1;
	}
}
