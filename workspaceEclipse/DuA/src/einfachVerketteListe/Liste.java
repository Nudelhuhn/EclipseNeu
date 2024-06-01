package einfachVerketteListe;

public class Liste {
	// Attribute
	private ListElement kopf;
	private ListElement aktuell; // Referenz auf das aktuelle Element
	private ListElement vorgaenger; // Referenz auf den Vorgaenger

	// Operationen
	boolean istLeer() {
		// Prüfen ob Liste leer ist
		return getKopf() == null;
	}

	void durchlaufe() {
		// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
		ListElement elem = getKopf();
		while (elem != null) {
			// bearbeite aktuelles Element
			System.out.print(elem.getDaten() + " ");
			// gehe ein Element weiter
			elem = elem.getNaechstes();
		}
		System.out.println();
	}

	boolean finde(int einObject) {
		vorgaenger = null;
		aktuell = getKopf();
		// suche so lange bis Element gefunden wurde oder aktuell aus der Liste läuft
		// (=null)
		while (aktuell != null) {
			if (einObject == aktuell.getDaten()) {
				return true;
			} else {
				vorgaenger = aktuell;
				aktuell = aktuell.getNaechstes();
			}
		}
		return false;
	}

	int getAktuelleDaten() {
		if (aktuell == null) {
			throw new NullPointerException("Kein Element vorhanden");
		}
		return aktuell.getDaten();
	}

	void einfuegeHinter(int neuesObject) {
		if (aktuell == null) {
			throw new NullPointerException("Kein Element vorhanden");
		} else {
			// Neues Objekt, damit Daten aus Parameter übergeben werden können
			// Zusätzlich Referenz auf das nächstes Objekt
			ListElement neu = new ListElement(neuesObject, aktuell.getNaechstes());
			aktuell.setNaechstes(neu);
		}
	}

	// Einfuegen eines Listenelements am Anfang der Liste
	void einfuegeKopf(int neuesObject) {
		// Neues Object ersetzt den kopf, wird also quasi davor eingefügt => der alte
		// Kopf, welcher noch keinen neuen Wert bekommen hat,
		// ist das nächste Object
		ListElement neu = new ListElement(neuesObject, getKopf());
		// Kopf bekommt neuen Wert
		setKopf(neu);
	}

	// Einfuegen eines Listenelements vor dem aktuellen Element aktuell
	void einfuegeVor(int neuesObject) {
		// aktuelles Element muss vorhanden sein
		if (aktuell == null)
			throw new NullPointerException("Kein Element vorhanden");

		ListElement neu = new ListElement(neuesObject, aktuell);
		if (vorgaenger == null)
			setKopf(neu);
		else {
			vorgaenger.setNaechstes(neu);
			vorgaenger = neu;
		} // da aktuell nicht versetzt wird
	}

	// Loeschen des Listenelements nach dem aktuellen Element aktuell
	void loescheNachfolger() {
		// aktuelles Element muss vorhanden sein
		if (aktuell.getNaechstes() == null)
			throw new NullPointerException("Kein Element vorhanden");

		aktuell.setNaechstes(aktuell.getNaechstes().getNaechstes());
	}

	// Loeschen des aktuellen Listenelements aktuell
	void loescheElement() {
		// aktuelles Element muss vorhanden sein
		if (aktuell == null)
			throw new NullPointerException("Es ist schon kein Element vorhanden");

		if (vorgaenger == null) {
			setKopf(aktuell.getNaechstes());
		} else {
			vorgaenger.setNaechstes(aktuell.getNaechstes());
		}
	}

	// Anzahl der Listenelemente ermitteln
	int zaehleElemente() {
		int zaehler = 0;
		ListElement zv = getKopf();
		while (zv != null) {
			zaehler++;
			zv = zv.getNaechstes();
		}
		return zaehler;
	}

	int gibWertvonElement_i(int i) {
		// Eine leere Liste enthaelt 0 Listenelemente.
		int anzahl = 0;
		// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
		ListElement elem = getKopf();
		while (elem != null) {
			// bearbeite aktuelles Element
			anzahl++;
			// gehe ein Element weiter

			if (anzahl == i) {
				System.out.println("Das " + i + ".te Element hat den Wert " + elem.getDaten());
				return elem.getDaten();
			}

			elem = elem.getNaechstes();

		}
		System.out.println("Das " + i + ".te Element existiert nicht!");
		return -1;

	}

	// letztes und vorletztes Listenelement ermitteln
	void findeEnde() {
		vorgaenger = null;
		aktuell = getKopf();

		// Liste leer?
		if (aktuell == null)
			return;
		// nein, Ende suchen:
		while (aktuell.getNaechstes() != null) {
			vorgaenger = aktuell;
			aktuell = aktuell.getNaechstes();
		}
		// aktuell verweist jetzt entweder auf null (wenn die Liste leer ist)
		// oder aber auf das letzte Listenelement
		// vorgaenger verweist jetzt entweder auf null (wenn die Liste leer ist
		// oder nur ein Element enthaelt) oder aber auf das vorletzte Listenelement
	}

	int getVorgaengerDaten() {
		// aktuelles Element muss vorhanden sein
		if (vorgaenger == null)
			throw new NullPointerException("kein aktuelles Element");

		return vorgaenger.getDaten();
	}

	int getKopfDaten() {
		// Listenkopf muss vorhanden sein, d.h. Liste darf nicht leer sein
		// if (kopf == null)
		if (istLeer())
			throw new NullPointerException("Liste ist leer");

		return getKopf().getDaten();
	}

	int getEndeDaten() {
		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		return getAktuelleDaten();
	}

	boolean einfuegeEndeNeu(int neuesObject) {
		ListElement neu = new ListElement(neuesObject);

		vorgaenger = null;
		aktuell = getKopf();

		// Liste leer?
		if (aktuell == null) {
			setKopf(neu);
			return true;
		}
		// nein, Ende suchen:
		while (aktuell.getNaechstes() != null) {
			vorgaenger = aktuell;
			aktuell = aktuell.getNaechstes();
		}

		aktuell.setNaechstes(neu);
		return true;
	}

	void konkateniere(Liste l) {
		// letztes Listenelement der aktuellen Liste ermitteln
		findeEnde();

		// Falls aktuell == null gilt, dann ist die Liste leer und wir setzen
		// den Kopf der aktuellen Listen auf den Listenkopf des Arguments l.
		if (aktuell == null)
			setKopf(l.getKopf());
		// Andernfalls wird die Nachfolger-Referenz des bisher letzten Elements
		// auf auf den Listenkopf des Arguments l gesetzt.
		else
			aktuell.setNaechstes(l.getKopf());
	}

	void spiegeln() {
		// Falls die Liste leer ist, ist nichts zu tun.
		if (getKopf() != null) {
			// In der Liste spiegel wird das Spiegelbild der Original-Liste aufgebaut.
			Liste spiegel = new Liste();

			// Wir durchlaufen die aktuelle Liste und fügen Kopien
			// der Listenelemente vorne an die Liste spiegel an.
			// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
			ListElement elem = getKopf();
			while (elem != null) {
				spiegel.einfuegeKopf(elem.getDaten());
				elem = elem.getNaechstes();
			}

			// Nun noch den Kopf der aktuellen Liste
			// auf den Kopf der neu erzeugten Liste spiegel setzen.
			setKopf(spiegel.getKopf());

			// Auf die Listenelemente der Original-Liste können wir jetzt nicht mehr
			// zugreifen. Sie werden irgendwann vom garbage collector aufgesammelt.
		}
	}

	void spiegelnInSitu() {
		ListElement prev, elem, next;

		prev = getKopf();
		// Liste leer? falls ja, nichts zu tun
		if (prev == null)
			return;

		elem = prev.getNaechstes();
		// einelementige Liste? falls ja, nichts zu tun
		if (elem == null)
			return;

		while (elem != null) {
			next = elem.getNaechstes();
			elem.setNaechstes(getKopf());
			setKopf(elem);
			prev.setNaechstes(next);
			elem = next;
		}
	}

	void spiegelnInSituV2() // Ohne Hilfsliste, nur temporaere Listelemente
	{
		if (!istLeer()) {
			// temporaere ListElemente
			ListElement temp1 = getKopf(), temp2 = getKopf().getNaechstes(); // vorgaenger und aktuell
			// Durchlauf
			while (temp2 != null) {
				getKopf().setNaechstes(temp2.getNaechstes());
				temp2.setNaechstes(temp1);
				temp1 = temp2;
				temp2 = getKopf().getNaechstes();
			}
			setKopf(temp1);
		}
	}

	boolean vergleiche(Liste l) {
		// Wird der Fall 1 auch dann korrekt behandelt,
		// wenn die folgenden beiden Anweisungen auskommentiert werden?
		// Fall 1: beide Listen sind leer und somit gleich
		if (getKopf() == null && l.getKopf() == null)
			return true;

		// Fall 2: mindestens eine der Listen ist nicht leer
		// Wir durchlaufen die beiden Listen und vergleichen jeweils
		// die in den beiden Listenelementen enthaltenen Daten.
		ListElement elem = getKopf(), lElem = l.getKopf();
		while (elem != null && lElem != null) {
			// Fall 2a: die Daten in den beiden betrachteten Listenelementen sind
			// verschieden
			if (elem.getDaten() != lElem.getDaten())
				return false;
			// gehe zum jeweils naechsten Listenelement
			elem = elem.getNaechstes();
			lElem = lElem.getNaechstes();
		}

		// Wenn wir hierher kommen, ist mindestens eine der beiden
		// Referenzen elem bzw. lElem gleich null;
		// Fall 2b: wenn nur eine der beiden Referenzen gleich null ist,
		// dann haben die beiden Listen unterschiedliche Laengen, sind also nicht gleich
		// Fall 2c: wenn beide Referenzen gleich sind, d.h., wenn
		// elem == lElem == null gilt, dann sind die beiden Listen gleich
		return elem == lElem;
	}

	void loescheKopf() {
		// Liste darf nicht leer sein
		if (getKopf() == null)
			throw new NullPointerException("Liste ist leer");

		setKopf(getKopf().getNaechstes());
	}

	void loescheEnde() {
		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		loescheElement();
	}

	void einfuegeEnde(int neuesObject) {
		ListElement neu = new ListElement(neuesObject);

		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		// falls aktuell == null gilt, dann ist die Liste leer und wir fuegen am
		// Listenkopf ein
		if (aktuell == null)
			setKopf(neu);
		// andernfalls wird die Nachfolger-Referenz des bisher letzten Elements auf das
		// neue Element gesetzt
		else
			aktuell.setNaechstes(neu);
	}

	public ListElement getKopf() {
		return kopf;
	}

	public void setKopf(ListElement kopf) {
		this.kopf = kopf;
	}
}
