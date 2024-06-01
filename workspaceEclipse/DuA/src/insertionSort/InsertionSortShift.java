//control+shift+o

package insertionSort;

public class InsertionSortShift
{
	private static long vergleiche;
	private static long vertauschungen;

	public static void insertionsortShift(int list[])
	{
		int j;
		int hilf;

		for (int i = 1; i < list.length; i++)
			// i = 1, because the first value is always assumed to be sorted already
		{
			hilf = list[i];

			// finde den Einfuegeplatz j des Elements hilf
			j = i - 1;

			/*
			 * you go through form the element which is currently beeing looked at and
			 * compare it backwards to the other elements in the array, so after the last
			 * number was compared or the number was bigger than the hilf variable the while
			 * loop will terminate
			 */
			while ((j >= 0) && (list[j] > hilf))
			{
				j = j - 1;
				vergleiche++;
			}
			if ((j >= 0) && (list[j] <= hilf)) {
				vergleiche++;				
			}
			j = j + 1;

			// Verschieben der Elemente des Zielteils zwischen j und i nach rechts
			for (int k = i; k > j; k--)
			{
				list[k] = list[k - 1];
				vertauschungen++;
			}

			// fuege hilf ein
			list[j] = hilf;

			// Ausgabe des Feldes nach i-tem Lauf
			/*
			 * System.out.print("Feld nach Durchlauf "+ i + " :"); for (int z=0;
			 * z<list.length; z++) System.out.print( list[z]+", " ); System.out.println();
			 */
		}
	}

	public static void main(String args[])
	{
		vergleiche = 0;
		vertauschungen = 0;
		// int a[] = {12,4,3,11};
//		int a[] = { 56, 22, 79, 27, 9, 30, 61, 4, 69, 38, 52, 89, 23, 17, 68, 30 };
		// int a[] = {3,5};
		 int a[] = {5,3,1};
		// int a[] = {3};
		insertionsortShift(a);

		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + ", ");
		System.out.println();
		System.out.println("Anzahl Vergleiche: " + vergleiche);
		System.out.println("Anzahl Vertauschungen: " + vertauschungen);

		vergleiche = 0;
		vertauschungen = 0;
		int[] zahlenfolge = new int[100000];
		for (int i = 1; i < 100000; i++)
			zahlenfolge[i] = (int) (Math.random() * 100000);
		// zahlenfolge[i] = 100000 - i;
		// zahlenfolge[i] = i;
	}
}
