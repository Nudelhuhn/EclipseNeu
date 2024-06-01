package uebung8;

public class afg4 implements a, b
{
	@Override
	public void methodA(int number) {}
	@Override
	public void methodB(int number) {}
	@Override
	public void methodB(long number) {}
	@Override
	public int methodC(int number) {}
	@Override
	public void methodD(int number) throws IllegalArgumentException {}
//	public void methodD(int number) throws NumberFormatException {}
}

interface a{
	public void methodA(int number);
	public void methodB(int number);
	public void methodC(int number);
	public void methodD(int number) throws IllegalArgumentException;
}
interface b{
	public void methodA(int number);
	public void methodB(long number);
	public int methodC(int number);
	public void methodD(int number) throws NumberFormatException;
}

/*
 * - bei gleicher Signatur wird nur eine ausgewählt
 * - bei unterschiedlichen Parametertypen werden beide übernommen
 * - bei unterschiedlichen Rückgabetypen ist dieser nicht kompaktibel
 *   mit der Methode (egal welche) und es wird nur eine übernommen, sonst Fehlermeldung
 *   duplicate Methods
 *   Funktioniert aber, wenn die implementierte Methode in der Klasse beide Methoden
 *   der Interfaces erfüllt, also wenn zb ein Interface Integer und das andere Number
 *   als Rückgabetyp haben(funktioniert da Integer die Basisklasse Number hat)
 * - bei unterschiedlicher throws Klausel wird die Methode des Interfaces
 *   genommen, welches als erstes implementiert wurde
 *   Bei eigenen Exceptions würde es nicht funktionieren
 */