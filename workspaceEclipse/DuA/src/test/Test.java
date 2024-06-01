package test;

public class Test {

	public static int wasTueIch(int[] a, int lower, int upper) {
		int middle = lower + ((upper - lower) / 2);

		// Abbruchkriterium der Rekursion
		if (upper == lower) {
			return a[lower];
		}

		if (a[middle] < a[upper]) {
			return wasTueIch(a, lower, middle);
		} else {
			return wasTueIch(a, middle + 1, upper);
		}
	}

	private static int counter = 0;
	public static int fibonacci(int n) {
		counter++;
		if(n < 1) {
			return 1;
		}else {
			return fibonacci(n-1) * fibonacci(n-2);
		}
	}

	public static void main(String[] args) {
		int [] a = {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5};
		int [] b = {15,16,17,1};
		int [] c = {4,5,6,7};
		System.out.println(wasTueIch(c, 0, c.length - 1));

		int n = 6;
		int count = 0;

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				count++;
		System.out.println(count);

		count = 0;
		for (int i = n; i > 0; i--)
			for (int j = i; j < n; j++)
				count++;
		System.out.println(count);

		count = 0;
		for (int i = 1; i <= n / 2; i++)
			for (int j = 1; j < n * n; j++)
				count++;
		System.out.println(count);

		count = 0;
		int i = n;
		do {
			count++;
			i = i / 3;
		} while (i > 0);
		System.out.println(count);
		
		int count = 0;
		System.out.println("n count");
		for(int n = 0; n <= 20; n++) {
			for (int i = 1; i < n; i++) {
				for(int j = 1; j < n; j*=2) {
					count++;
				}
			}
			System.out.println(n + " " + count);
		}
		
		System.out.println("-------");
		
		count = 0;
		System.out.println("n count");
		for(int n = 0; n <= 20; n++) {
			count = 0;
			for(int i = 0; i <= n; i+=2) {
				for(int j = 0; j < n * n; j++) {
					count++;
				}
			}
			System.out.println(n + " " + count);
		}
		//Gauß
//		System.out.println();
//		for(int i = 0; i < n; i++)
//			System.out.println(i*(i-1)/2);
		
		for(int i = 0; i < 20; i++) {
			fibonacci(i);
			System.out.println(i + " " + counter);
			counter = 0;
		}
	}

}
