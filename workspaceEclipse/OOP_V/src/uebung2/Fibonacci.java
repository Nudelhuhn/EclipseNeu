package uebung2;

public class Fibonacci
{
	public static long[] cache = new long[100];
	
	public static long fibRecursive(int n)
	{
		if(n > 92) {
			throw new IllegalArgumentException("Argument zu gro�!");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Argument zu klein!");
		}
		if (n <= 1)
		{
			return n;
		}
		if(cache[n] > 0) {
			return cache[n];
		}
		long fib1 = fibRecursive(n - 1);
		long fib2 = fibRecursive(n - 2);
		
		cache[n] = fib1 + fib2;
		return fib1 + fib2;
	}

	
	public static long fibIterative(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Argument zu klein!");
		}
		if(n == 1) {
			return n;
		}
		long x = 0;
		long y = 1;
		long z = 0;
//		long nextfib = 0;
		for(int i = 1; i < n; i++) {
//			if(i % 2 == 0) {
//				x = nextfib;
//			}else {
//				y = nextfib;
//			}
//			nextfib = x + y;
			z = x + y;
			x = y;
			y = z;
			if(z < 0) {
				throw new IllegalArgumentException("Argument zu gro�!");
			}
		}
//		return nextfib;
		return z;
	}
	
	public static void main(String[] args)
	{
		for(int i = 0; i < 100; i++) {
			System.out.println("(" + i + ")" + "\t" + fibRecursive(i));
//			System.out.println("(" + i + ")" + "\t" + fibIterative(i));
		}
	}
}

// - steigende Verz�gerung der Konsolenausgabe
// - bei Rekursion zu langsam bei etwa i = 54
// - bei Iteration �bersteigung der Gr��e des Datentyps bei i = 92
// => Programm w�rde negative Zahlen ausgeben, sobald die gr��te darstellbare Zahl des
//   primitiven Datentyps dargestellt wurde
//   (long: 2^63 - 1, int: 2^31 - 1)


