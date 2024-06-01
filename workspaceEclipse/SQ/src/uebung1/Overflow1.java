package uebung1;

public class Overflow1 {
	public static void main(String[] argv) {
		int x, y;
		int r;
		x = (int) (-Math.pow(2, 31));
		y = x;
		r = x + y;
		System.out.println("Ausgabe 1: x = " + x);
		System.out.println("Ausgabe 2: y = " + y);
		System.out.println("Ausgabe 3: r = " + r + 1);
		System.out.println("Ausgabe 4: r = " + (r + 1));
	}
}