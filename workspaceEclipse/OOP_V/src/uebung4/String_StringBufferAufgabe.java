package uebung4;

//Aufgabe 4

public class String_StringBufferAufgabe
{
	public static void main(String[] args)
	{
		int durchlaeufe = 200000;

		// Teil 2:
		long t3 = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < durchlaeufe; i++)
		{
			sb.append("*");
		}
		String s2 = sb.toString();
		long t4 = System.currentTimeMillis();
		long diffTeil2 = t4-t3;

		
		// Teil 1:
		long t1 = System.currentTimeMillis();
		String s1 = "";
		for (int i = 0; i < durchlaeufe; i++)
		{
			s1 += "*";
		}
		long t2 = System.currentTimeMillis();
		long diffTeil1 = t2-t1;
		
		System.out.println("Teil 2 Dauer: " + diffTeil2 + " Millisekunden");
		System.out.println("Teil 1 Dauer: " + diffTeil1 + " Millisekunden");

		System.out.println(s1.equals(s2)); // true, da gleicher Inhalt
		System.out.println(s1 == s2);
	}
}

// b)
// durchlaeufe = 2.000
// Teil 1: 1 Millisekunde
// Teil 2: 4 Millisekunden

// durchlaeufe = 20.000
// Teil 1: 1 Millisekunde
// Teil 2: 56 Millisekunden

// durchlaeufe = 200.000
// Teil 1: 7 Millisekunde
// Teil 2: 2591 Millisekunden


//c)
// Teil 2
// id verändert sich nicht
// stringbuffer Objekt anfangs ein Feld der Größe 16
// nach jedem Durchlauf, wird * angehangen (ASCII 42)
// sobald Ende des Feldes erreicht wird, wird größe mehr als verdoppelt

// Teil 1
// id verändert sich
// jeder Schleifendurchlauf erzeugt neues String Feld, mit kopierten alten Feldwerten plus neuen Feldwert

