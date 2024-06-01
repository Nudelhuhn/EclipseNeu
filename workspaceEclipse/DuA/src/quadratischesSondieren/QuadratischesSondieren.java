package quadratischesSondieren;

/*
a)
eim geschlossenen Hashing auf einer Hashtabelle der Größe m, die als eindimensionales 
Array implementiert ist (d.h. Bucket-Größe = 1), kann es zu Kollisionen kommen.
 
Geben Sie die mathematische Funktion für die Kollisionsbehandlungsstrategie 
„quadratisches Sondieren mit wechselndem Vorzeichen“ an. 
Implementieren Sie anschließend eine Java-Methode für diese Kollisionsstrategie. Der 
Methode übergeben werden der ursprüngliche Hash-Wert h0 und der Kollisionszähler i
für die i-te Kollisionsbehandlung. (8 Punkte)


Mathematische Definition:

h2i-1(k) = (h(k) + i^2) mod m 	\
								|	1 <= i <= (m-1)/2
h2i(k) = (h(k) - (i^2)) mod m	/

In Worten: alle geraden Indexe i werden mit -i^2 subtrahiert, alle ungeraden werden mit i^2 addiert.
Da es sich dabei um Kollisionsbehandlung handelt, fängt i automatisch bei mindestens 1 an, aber kann maximal
bis zur Hälfte der Array-Größe aufzählen.
Die Hashfunktion mit Index i = 0 ist die triviale Funktion h(k) = k mod m, welche man zu Beginn jedes
neuem Elements anwendent.
*/

class QuadratischesSondieren {
	// ein geloeschtes Feldelement wird mit dem Wert Integer.MIN-VALUE
	// gekennzeichnet
	final int emptyValue = Integer.MIN_VALUE;
	Data emptyData;

	Data[] dataArray;
	int[] visitCounter;
	int arraySize;

	public QuadratischesSondieren(int size) {
		arraySize = size;
		dataArray = new Data[arraySize];
		emptyData = new Data(emptyValue);
		visitCounter = new int[arraySize];
	}

	public int hash(int key) {
		return key % arraySize;
	}

	public int quadhash1(int startHash, int i) {
		return ((startHash + i * i) % arraySize);
	}

	public int quadhash2(int startHash, int i) {
		// modifiziertes quadratisches Sondieren mit alternierendem Vorzeichen:
		// hi(x) = (h(x) + i²) mod m für ungerades i, dh. 1,3,5,..
		// hi(x) = (h(x) - i²) mod m für gerades i, dh. 2,4,6,..
		// Wichtig: negative Werte müssen auf Feldindizes abgebildet werden!

		if (i % 2 == 0)
			// nur bei negativen Hash-Werten ist Vorsicht geboten !!!
			// In java können bei Modulo Operationen negative Werte heraus kommen
			if (((startHash - i * i) % arraySize) < 0)
				return ((startHash - i * i) % arraySize + arraySize);
			else
				return ((startHash - i * i) % arraySize);
		else
			return ((startHash + i * i) % arraySize);
	}

	// keine Eindeutigkeit der neu einzufuegenden Werte gewaehrleistet
	// deshalb Member-Abfrage notwendig

	public void insert(Data data) {
		int key = data.dataValue;
		int hashValue = hash(key);
		int startHash = hashValue;
		int i = 0;
		if (!isMember(key)) {
			while (dataArray[hashValue] != null && dataArray[hashValue] != emptyData && i < arraySize) // hiermit wird
																										// bei komplett
																										// gefuellter
																										// Tabelle
			// Endlosschleife bei insert mit quadhash2 verhindert
			{
				visitCounter[hashValue]++;
				i++;
				hashValue = quadhash2(startHash, i);
			}
			if (i == arraySize)
				System.out.println("Die Tabelle ist voll. Der Wert " + key + " kann nicht mehr eingefuegt werden.");
			else {
				dataArray[hashValue] = data;
				visitCounter[hashValue]++;
			}
		} else
			System.out.println("Key " + key + " ist bereits enthalten\n");
	}

	public void insert1(Data data) {
		int key = data.dataValue;
		int hashValue = hash(key);
		int startHash = hashValue;
		int i = 0;

		if (!isMember(key)) {
			// hier fehlt die Abfrage i < arraySize, um eine Endlosschleife für quadhash1 zu
			// bekommen
			while (dataArray[hashValue] != null && dataArray[hashValue].dataValue != emptyValue)
			// s.o. Kommentar && i < arraySize )
			{
				visitCounter[hashValue]++;
				i++;
				hashValue = quadhash1(startHash, i);
			}
			dataArray[hashValue] = data;
			visitCounter[hashValue]++;
		} else
			System.out.println("Key " + key + " ist bereits enthalten\n");
	}

	public boolean isMember(int key) {
		int hashValue = hash(key);
		int startHash = hashValue;
		int hashCounter = 0;

		while ((dataArray[hashValue] != null) && (hashCounter < arraySize)) {
			visitCounter[hashValue]++;
			if (dataArray[hashValue].dataValue == key) {
				System.out.println("Key " + key + " ist an Position " + hashValue + " enthalten.\n");
				return true;
			}
			hashCounter++;
			hashValue = quadhash2(startHash, hashCounter);
		}
		System.out.println("Key " + key + " ist nicht enthalten.\n");

		if (dataArray[hashValue] == null)
			visitCounter[hashValue]++;
		return false;
	}

	public Data delete(int key) {
		int hashValue = hash(key);
		int startHash = hashValue;
		int i = 0;

		while ((dataArray[hashValue] != null) && (i < arraySize)) {
			visitCounter[hashValue]++;
			if (dataArray[hashValue].dataValue == key) {
				Data temp = dataArray[hashValue];
				dataArray[hashValue] = emptyData;
				return temp;
			}
			i++;
			hashValue = quadhash2(startHash, i);
		}

		if (dataArray[hashValue] == null) {
			visitCounter[hashValue]++;
			System.out.println("Zu loeschender Key " + key + " ist nicht in der Tabelle enthalten.\n");
		}
		return null;
	}

	public void dumpTable() {
		for (int i = 0; i < arraySize; i++)
			if (dataArray[i] != null) {
				int value = dataArray[i].dataValue;
				if (value == emptyValue)
					// geloeschtes Feld wird durch 'D' (Deleted) symbolisiert
					System.out.print("D" + ":" + visitCounter[i] + " ");
				else
					System.out.print(value + ":" + visitCounter[i] + " ");
			} else
				System.out.print("--" + ":" + visitCounter[i] + " ");
		System.out.println("\n");
	}

	// Ende Klasse HashTable
	
	public static void main(String[] args) {
		System.out.println(12 % 5);
	}
}

class Data {
	public int dataValue;

	public Data(int data) {
		dataValue = data;
	}
}
