package lists;

public class Liste
{
	ListElement kopf;
	private ListElement aktuell;
	private ListElement vorgaenger;

	boolean istLeer()
	{
		return kopf == null;
	}

	void durchlaufe()
	{
		// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
		ListElement elem = kopf;
		while (elem != null)
		{
			// bearbeite aktuelles Element
			System.out.print(elem.getDaten() + " ");
			// gehe ein Element weiter
			elem = elem.getNaechstes();
		}
		System.out.println();
	}

	int zaehleElemente()
	{
		// Eine leere Liste enthaelt 0 Listenelemente.
		int anzahl = 0;
		// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
		ListElement elem = kopf;
		while (elem != null)
		{
			// bearbeite aktuelles Element
			anzahl++;
			// gehe ein Element weiter
			elem = elem.getNaechstes();
		}
		return anzahl;
	}

	int gibWertvonElement_i(int i)
	{
		// Eine leere Liste enthaelt 0 Listenelemente.
		int anzahl = 0;
		// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
		ListElement elem = kopf;
		while (elem != null)
		{
			// bearbeite aktuelles Element
			anzahl++;
			// gehe ein Element weiter

			if (anzahl == i)
			{
				System.out.println("Das " + i + ".te Element hat den Wert " + elem.getDaten());
				return elem.getDaten();
			}

			elem = elem.getNaechstes();

		}
		System.out.println("Das " + i + ".te Element existiert nicht!");
		return -1;

	}

	boolean finde(int einObject)
	{
		vorgaenger = null;
		aktuell = kopf;
		while (aktuell != null)
		{
			if (einObject == aktuell.getDaten())
				return true;
			else
			{
				vorgaenger = aktuell;
				aktuell = aktuell.getNaechstes();
			}
		}
		return false;
	}

	// letztes und vorletztes Listenelement ermitteln
	void findeEnde()
	{
		vorgaenger = null;
		aktuell = kopf;

		// Liste leer?
		if (aktuell == null)
			return;
		// nein, Ende suchen:
		while (aktuell.getNaechstes() != null)
		{
			vorgaenger = aktuell;
			aktuell = aktuell.getNaechstes();
		}
		// aktuell verweist jetzt entweder auf null (wenn die Liste leer ist)
		// oder aber auf das letzte Listenelement
		// vorgaenger verweist jetzt entweder auf null (wenn die Liste leer ist
		// oder nur ein Element enthaelt) oder aber auf das vorletzte Listenelement
	}

	int getAktuelleDaten()
	{
		// aktuelles Element muss vorhanden sein
		if (aktuell == null)
			throw new NullPointerException("kein aktuelles Element");

		return aktuell.getDaten();
	}

	int getVorgaengerDaten()
	{
		// aktuelles Element muss vorhanden sein
		if (vorgaenger == null)
			throw new NullPointerException("kein aktuelles Element");

		return vorgaenger.getDaten();
	}

	int getKopfDaten()
	{
		// Listenkopf muss vorhanden sein, d.h. Liste darf nicht leer sein
		// if (kopf == null)
		if (istLeer())
			throw new NullPointerException("Liste ist leer");

		return kopf.getDaten();
	}

	int getEndeDaten()
	{
		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		return getAktuelleDaten();
	}

	void einfuegeKopf(int neuesObject)
	{
		/*
		 * ListElement neu = new ListElement(neuesObject, kopf); kopf = neu;
		 */
		kopf = new ListElement(neuesObject, kopf);

	}

	void einfuegeHinter(int neuesObject)
	{
		// Vorhandenes Element muss angegeben sein
		if (aktuell == null)
			throw new NullPointerException();

		// Einfuegen hinter Element aktuell
		ListElement neu = new ListElement(neuesObject, aktuell.getNaechstes());
		aktuell.setNaechstes(neu);
	}

	void einfuegeVor(int neuesObject)
	{
		// aktuelles Element muss vorhanden sein
		if (aktuell == null)
			throw new NullPointerException();

		ListElement neu = new ListElement(neuesObject, aktuell);
		if (vorgaenger == null)
			kopf = neu;
		else
		{
			vorgaenger.setNaechstes(neu);
			vorgaenger = neu;
		} // da aktuell nicht versetzt wird
	}

//	 Klausuraufgabe SoSe 22 Aufgabe 6 
//	 neues Element am Ende der Liste einfügen 
//	 neues Element darf nicht in der Liste vorhanden sein, sonst Fehlermeldung
	public void einfuegeEndeUnikat(int neuerWert)
	{
		ListElement neu = new ListElement(neuerWert);
		if (kopf == null)
		{
			kopf = neu;
			return;
		}
		if(kopf.getDaten() == neuerWert) {
			System.out.println("Wert bereits in Der Liste enthalten!");
			return;
		}
		ListElement elem = kopf;
		while (elem.getNaechstes() != null)
		{
			if (elem.getNaechstes().getDaten() == neuerWert)
			{
				System.out.println("Wert bereits in Der Liste enthalten!");
				return;
			}
			elem = elem.getNaechstes();
		}
		elem.setNaechstes(neu);
	}
	
	void einfuegeEnde(int neuesObject)
	{
		ListElement neu = new ListElement(neuesObject);

		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		// falls aktuell == null gilt, dann ist die Liste leer und wir fuegen am
		// Listenkopf ein
		if (aktuell == null)
			kopf = neu;
		// andernfalls wird die Nachfolger-Referenz des bisher letzten Elements auf das
		// neue Element gesetzt
		else
			aktuell.setNaechstes(neu);
	}

	boolean einfuegeEndeNeu(int neuesObject)
	{
		ListElement neu = new ListElement(neuesObject);

		vorgaenger = null;
		aktuell = kopf;

		// Liste leer?
		if (aktuell == null)
		{
			kopf = neu;
			return true;
		}
		// nein, Ende suchen:
		while (aktuell.getNaechstes() != null)
		{
			vorgaenger = aktuell;
			aktuell = aktuell.getNaechstes();
		}

		aktuell.setNaechstes(neu);
		return true;
	}

	void loescheNachfolger()
	{
		// Nachfolger von aktuell muss vorhanden sein
		if (aktuell.getNaechstes() == null)
			throw new NullPointerException("kein Nachfolger vorhanden");

		aktuell.setNaechstes(aktuell.getNaechstes().getNaechstes());
	}

	void loescheElement()
	{
		// aktuelles Element muss vorhanden sein
		if (aktuell == null)
			throw new NullPointerException("kein aktuelles Listenelement vorhanden");

		if (vorgaenger == null)
			kopf = aktuell.getNaechstes();
		else
			vorgaenger.setNaechstes(aktuell.getNaechstes());
	}

	void loescheKopf()
	{
		// Liste darf nicht leer sein
		if (kopf == null)
			throw new NullPointerException("Liste ist leer");

		kopf = kopf.getNaechstes();
	}

	void loescheEnde()
	{
		// letztes Listenelement ermitteln, d.h. aktuell und vorgaenger korrekt setzen
		findeEnde();

		loescheElement();
	}

	void konkateniere(Liste l)
	{
		// letztes Listenelement der aktuellen Liste ermitteln
		findeEnde();

		// Falls aktuell == null gilt, dann ist die Liste leer und wir setzen
		// den Kopf der aktuellen Listen auf den Listenkopf des Arguments l.
		if (aktuell == null)
			kopf = l.kopf;
		// Andernfalls wird die Nachfolger-Referenz des bisher letzten Elements
		// auf auf den Listenkopf des Arguments l gesetzt.
		else
			aktuell.setNaechstes(l.kopf);
	}

	void spiegeln()
	{
		// Falls die Liste leer ist, ist nichts zu tun.
		if (kopf != null)
		{
			// In der Liste spiegel wird das Spiegelbild der Original-Liste aufgebaut.
			Liste spiegel = new Liste();

			// Wir durchlaufen die aktuelle Liste und fügen Kopien
			// der Listenelemente vorne an die Liste spiegel an.
			// Die lokale Variable elem verweist auf das gerade betrachtete Listenelement.
			ListElement elem = kopf;
			while (elem != null)
			{
				spiegel.einfuegeKopf(elem.getDaten());
				elem = elem.getNaechstes();
			}

			// Nun noch den Kopf der aktuellen Liste
			// auf den Kopf der neu erzeugten Liste spiegel setzen.
			kopf = spiegel.kopf;

			// Auf die Listenelemente der Original-Liste können wir jetzt nicht mehr
			// zugreifen. Sie werden irgendwann vom garbage collector aufgesammelt.
		}
	}

	void spiegelnInSitu()
	{
		ListElement prev, elem, next;

		prev = kopf;
		// Liste leer? falls ja, nichts zu tun
		if (prev == null)
			return;

		elem = prev.getNaechstes();
		// einelementige Liste? falls ja, nichts zu tun
		if (elem == null)
			return;

		while (elem != null)
		{
			next = elem.getNaechstes();
			elem.setNaechstes(kopf);
			kopf = elem;
			prev.setNaechstes(next);
			elem = next;
		}
	}

	void spiegelnInSituV2() // Ohne Hilfsliste, nur temporaere Listelemente
	{
		if (!istLeer())
		{
			// temporaere ListElemente
			ListElement temp1 = kopf, temp2 = kopf.getNaechstes(); // vorgaenger und aktuell
			// Durchlauf
			while (temp2 != null)
			{
				kopf.setNaechstes(temp2.getNaechstes());
				temp2.setNaechstes(temp1);
				temp1 = temp2;
				temp2 = kopf.getNaechstes();
			}
			kopf = temp1;
		}
	}

	boolean vergleiche(Liste l)
	{
		// Wird der Fall 1 auch dann korrekt behandelt,
		// wenn die folgenden beiden Anweisungen auskommentiert werden?
		// Fall 1: beide Listen sind leer und somit gleich
		if (kopf == null && l.kopf == null)
			return true;

		// Fall 2: mindestens eine der Listen ist nicht leer
		// Wir durchlaufen die beiden Listen und vergleichen jeweils
		// die in den beiden Listenelementen enthaltenen Daten.
		ListElement elem = kopf, lElem = l.kopf;
		while (elem != null && lElem != null)
		{
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

}
