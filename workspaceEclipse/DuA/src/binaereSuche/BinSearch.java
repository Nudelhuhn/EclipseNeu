package binaereSuche;

public class BinSearch {
	static int zaehler = 0;
	public static void suche(int[] arr, int key, int linkerZeiger, int rechterZeiger) {
		int mitte = (linkerZeiger + rechterZeiger)/2;
		if(linkerZeiger < rechterZeiger) {
			zaehler++;
			if(key == mitte) {
				System.out.println("Element an Stelle " + arr[mitte]);
				System.out.println("Anzahl paarweise Wertevergleiche: " + zaehler);
				return;
			}
			else if(key < mitte) {
				suche(arr, key, linkerZeiger, (rechterZeiger = mitte - 1));
			}else {
				suche(arr, key, (linkerZeiger = mitte + 1), rechterZeiger);
			}
		}
		System.out.println("Element nicht in Menge enthalten");
		System.out.println("Anzahl paarweise Wertevergleiche: " + zaehler);
	}
}
//Erklärung bei binItSuche
