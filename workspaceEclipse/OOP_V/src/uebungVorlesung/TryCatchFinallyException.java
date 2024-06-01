package uebungVorlesung;

import java.util.Scanner;

public class TryCatchFinallyException
{
	public static void eingabe(String s) {
		if(s.equals("Hallo")) {
			System.out.println("unügltige Eingabe, bitte nochmal");
			main(null);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);	
		String s = scanner.next();
		eingabe(s);
        scanner.close();
	}
}
