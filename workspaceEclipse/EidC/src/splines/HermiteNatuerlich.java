package splines;

/**
 * Einfuehrung in die Computergrafik Erzeugt die erforderlichen Daten zum
 * zeichnen eine Hermitesplines aus einer Punktliste
 *
 * @author F. N. Rudolph (c) 2012 22.04.2020
 */

/*
 * Es wurden kleine Änderungen von mir (Gregor Germerodt) vorgenommen und Kommentare hinzugefügt
 */
public class HermiteNatuerlich implements HermiteCurve
{
	private float[] px, py; // Koordinatenwerte der Punkte
	private float[] pSx, pSy; // Steigungswerte in den Punkten
	private float[][] matrixA; // Koeffizientenmatrix Steigungen A
	private float[][] matrixB; // Koeffizienten Randbedingungen B
	private float[][] aInversB; // Produkt A_invers B

	/**
	 * 
	 */
	public HermiteNatuerlich(Punkte punkte)
	{
		construct(punkte);
	}

	private void construct(Punkte punkte)
	{
		// Matrizen aufbauen
		int n = punkte.size();
		if (n > 2)
		{
			/*
			 * erster Punkte Randpunktformel natuerlicher Spline 2_ + 1_ = -3_ + 3_ (Skript
			 * S. 23)
			 */
			matrixA = new float[n][n];
			matrixB = new float[n][n];
			matrixA[0][0] = 2;
			matrixA[0][1] = 1;
			matrixB[0][0] = -3;
			matrixB[0][1] = 3;
			mittelpunktformel(matrixA, matrixB);
			/*
			 * letzter Punkt Randfunktformel natuerlicher Spline 1_ + 2_ = -3_ + 3_ (Skript
			 * S. 23)
			 */
			matrixA[n - 1][n - 2] = 1;
			matrixA[n - 1][n - 1] = 2;
			matrixB[n - 1][n - 2] = -3;
			matrixB[n - 1][n - 1] = 3;
			Matrix.print("matrixA", matrixA);
			Matrix.print("matrixB", matrixB);
			float[][] aInvers = Matrix.invertiereMatrix(matrixA);
			Matrix.print("aInvers", aInvers);
			aInversB = Matrix.matMult(aInvers, matrixB);
			px = new float[punkte.size()];
			py = new float[punkte.size()];
			recalcDerivate(punkte);
		}
	}

	private void mittelpunktformel(float[][] matrixA, float[][] matrixB)
	{
		/*
		 * Mittelpunkte Mittelpunktformel 1_ + 4_ + 1_ = -3_ + 3_ (Skript S. 23)
		 */
		for (int i = 1; i < matrixA.length - 1; i++)
		{
			matrixA[i][i - 1] = 1;
			matrixA[i][i] = 4;
			matrixA[i][i + 1] = 1;
			matrixB[i][i - 1] = -3;
			matrixB[i][i + 1] = 3;
		}
	}

	// Ableitungsberechnung
	public void recalcDerivate(Punkte punkte)
	{
		int n = punkte.size();
		if (n > 2)
		{
			for (int i = 0; i < n; ++i)
			{
				px[i] = (float) punkte.get(i).getX();
				py[i] = (float) punkte.get(i).getY();
			}
			// P' = A^(-1)BP (Skript S. 23)
			pSx = Matrix.matMult(aInversB, px);
			pSy = Matrix.matMult(aInversB, py);
		}
	}

	/**
	 * @return the px
	 */
	public synchronized float[] getPx()
	{
		return px;
	}

	/**
	 * @return the py
	 */
	public synchronized float[] getPy()
	{
		return py;
	}

	/**
	 * @return the pSx
	 */
	public synchronized float[] getpSx()
	{
		return pSx;
	}

	/**
	 * @return the pSy
	 */
	public synchronized float[] getpSy()
	{
		return pSy;
	}

	/**
	 * @return the aInversB
	 */
	public synchronized float[][] getaInversB()
	{
		return aInversB;
	}

}
