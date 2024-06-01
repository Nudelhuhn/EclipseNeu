package uebung7_3;

import java.util.Date;
import java.util.Set;

public class Prüfung
{
	private Date datum;
	private Set<Dozent> prüfer;
	private double note;
	private Set<Studierende> hatPrüfung;
	private double[] gültigeNoten =
			{1.0, 1.3, 1.7,
			 2.0, 2.3, 2.7,
			 3.0, 3.3, 3.7,
			 4.0, 4.3, 4.7,
			 5.0};
	
	public Prüfung(Date datum, Set<Dozent> prüfer, double note, Set<Studierende> hatPrüfung) {
		this.datum = datum;
		this.prüfer = prüfer;
		for(double n : gültigeNoten) {
			if(n == note) {
				this.note = note;
			}
		}
		this.hatPrüfung = hatPrüfung;
	}
}
