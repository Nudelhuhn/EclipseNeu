package uebung4;

//Aufgabe 4.1
//- this hat in statischen Methoden keine Verwendung
//- statische methoden sind an keinem Objekt gebunden nicht-statische Attribute jedoch schon
// => Zugriff auf nicht-statische Attribute/Methoden erst mit Angabe eines Objekts möglich
//Ecplise weiß nicht welches i man meint, da man nicht auf this zugreifen dürfen, aber hier wird explizit das obj1 oder
//obj2 genannt, deswegen funktioniert es

// Kein Gegenbeispiel, da in statischen Methoden keine Objekte erwartet werden, sondern wenn dann
// Zugriffe auf statische Attribute => Prinzip statischer Eigenschaften wird nicht klar einsehbar gemacht

public class Example
{
	private int i;

	public Example(int i)
	{
		this.i = i;
	}

	public static boolean isEqual(Example obj1, Example obj2)
	{
		return obj1.i == obj2.i;
	}

	public static void main(String[] args)
	{
		Example a = new Example(1);
		Example b = new Example(1);
		System.out.println(isEqual(a, b));
		
		System.out.println();
		
		String s1 = new String("Hallo Welt");
		String s2 = s1;
		String s3 = new String("Hallo Welt");
		System.out.println(s1 == s2); // true
		System.out.println(s1.equals(s2)); // true
		System.out.println(s1 == s3); // false
		System.out.println(s1.equals(s3)); // true
	}
}
