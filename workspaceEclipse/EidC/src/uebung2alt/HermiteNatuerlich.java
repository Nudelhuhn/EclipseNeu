/**
 * 
 */
package uebung2alt;

import packagefuerMatrixInfo.Matrix;

/**
 * Einfuehrung in die Computergrafik
 * Erzeugt die erforderlichen Daten zum zeichnen eine
 * Hermitesplines aus einer Punktliste
 *
 * @author F. N. Rudolph (c) 2012
 *         22.04.2020
 */
public class HermiteNatuerlich {
	private float[] px, py;
	private float[] pSx, pSy;
	private float[][] matrixA;
	private float[][] matrixB;
	private float[][] aInversB;

	/**
	 * 
	 */
	public HermiteNatuerlich(Punkte punkte) {
		// TODO Auto-generated constructor stub
		int n = punkte.size();
		if (n > 2) {
			// erster Punkt
			matrixA = new float[n][n];
			matrixB = new float[n][n];
			matrixA[0][0] = 2;
			matrixA[0][1] = 1;
			matrixB[0][0] = -3;
			matrixB[0][1] = 3;
			// Mittelpunkte
			for (int i = 1; i < n - 1; i++) {
				matrixA[i][i - 1] = 1;
				matrixA[i][i] = 4;
				matrixA[i][i + 1] = 1;
				matrixB[i][i - 1] = -3;
				matrixB[i][i + 1] = 3;
			}
			// letzter Punkt
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
			for (int i = 0; i < n; ++i) {
				px[i] = (float) punkte.get(i).getX();
				py[i] = (float) punkte.get(i).getY();
			}
			pSx = Matrix.matMult(aInversB, px);
			pSy = Matrix.matMult(aInversB, py);
		}
	}

	/**
	 * @return the px
	 */
	public synchronized float[] getPx() {
		return px;
	}

	/**
	 * @return the py
	 */
	public synchronized float[] getPy() {
		return py;
	}

	/**
	 * @return the pSx
	 */
	public synchronized float[] getpSx() {
		return pSx;
	}

	/**
	 * @return the pSy
	 */
	public synchronized float[] getpSy() {
		return pSy;
	}

	/**
	 * @return the aInversB
	 */
	public synchronized float[][] getaInversB() {
		return aInversB;
	}

}
