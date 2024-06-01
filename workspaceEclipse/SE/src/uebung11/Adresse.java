//package uebung11;
//
//class Adresse {
//	private final String Vorname, Nachname, Firmenname, Postfach, Ort, Stra�e, Hausnummer, Postleitzahl;
//
//	private Adresse(String vorname, String nachname, String firmenname, String postfach, String ort, String stra�e,
//			String hausnummer, String postleitzahl) {
//		Vorname = vorname;
//		Nachname = nachname;
//		Firmenname = firmenname;
//		Postfach = postfach;
//		Ort = ort;
//		Stra�e = stra�e;
//		Hausnummer = hausnummer;
//		Postleitzahl = postleitzahl;
//	}
//
//	public static class Builder {
//		private String vorname, nachname, firmenname, Postfach, Ort, Stra�e, Hausnummer, Postleitzahl;
//	}
//
//	public Builder Vorname(String vorname) {
//		Vorname = vorname;
//		return this;
//	}
//
//	public Builder Nachname(String nachname) {
//		Nachname = nachname;
//		return this;
//	}
//
//	public Builder Firmenname(String firmenname) {
//		Firmenname = firmenname;
//		return this;
//	}
//
//	public Builder Postfach(String postfach) {
//		Postfach = postfach;
//		return this;
//	}
//
//	public Builder Ort(String ort) {
//		Ort = ort;
//		return this;
//	}
//
//	public Builder Stra�e(String stra�e) {
//		Stra�e = stra�e;
//		return this;
//	}
//
//	public Builder Hausnummer(String hausnummer) {
//		Hausnummer = hausnummer;
//		return this;
//	}
//
//	public Builder Postleitzahl(String postleitzahl) {
//		Postleitzahl = postleitzahl;
//		return this;
//	}
//
//	public Adresse build() {
//		if (Stra�e != null && Hausnummer == null) {
//			throw new IllegalStateException("");
//		}
//		if (Stra�e == null && Hausnummer != null) {
//			throw new IllegalStateException("");
//		}
//		if (Postfach != null && Firmenname == null) {
//			throw new IllegalStateException("");
//		}
//		return new Adresse(Vorname, Nachname, Firmenname, Postfach, Stra�e, Hausnummer, Postleitzahl, Ort);
//	}
//}
