package splines;

/*
 * Um bei der Methode "drawHermiteCurve" in der Klasse Zeichenflaeche jede Hermite Kurve einf�gen zu k�nnen, muss man ein interface f�r Hermite Kurven implementieren, welche jede Methode,
 * die auf Hermite Kurven zugreifen, aus der Methode "drawHermiteCurve" enth�lt.
 */
public interface HermiteCurve
{
	public float[] getPx();

	public float[] getPy();

	public float[] getpSx();

	public float[] getpSy();
	
	public void recalcDerivate(Punkte p);
}