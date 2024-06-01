package binaereSuche;

public class ProfBinSearch
{
	public static int vergleiche = 0;

	public static int sucheBinaer(int[] a, int gesucht)
	{
		int n = a.length;
		int unten = 0;
		int oben = n - 1;
		while (unten <= oben)
		{
			int m = (unten + oben) / 2;

			vergleiche++;
			if (a[m] == gesucht)
			{

				System.out.println("Element gefunden an Indexposition " + m);
				return m;
			}

			// es werden nur die Vergleiche auf Gleichheit gezählt
			if (a[m] < gesucht)
				unten = m + 1;
			else
				oben = m - 1;

		}
		System.out.println("Element wurde nicht gefunden. ");
		return -1;
	}

	public static int sucheBinaerRekursiv(int[] a, int gesucht, int unten, int oben)
	{
		int m = (unten + oben) / 2;
		vergleiche++;
		if (a[m] == gesucht)
		{
			System.out.println("Element steht an Indexposition " + m);
			return m;
		}
		if (unten < oben)
		{
			if (a[m] > gesucht)
				return sucheBinaerRekursiv(a, gesucht, unten, m - 1);
			else
				return sucheBinaerRekursiv(a, gesucht, m + 1, oben);
		} else
		{
			System.out.println("Element nicht gefunden!");
			return -1;
		}
	}

	public static void main(String args[])
	{

		int b[] = new int[100000];
		for (int i = 0; i < b.length; i++)
			b[i] = i;

		int zahl = Integer.parseInt(args[0]);

		vergleiche = 0;
		sucheBinaer(b, zahl);
		System.out.println("Anzahl Vergleiche bei iterativer BS: " + vergleiche + "\n");
		
		vergleiche = 0;
		sucheBinaerRekursiv(b, zahl, 0, b.length - 1);
		System.out.println("Anzahl Vergleiche: " + vergleiche);
	}
}
