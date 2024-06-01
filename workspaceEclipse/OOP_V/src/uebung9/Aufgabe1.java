package uebung9;


public class Aufgabe1 //Basisklasse
{
	public void method() {
		System.out.println("Etwas");
	}

	public static void main(String[] args) {
		B b = new B();
		b.method();
	}
	/*
	 * Methode der Basisklasse wird benutzt
	 * Methode der Schnittstelle wird ignoriert, Methoden von Klassen werden in Java
	 * dem Methoden von Interfaces bevorzugt
	 */
}

interface I {
	public default void method() {
		System.out.println("Was anderes");
	}
}

class B extends Aufgabe1 implements I {}