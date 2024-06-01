package uebung8;

public class uebung8_4
{
	public static void main(String[] args) {
		Abschlussarbeit a = new Abschlussarbeit("Mathe");
		a.themaErfragen();
		a.planen();
	}
}

class Abschlussarbeit{
	private String thema;
	private boolean themaGültig;
	private boolean vorErfüllt;
	private boolean istAngemeldet;
	private boolean abbrechen;
	private int anzahlArbeitspakete;
	private double x,y,z,a;
	
	public Abschlussarbeit(String thema) {
		
		this.thema = thema;
		
		x = Math.random();
		y = Math.random();
		z = Math.random();
		a = Math.random();
		
		themaGültig = x < 0.5 ? true : false;
		vorErfüllt = y < 0.8 ? true : false;
		abbrechen = a < 0.2 ? true : false;
		
		anzahlArbeitspakete = (int)Math.random() * 10;
		
	}
	
	
	public void themaErfragen() {
		System.out.println("Thema " + "\"" + thema + "\"" + " gültig?: " + themaGültig);
		if(themaGültig)
			voraussetzungenPrüfen();
	}
	
	public boolean voraussetzungenPrüfen() {
		if(themaGültig && vorErfüllt) {
			return true;
		}else {
			return false;
		}
	}
	
	public void planen() {
		if(voraussetzungenPrüfen()) {		
			System.out.println("Plan erstellt");
			arbeitAnmelden();
		}
	}
	
	public void arbeitAnmelden() {
		if(voraussetzungenPrüfen()) {			
			System.out.println("Arbeit angemeldet, Arbeitspakete erstellt");
			istAngemeldet = true;
			themaBearbeiten();
		}
	}
	
	public void themaBearbeiten() {
		if(voraussetzungenPrüfen()) {		
			if(abbrechen) {
				istAngemeldet = false;
				arbeitAbbrechen();
			}else{
				anzahlArbeitspakete -= 1;
				System.out.println("Arbeitspaket abgeschlossen");
				System.out.println("verbleibend: " + anzahlArbeitspakete);
				arbeitBesprechen();
			}
		}
	}
	
	public void arbeitBesprechen() {
		if(voraussetzungenPrüfen()) {			
			if(istAngemeldet) {
				if(z <= 0.33)
					System.out.println("Arbeit besprochen. Ist echt kacke");
				else if(z <= 0.66)
					System.out.println("Arbeit besprochen. War ok");
				else
					System.out.println("Arbeit besprochen. War echt einfach");
			}
			if(anzahlArbeitspakete <= 0) {
				System.out.println("Alle Arbeitspakete abgeschlossen");
				arbeitAbgeben();
			}
		}
	}
	
	public void arbeitAbgeben() {
		if(voraussetzungenPrüfen()) {			
			if(istAngemeldet) {			
				if(anzahlArbeitspakete <= 0) {					
					System.out.println("Arbeit abgegeben. Warte auf Ausarbeitung");
				}
			}
		}
	}
	
	public void arbeitAbbrechen() {
		System.out.println("Arbeit abgebrochen");
	}
}