package dac;

public class search
{
	public static int wasTueIch(int[] a, int lower, int upper)
	{
		int middle = lower + ((upper - lower) / 2);

		// Abbruchkriterium der Rekursion
		if (upper == lower)
		{
			return a[lower];
		}

		if (a[middle] < a[upper])
		{
			return wasTueIch(a, lower, middle);
		} else
		{
			return wasTueIch(a, middle + 1, upper);
		}
	}

	public static void s1(int n)
	{
		int count = 0;
		for (int i = 1; i <= n; i += 3) {
			for (int j = 0; j < n; j++)
				count++;
			System.out.println(i + " " + count);
		}
	}

	public static void s2(int n)
	{
		int count = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= 10; j++)
				for (int k = 1; k <= j; k++)
					count++;
			System.out.println(i + " " + count);
		}
	}

	public static void s3(int n)
	{
		int count = 0;
		for (int i = 1; i <= n; i *= 3) {
			count++;
			System.out.println(i + " " + count);
		}
	}

	static int counter = 0;
	static int funktionX(int n)
	// Komplexitätsmaß ist hier die Anzahl der rekursiven Aufrufe
	{
		counter++;
		if (n < 1)
			return 1;
		return funktionX(n - 1) * funktionX(n - 2);
	}

	public static void main(String[] args) {
		s1(10);
		System.out.println();
		s2(10);
		System.out.println();
		s3(10);
		for(int i = 0; i < 10; i++) {
			funktionX(i);
			System.out.println(counter);
		}
	}
}
