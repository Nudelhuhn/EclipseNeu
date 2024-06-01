package pwGenerator;

import java.util.ArrayList;
import java.util.List;

public class Passwort {
	static int counter = 0;	//Zähler für mind. 3/10 Zahlen im Passwort
		//alle möglichen Zeichen //Wiederholung der Zahlen und !? erhöht dessen Wahrscheinlichkeit (wichtig für PWs mit Länge < 8)
	private static char[] moeglZ = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '!', '?', '!', '?', '!',
			'?' };

	public static void pwGenerieren(int passwortlaenge) {
			//Passwort als Objekt, damit nachfolgende Operationen auf dem Passwort möglich sind
		List<Character> passwort = new ArrayList<>();
		for (int i = 0; i < passwortlaenge; i++) {
			char zZ = moeglZ[(int) (Math.random() * 100) % 75]; //zZ = zufälliges Zeichen des Arrays moeglZ
			passwort.add(zZ);
				//Zählen der hinzugefügten Zahlen, um später ungültige Pws automatisch aussortieren zu lassen
			if(zZ == '1'||zZ == '2'||zZ == '3'||zZ == '4'||zZ == '5'||zZ == '6'||zZ == '7'||zZ == '8'||zZ == '9'||zZ == '0')
				counter++;
		}
		if(passwortlaenge > 7) {
				//Wenn das pw mind. jeweils ein ! und ? und mind. 3/10 Zahlen hat und pwL mind 8 ist, dann gelten Kriterien für lange PWs
			if((passwort.contains('!') && passwort.contains('?')) && (counter > (int)(passwortlaenge * (3/10)))) {
				for(Character pwZ : passwort) {
					System.out.print(pwZ);
				}
				return;
			}else {
				counter = 0;
				pwGenerieren(passwortlaenge);
			}
		}else {
			for(Character pwZ : passwort) {
				System.out.print(pwZ);
			}
		}
		
	}

	public static void main(String[] args) {
		int pwLaenge = 20;
		int wieViele = 10;
		for(int i = 0; i < wieViele; i++) {
			pwGenerieren(pwLaenge);
			System.out.println();
		}
	}
}
