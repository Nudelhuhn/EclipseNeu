package uebung9;

@FunctionalInterface
interface Example
{
	public int m(int i);
}

@FunctionalInterface
interface OtherExample
{
	public int otherM(int i);
}

//a)
public class Aufgabe4
{
	public int method3(Example example) {
		return example.m(0);
	}
	public int method3(OtherExample otherExample) {
		return otherExample.otherM(0);
	}
	
	public static void main(String[] args) {
		
		/*
		 * Das Problem war, wenn wir den Lambda-Ausdruck ohne Typ angeben, dann
		 * kommt die Fehlermeldung der Mehrdeutigkeit
		 */
		
		Aufgabe4 afg4 = new Aufgabe4();
		
		Example example = (int m_0) -> m_0 + 1;
		OtherExample otherExample = (int otherM_0) -> otherM_0 - 1;
		System.out.println(afg4.method3((int m_0) -> m_0 + 1));
		System.out.println(afg4.method3((Example)(int m_0) -> m_0 + 1));
		System.out.println(afg4.method3(otherExample));
		/*
		 * 1. Aufruf Methode Aufgabe4 mit Interface Parameter
		 * 2. Aufruf Methode des Interface
		 * 3. return des übergebenen int Werts in Methode von Klasse Aufgabe4 + angebenen 
		 *    int Wert im Lambda-Ausdruck
		 */
		
		//b)
		Example ex = s -> s + 1; 
		Object o = ex; 
		OtherExample oEx = (OtherExample) o; 
		oEx.otherM(17);
		// => ClassCastException
	}
}

