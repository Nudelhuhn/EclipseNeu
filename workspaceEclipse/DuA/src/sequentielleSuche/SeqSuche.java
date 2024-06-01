package sequentielleSuche;

public class SeqSuche {
	static int zaehler = 0;
	public static int suchen(int arr[], int key) {
		for(int i = 0; i < arr.length; i++) {
			zaehler++;
			if(arr[i] == key) {
				System.out.println("Element an Stelle " + i);
				System.out.println("Anzahl paarweise Wertevergleiche: " + zaehler);
				return 1;
			}
		}
		System.out.println("Elemente nicht in Menge enthalten!");
		return -1;
	}
}
