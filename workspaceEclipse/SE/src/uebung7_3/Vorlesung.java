package uebung7_3;

import java.util.Set;

public class Vorlesung
{
	private String title;
	private int nr;
	private Set<Studierende> hatStudierende;
	private Set<Dozent> hatDozent;
	
	public Vorlesung(String title, int nr, Set<Studierende> hatStudierende, Set<Dozent> hatDozent) {
		this.title = title;
		this.nr = nr;
		if(hatStudierende.size() >= 5) {
			this.hatStudierende = hatStudierende;
		}
		if(hatDozent.size() >= 1) {
			this.hatDozent = hatDozent;
		}
	}
}
