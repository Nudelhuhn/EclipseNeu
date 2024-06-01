package uebung7_2;

import java.util.Set;

public class Dozent
{
	private Set<Fachgebiet> fachgebiet;
	
	public Dozent(Set<Fachgebiet> fachgebiet) {
		if(fachgebiet.size() >= 1 && fachgebiet.size() <= 2) {
			this.fachgebiet = fachgebiet;
		}
	}
}
