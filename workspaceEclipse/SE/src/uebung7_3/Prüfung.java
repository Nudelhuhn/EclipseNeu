package uebung7_3;

import java.util.Date;
import java.util.Set;

public class Pr�fung
{
	private Date datum;
	private Set<Dozent> pr�fer;
	private double note;
	private Set<Studierende> hatPr�fung;
	private double[] g�ltigeNoten =
			{1.0, 1.3, 1.7,
			 2.0, 2.3, 2.7,
			 3.0, 3.3, 3.7,
			 4.0, 4.3, 4.7,
			 5.0};
	
	public Pr�fung(Date datum, Set<Dozent> pr�fer, double note, Set<Studierende> hatPr�fung) {
		this.datum = datum;
		this.pr�fer = pr�fer;
		for(double n : g�ltigeNoten) {
			if(n == note) {
				this.note = note;
			}
		}
		this.hatPr�fung = hatPr�fung;
	}
}
