/*
 * Aufgabe 1
 * 
 * a1) - Codeblock oder Methode
 * a2) - keine, nur in Methode definiert
 * a3) - Stack
 * a4) - je nach Anzahl Methodenaufrufe (für jeden Aufruf neue Variable und Speicherplatzreservierung)
 * a5) - nein
 * 
 * b1) - außerhalb von Methoden, innerhalb der Klasse
 * b2) - private, public, protected, keine Sichtbarkeit angegeben (default)
 * b3) - im Speicherbereich der statischen Daten
 * b4) - ein einziges
 * b5) - ja,
 * 		- int, float, double = 0
 * 		- boolean = false
 * 		- char = \0
 * 		- nicht-primitive Datentypen (Klassen, Arrays, ...) = null
 * 
 * c1) - außerhalb von Methoden, innerhalb der Klasse
 * c2) - private, public, protected, keine Sichtbarkeit angegeben (default)
 * c3) - Im Heap. Nicht-statische Attribute sind Teile von Objekten, Objekte werden im Heap gespeichert
 * c4) - je nach Anzahl der erstellten Objekte, die diese Attribute nutzen
 * c5) - ja,
 * 		- int, float, double = 0
 * 		- boolean = false
 * 		- char = \0
 * 		- nicht-primitive Datentypen (Klassen, Arrays, ...) = null
 */

/*
 * Aufgabe 2
 * import uebungVorlesung.SampleClass;
 */

package uebung3;

import uebungVorlesung.SampleClass;

public class SampleClassUser
{
	private static SampleClass lastDeletedObject;
	private SampleClass[] objects;

	public SampleClassUser(int numberOfObjects)
	{
		if (numberOfObjects <= 0)
		{
			throw new IllegalArgumentException("Arg. zu klein");
		}
		if (numberOfObjects > 10)
		{
			throw new IllegalArgumentException("Arg. zu groß");
		}
		objects = new SampleClass[numberOfObjects];
		for (int i = 0; i < numberOfObjects; i++)
		{
			objects[i] = new SampleClass(i * 10 + 1, i * 10 + 2, null);
			if (i > 0)
			{
				objects[i].setNext(objects[i - 1]);
			}
		}
	}

	public void deleteObjectFromArray(int objectNumber)
	{
		lastDeletedObject = objects[objectNumber];
		objects[objectNumber] = null;
	}

	public static void showLastDeletedObject()
	{
		if (lastDeletedObject != null)
		{
			System.out.println(lastDeletedObject);
		} else
		{
			System.out.println("kein gelöschtes Objekt");
		}
	}

	public static void main(String[] args)
	{
		SampleClassUser scu = new SampleClassUser(4);
		scu.deleteObjectFromArray(1);//CS1
		scu.deleteObjectFromArray(3);//CS2
		scu.deleteObjectFromArray(3);//CS3
		showLastDeletedObject();
	}
}
