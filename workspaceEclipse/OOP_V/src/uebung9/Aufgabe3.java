package uebung9;

public class Aufgabe3
{
	
}

interface I3 {
	public default void method2() {
		System.out.println("method2 I3");
	}
}

interface I4 extends I3 {
	@Override
	public abstract void method2();
}

abstract class abstractClass implements I3 {
	public abstract void method2();
}

/*
 * Sowohl Schnittstellen, als auch abstrakte Klassen, können default Methoden
 * einer Schnittstelle implementieren und als abstrakte Methode überschreiben
 */