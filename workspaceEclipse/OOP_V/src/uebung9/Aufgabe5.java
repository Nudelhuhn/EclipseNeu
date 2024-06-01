package uebung9;

public class Aufgabe5
{

}

//a)
@FunctionalInterface
interface I5
{
	// Ein functional Interface muss mindestens eine abstrakte Methode besitzen
	public default void method4() {}
}

//b)
@FunctionalInterface
interface I6
{
	public abstract void method5();
	public default void method6() {}
	public default void method7() {}
}

//c)
@FunctionalInterface
interface I7
{
	public abstract void method8();
	public abstract void method9();
	public default void method10() {}
	public default void method11() {}
	//Ein functional Interface darf maximal eine abstrakte Methode besitzen
}

//d)
@FunctionalInterface
interface I8 {
	public abstract void method12();
	boolean equals(Object o);
	int hashCode();
}
/*
 * Object ist die Basisklasse aller Klassen, dh die Methoden werden sowieso
 * schon überall benutzt und verletzen deswegen nicht die Bedingungen des
 * functional Interfaces
 */