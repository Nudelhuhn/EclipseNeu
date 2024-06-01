package uebung9;

interface I1 {
	public default void method1() {
		System.out.println("Interface I1");
	}
}

interface I2 {
	public default void method1() {
		System.out.println("Interface I2");
	}
}

//a)
public class Aufgabe2 implements I1, I2
{
	/*
	 * Fehlermeldung
	 * Duplicate default methods named method1 with the
	 * parameters () and () are inherited from the types I2 and I1
	 */
	
	//c)
//	@Override
//	public void method1()
//	{
//		//Aufruf möglich durch <Interface>.super.<Methode>
//		I1.super.method1();
//		I2.super.method1();
//	}
	
	public static void main(String[] args) {
		Aufgabe2 t2 = new Aufgabe2();
		t2.method1();
	}
}

//b)
class test implements I1, I2{
	@Override
	public void method1() {
		//Methode wird erfolgreich überschrieben
	}
}