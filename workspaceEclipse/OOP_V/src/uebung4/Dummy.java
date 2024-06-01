package uebung4;

public class Dummy
{
	public static I createObject() 
	 {
	 return new B();	//erzeugt keinen Laufzeitfehler
//	 return new A();	//erzeugt Laufzeitfehler
	 }
	
	public static void main(String[] args) {
//		J j = Dummy.createObject(); // Zuweisung ist nicht korrekt, da J spezieller als I ist
		// korrekte Zuweisungen:
		J jj = (J)Dummy.createObject();
		// (vor Programmbeginn ist rechte Seite vom Typ I, da R�ckgabewert der methode I ist
		// w�hrend der Laufzeit wird klar, dass ein B zur�ckgegeben wird, was erfolgreich zu J oder B casted werden kann)
		J jjj = (B)Dummy.createObject();
	}
}
