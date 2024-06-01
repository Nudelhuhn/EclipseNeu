package uebung4;

//class A
//{
//	public A(String s)
//	{
//	}
//}
//
//class B extends A
//{
////	//c
////	public B() {
////		super("");
////	}
//}

//Aufgabe 4.2
//a)
// Klasse B hat einen parameterlosen Konstruktor, da kein eigener angeben ist.
// In diesem automatisch generierten Konstruktor wird implizit super() aufgerufen,
// was den parameterlosen Konstruktor der Oberklasse aufruft.
// Die Oberklasse A hat jedoch keinen automatisch generierten parameterlosen Kontruktor, da selbst einer definiert wurde.
// => Syntaxfehler
//(Superkonstruktor ruft Konstruktor der Superklasse auf, um Initialisierung der geerbten Parameter zu ermöglichen)
//b)
// Einen parameterloser Konstruktor von A definieren oder die Parameter des bestehenden entfernen.
//c)
// Konstruktor definieren, worin der Superkonstruktor der Oberklasse aufgerufen wird (also mit einem string)


////Aufgabe 4.3 c)
//interface I
//{
//}
//
//interface J extends I
//{
//}
//
//public class A implements I
//{
//	public A(int x) {
//		
//	}
//	
//	public static void main(String[] args) {
//		A a1 = new B(1,2);
//	}
//}
//
//class B extends A implements J
//{
//	public B(int x, int y) {
//		super(x);
//	}
//}


//Aufgabe 4.3 d)
interface I
{
}

interface J extends I
{
}

class A implements I
{
	public A() {
		
	}
	public static void main(String[] args) {
		A a1 = B.createAObject();
		//J j = (J)Dummy.createObject();	//Nur cast zu J und B funktionieren, aber Laufzeitfehler bei Dummy class mit return A(int)
	}
}

class B extends A implements J
{
	public static A createAObject() {
		return new A();
	}
}