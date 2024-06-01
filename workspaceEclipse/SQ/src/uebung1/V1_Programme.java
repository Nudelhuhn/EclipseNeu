package uebung1;

import java.math.BigDecimal;

public class V1_Programme
{

	public static void main(String[] args)
	{
		System.out.println("Ergebnis1: " + (2.00 - 1.10));
		
		double ergebnis2 = 2.00 - 1.10;
		System.out.println("Ergebnis2: " + ergebnis2);
		
		// Wie kann man das Verhalten beheben?
		float ergebnis3 = (float) (2.00 - 1.10);
		System.out.println("Ergebnis3: " + ergebnis3);
		// war nur Zufall, dass es bei float funktioniert hat
		// da float auch endliche Genauigkeit der Zahlen-
		// darstellung hat
		
		// Man wandelt in int und aendert die Einheit
		int ergebnis4 = 200 - 110;
		System.out.println("Ergebnis4: " + ergebnis4 + " Cent");
		
		// Wenn es mal genau sein soll
		BigDecimal x = new BigDecimal("2.00");
		BigDecimal y = new BigDecimal("1.10");
		System.out.println("Ergebnis5: " + x.subtract(y));
		// Bei BigDecimal entscheidet Programm automatisch
		// welche Vor- und Nachkommazahlen gew‰hlt werden
		// sollen und nicht n‰herungsweise wie bei Flieﬂ-
		// kommazahlen

		int result = 1;
		int max = Integer.MAX_VALUE;
		try {
			result = Math.addExact(result, max);
		}catch (ArithmeticException e){
			e.printStackTrace();
		}
	}

}
