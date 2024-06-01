package uebung8;

import java.util.HashSet;
import java.util.Set;

public class uebung8_1
{
	public static void main(String[] args) {
		Set<Mitarbeiter> mitarbeiter = new HashSet<Mitarbeiter>();
		Abteilung abteilung = new Abteilung(mitarbeiter);
		Mitarbeiter manfred = new Mitarbeiter(abteilung);
		manfred.setAbteilung(new Abteilung(mitarbeiter));
	}
}

class Mitarbeiter{
	private Abteilung abteilung;
	
	public Mitarbeiter(Abteilung abteilung) {
		this.abteilung = abteilung;
	}
	
	public void setAbteilung(Abteilung abt) {
		if(abt != abteilung) {		
			abteilung = abt;
			System.out.println("rekursiver Mitarbeiter");
			abt.addMitarbeiter(this);
		}
	}
}

class Abteilung{
	private Set<Mitarbeiter> mitarbeiter;
	
	public Abteilung(Set<Mitarbeiter> mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	public void addMitarbeiter(Mitarbeiter m) {
		if(!mitarbeiter.contains(m)) {			
			mitarbeiter.add(m);
			System.out.println("rekursive Abteilung");
			m.setAbteilung(this);
		}
	}
}