package stackUndQueue;

public class Queue {
	private int anfang;
	private int ende;
	private int arrGroe�e = 6;
	private int[] arr = new int[arrGroe�e];
	private boolean QueueLeer;

	// Leere Queue erstellen; Anfangs- und Endzeiger sind noch auf Null
	public Queue() {
		anfang = 0;
		ende = 0;
		QueueLeer = true;
	}

	// Gibt Element an, welches als n�chstes in der Reihenfolge dran ist
	public void front() {
		if (QueueLeer == false) {
			System.out.println("Anfang: " + anfang);
			System.out.println("Ende: " + ende);
		} else {
			System.out.println("Queue ist leer");
		}
	}

	// F�gt ein neues Element in die Warteschlange
	// Es wird gepr�ft, ob das Ende dem Anfgang entspricht (Queue ist voll) oder ob
	// das Ende im Array zum Start springen muss (zirkul�res Array)
	public void enqueue(int elem) {
		QueueLeer = false;
		if (ende + 1 != anfang && ende != (arrGroe�e - 1)) {
			if(ende + 2 == anfang) {
				arr[ende+1] = elem;
				ende++;
			}else {
				arr[ende] = elem;
				ende++;
			}
		} else {
			if (ende == arrGroe�e - 1) {
				if (ende * 0 == anfang || ende + 1 == anfang) {
					System.out.println("Queue ist voll");
				} else {
					ende = 0;
					arr[ende] = elem;
				}
			} else if (ende * 0 == anfang || ende + 1 == anfang) {
				System.out.println("Queue ist voll");
			}
		}
	}

	// entfernen des ersten Queue-Elements
	public void dequeue() {
		if (QueueLeer == true) {
			System.out.println("Aus einer leeren Queue, kann kein Element entfernt werden");
		} else {
			if (anfang == ende) {
				anfang = 0;
				ende = 0;
				QueueLeer = true;
			} else if (anfang == (arrGroe�e - 1)) {
				anfang = 0;
			} else {
				arr[anfang] = 0;
				anfang++;
			}
		}
	}

	// Queue ausgeben
	// Um die Ausgabe �bersichtlicher zu gestalten, werden alle Zellen mit "0" nicht
	// angezeigt
	public void showQueue() {
		for (int i = 0; i < (arrGroe�e - 1); i++) {
//			if (arr[i] != 0) {
				System.out.print(arr[i] + " ");
			}
//		}
	}
	
	
	
	private boolean queueFull = false;
	
	public int front1() {
        // �berpr�fen, ob die Schlange leer ist
        if (anfang == ende && !queueFull) {
            System.out.println("Die Schlange ist leer!");
            return -1; // Oder werfen Sie eine spezielle Exception
        }

        return arr[anfang];
    }

    public void enqueue1(int value) {
        // �berpr�fen, ob die Schlange voll ist
        if (anfang == ende && queueFull) {
            System.out.println("Die Schlange ist voll!");
            return;
        }

        // Einf�gen des Werts in die Schlange
        arr[ende] = value;
        ende = (ende + 1) % arr.length; // zirkul�rer Array-Index
        queueFull = (anfang == ende); // �berpr�fen, ob die Schlange jetzt voll ist => true
    }
}
