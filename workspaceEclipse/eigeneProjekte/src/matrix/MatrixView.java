package matrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Anzeige einer Matrix mit formatierten double-Werten
 * 
 * Einfuehrung in die Computergrafik
 * 
 * @author F. N. Rudolph (c) 2014
 * 
 *         Datum: 23.05.2014
 */
public class MatrixView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel[][] jl;
	private int decimals = 2;
	private int nVert, nHor;
	private GridLayout gl;
	private JFrame jf;
	private static int lastX = 0;

	/**
	 * Konstruiert eine Matrixanzeige fuer array
	 * 
	 * @param array
	 *            anzuzeigendes double Array
	 */
	public MatrixView(double[][] array) {
		nVert = array.length;
		nHor = array[0].length;
		construct();
		setLabels(array);
	}

	/**
	 * Konstruiert eine Matrixanzeige mit nv Zeilen und nh Spalten
	 * 
	 * @param nv
	 *            Anzahl Zeilen
	 * @param nh
	 *            Anzahl Spalten
	 */
	public MatrixView(int nv, int nh) {
		nVert = nv;
		nHor = nh;
		construct();
	}

	/**
	 * allgemeiner Konstruktor
	 */
	private void construct() {
		gl = new GridLayout(nVert, nHor);
//		gl.setHgap(5);
//		gl.setVgap(5);
		this.setLayout(gl);
		TitledBorder border = new TitledBorder("Überschrift");
		Font f1 = new Font(border.getTitleFont().getFontName(), Font.PLAIN, 20);
		border.setTitleFont(f1);
		this.setBorder(border);

		jl = new JLabel[nVert][nHor];
		for (int i = 0; i < nVert; ++i) {
			for (int j = 0; j < nHor; ++j) {
				jl[i][j] = new JLabel("[" + i + ", " + j + "]");
				setText(i, j, i * 10 + j);
				Font f = new Font(jl[i][j].getFont().getFontName(), Font.PLAIN,
						20);
				jl[i][j].setFont(f);
				jl[i][j].setHorizontalAlignment(JLabel.RIGHT);
				this.add(jl[i][j]);
			}
		}

	}

	/**
	 * Anzahl der Zeilen
	 * 
	 * @return the nVert
	 */
	public int getNvert() {
		return nVert;
	}

	/**
	 * Anzahl der Spalten
	 * 
	 * @return the nHor
	 */
	public int getNhor() {
		return nHor;
	}

	/**
	 * Formatiert alle Labels neu
	 */
	private void setLabels() {
		for (int i = 0; i < jl.length; ++i) {
			for (int j = 0; j < jl[i].length; ++j) {
				try {
					double d = new Double(jl[i][j].getText());
					this.setText(i, j, d);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Setzt alle Labels auf einen Wert
	 * 
	 * @param d   Wert
	 */
	public void setLabels(double d) {
		for (int i = 0; i < jl.length; ++i) {
			for (int j = 0; j < jl[i].length; ++j) {
				try {
					setText(i, j, d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Setzt die Anzeige eines double Arrays
	 * @param d Array
	 */
	public void setLabels(double[][] d) {
		for (int i = 0; i < d.length; ++i) {
			for (int j = 0; j < d[i].length; ++j) {
				try {
					setText(i, j, d[i][j]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setText(int i, int j, double d) {
		System.err.println("i: " + i + "; j: " + j);
		try {
			jl[i][j].setText(format(d, decimals));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Formatiere einen Doublewert in einen String
	 * 
	 * @param d
	 *            Wert
	 * @param dec
	 *            Nachkommastellen
	 * @return Formatierter Zahlenwert in einem String
	 */
	private static String format(double d, int dec) {
		long sig = (d < 0) ? -1 : 1;
		// Runden
		d += sig * 0.5d / Math.pow(10, dec);
		//
		Double m = new Double(d);
		String t = m.toString();
		if (t.contains("E")) {
			t = "######"; // mit Exponent noch nicht implementiert
		} else {
			int i = t.indexOf('.'); // wo ist der Punkt
			String nachkommastellen = (t.substring(i, i + dec + 1) + "000000000000000000000")
					.substring(0, dec + 1);
			t = t.substring(0, i) + nachkommastellen;
		}
		return t;
	}

	/**
	 * Setze den Text in Label i j
	 * 
	 * @param i
	 *            Zeilenindex
	 * @param j
	 *            Spaltenindex
	 * @param s
	 *            Text
	 */
	public void setText(int i, int j, String s) {
		try {
			jl[i][j].setText(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Liefert den Text in Label i j
	 * 
	 * @param i
	 *            Zeilenindex
	 * @param j
	 *            Spaltenindex
	 */
	public String getText(int i, int j) {
		try {
			return jl[i][j].getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Liefert die Anzahl der anzuzeigenden Nachkommastellen
	 * 
	 * @return the decimals
	 */
	public int getDecimals() {
		return decimals;
	}

	/**
	 * Setzt die Anzahl der anzuzeigenden Nachkommastellen
	 * 
	 * @param decimals
	 *            the decimals to set
	 */
	public void setDecimals(int decimals) {
		this.decimals = decimals;
		setLabels();
	}

	/**
	 * Setze die Umrandung auf Dunkelgrau Staerke 4
	 */
	public void setLabelBorder() {
		setLabelBorder(Color.DARK_GRAY, 4);
	}

	/**
	 * Setze Umrandung
	 * 
	 * @param c
	 *            Farbe
	 * @param t
	 *            Strichstaerke
	 */
	public void setLabelBorder(Color c, int t) {
		LineBorder lb = new LineBorder(c, t);
		setBorder(lb);
	}

	/**
	 * Setze die Umrandung um die Zahlen
	 * 
	 * @param b
	 */
	public void setLabelBorder(Border b) {
		for (int i = 0; i < nVert; ++i) {
			for (int j = 0; j < nHor; ++j) {
				try {
					jl[i][j].setBorder(b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Testprogramm
	 * 
	 * @param args
	 *            wird nicht benutzt
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("MatrixView 0");
		double[][] d = { { 1, 2, 3, 9 }, { 4, 5, 5, 9 },
				{ 6, 7, 8, 9 }, { 6, 7, 8, 9 } };
		jf.add(new MatrixView(d));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(300, 400);
		jf.setVisible(true);

		JFrame jf1 = new JFrame("MatrixView 1");
		double[][] d1 = { { 6, -7, 8, 1 }, { 1, 2, 3, 1 },
				{ 4.4447f, -5.4677f, 18.456f, 1 } };
		MatrixView mv = new MatrixView(4, 3);
		// mv.setLabelBorder();
		mv.setDecimals(2);
		mv.setLabelBorder(new LineBorder(Color.cyan));
		mv.setText(2, 3, 1);

		jf1.add(mv);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setSize(300, 400);
		jf1.setLocation(400, 300);
		jf1.setVisible(true);

		// mv.setLabels(0f);
		// mv.setLabels(d1);
		// double[][] d2 = {
		// { 677777788, -7, 1, 1}, { 1, 2, 3, 1 },
		// { 4.4447f, -5.4677f, 18.456f, 1 }
		// };
		// mv.setLabels(d2);
	}

	public static MatrixView mvFrame(String title, double[][] m) {
		MatrixView mv = new MatrixView(m);
		mv.setPreferredSize(new Dimension(300, 100));
		mv.jf = new JFrame(title);
		mv.jf.setLocation(lastX, 0);

		mv.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mv.jf.add(mv);
		mv.jf.pack();
		mv.jf.setVisible(true);
		lastX = (int) mv.jf.getLocation().getX() + mv.jf.getWidth();
		return mv;
	}

	public void setLocationFrame(int x, int y) {
		jf.setLocation(x, y);
		lastX = (int) jf.getLocation().getX() + jf.getWidth();
	}

	public static double[][] mult(double[][] m1, double[][] m2) {
		double res[][] = new double[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int k = 0; k < m2[0].length; k++) {
				double tmp = 0f;
				for (int j = 0; j < m2.length; j++) {
					;
					tmp += m1[i][j] * m2[j][k];
				}
				res[i][k] = tmp;
			}
		}
		return res;
	}

}
