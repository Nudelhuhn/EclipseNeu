package fakueltaet;

public class Fakultaet
{
	/*
	 * Halstead-Metrik anwenden fakRec
	 * N1 = 16
	 * N2 = 9
	 * n1 = 11
	 * n2 = 4
	 */

	public static int fakRec(int n) {
		if(n <= 0)
			return 1;
		return fakRec(n-1)*n;
	}
	
	/*
	 * Halstead-Metrik anwenden fakIt
	 * N1 = 21
	 * N2 = 12
	 * n1 = 12
	 * n2 = 6
	 */
	
//	public static int fakIt(int n) {
//		int result = 1;
//		for(int i = n; i > 0; i--) {
//			result *= i;
//		}
//		return result;
//	}
	
	public static int fakIt(int n) {
		int result = 1;
		int i = 1;
		while(i <= n) {
			result *= i;
			i++;
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		for(int i = 0; i <= 10; i++)
			System.out.println(i + " : " + fakRec(i));
		System.out.println("-----------------");
		for(int i = 0; i <= 10; i++)
			System.out.println(i + " : " + fakIt(i));
	}

}
