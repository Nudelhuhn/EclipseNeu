package splines;

/*
 * Um bei der Methode "drawHermiteCurve" in der Klasse Zeichenflaeche jede Hermite Kurve einfügen zu können, muss man ein interface für Hermite Kurven implementieren, welche jede Methode,
 * die auf Hermite Kurven zugreifen, aus der Methode "drawHermiteCurve" enthält.
 */
public interface HermiteCurve
{
	public float[] getPx();

	public float[] getPy();

	public float[] getpSx();

	public float[] getpSy();
	
	public void recalcDerivate(Punkte p);
}