package suche;

public class binaereSuche {
	public static int sucheIterativ(int[] a, int gesucht) {
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] == gesucht) {
				System.out.println("Element" + " \"" + gesucht + "\" " + "gefunden an Stelle" + " " + i);
				System.out.println("Anzahl der paarweisen Vergleiche = " + (i + 1));
				return a[i];
			}
		}
		System.out.println("Element nicht gefunden!");
		return -1;
	}
	
	static int zaehlen = 0;
	
	public static int sucheRekursiv(int[] a, int gesucht) {
		
		if(a[zaehlen] == gesucht) {
			System.out.println("Element" + " \"" + gesucht + "\" " + "gefunden an Stelle" + " " + zaehlen);
			System.out.println("Anzahl der paarweisen Vergleiche = " + (zaehlen + 1));
			return a[zaehlen];
		} else {
			zaehlen++;
			if(zaehlen < a.length) {
				return sucheRekursiv(a, gesucht);
			}else {
				System.out.println("Element nicht gefunden!");
				return -1;
			}
		}
	}
}
