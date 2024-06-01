package binaereSuche;

public class BinItSuche {
	static int linkerZeiger, rechterZeiger, mitte;
	public static void suche(int[] arr, int key) {
		linkerZeiger = arr[0];
		rechterZeiger = arr.length-1;
		int zaehler = 0;
		while(linkerZeiger <= rechterZeiger) {
			zaehler++;
			mitte = (linkerZeiger + rechterZeiger)/2;
			if(key == mitte) {
				System.out.println("Element an Stelle " + arr[mitte]);
				System.out.println("Anzahl paarweise Wertevergleiche: " + zaehler);
				return;
			}
			else if(key < mitte) {
				rechterZeiger = mitte - 1;
			}else {
				linkerZeiger = mitte + 1;
				
			}
		}
		System.out.println("Element nicht in Menge enthalten");
		System.out.println("Anzahl paarweise Wertevergleiche: " + zaehler);
	}
}

//Gegeben ist ein Array mit aufsteigend sortierten natürlichen Zahlen
//Am Anfang ist der linkeZeiger auf das erste Element gerichtet und der rechte auf das letzte Element
//Die Mitte ergibt sich aus der Summe von linken und rechten Zeiger geteilt durch zwei
//Jeder Durchlauf prüft, ob das gesuchte Element nun links oder rechts von der Mitte liegt oder die Mitte selbst ist
//Ist das gesuchte Element größer als die Mitte, wird der linke Zeiger auf die Mitte+1 gesetzt, da in der kleineren Hälfte das Element nicht sein kann
//genauso verhält es sich mit der linken Hälfte und dem linken Zeiger, wenn das gesuchte Element kleiner als die Mitte ist
//Dieser Vorgang wird so lange wiederholt bis das Element gefunden wurde oder das Element nicht gefunden wurde und die Bedingung lZ <= rZ n. mehr gilt