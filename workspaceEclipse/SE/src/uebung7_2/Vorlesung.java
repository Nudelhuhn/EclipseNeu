package uebung7_2;

import java.util.Set;

public class Vorlesung
{
	private String title;
	private int nr;
	private Set<Studierende> vorlesungsteilnehmer;
	private Set<Dozent> dozent;
	
	public Vorlesung(String title, int nr, Set<Studierende> vorlesungsteilnehmer, Set<Dozent> dozent) {
		this.title = title;
		this.nr = nr;
		if(vorlesungsteilnehmer.size() >= 5) {
			this.vorlesungsteilnehmer = vorlesungsteilnehmer;
		}
		if(dozent.size() >= 1) {
			this.dozent = dozent;
		}
	}
}
