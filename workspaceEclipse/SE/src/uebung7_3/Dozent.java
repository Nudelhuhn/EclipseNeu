package uebung7_3;

import java.util.Set;

public class Dozent
{
	private Set<Fachgebiet> fachgebiete;
	
	public Dozent(Set<Fachgebiet> fachgebiete) {
		if(fachgebiete.size() >= 1 && fachgebiete.size() <= 2) {
			this.fachgebiete = fachgebiete;
		}
	}
}
