package uebung2;

public class ExpressionEvaluation
{
	public static int plus(int a, int b)
	{
		return a + b;
	}

	public static int minus(int a, int b)
	{
		int result = a - b;
		return result;
	}

	public static int times(int a, int b)
	{
		int x = a;
		int y = b;
		int result = x * y;
		return result;
	}

	// computes (a + b)**2
	public static int binom1(int a, int b)
	{
		int sum = plus(a, b);
		int result = times(sum, sum);
		return result;
	}

	// computes a**2 + 2*a*b + b**2
	public static int binom2(int a, int b)
	{
		int aSquare = times(a, a);
		int bSquare = times(b, b);
		int ab = times(a, b); //Rbinom2times3
		int abDouble = times(ab, 2);
		int result = plus(aSquare, abDouble);
		result = plus(result, bSquare);
		return result;
	}

	public static void main(String[] args)
	{
		int x = 5;
		int y = 6;
		int r1 = binom1(x, y);
		System.out.println("Ergebnis von binom1(" + x + ", " + y + "): " + r1);
		int r2 = binom2(x, y);
		System.out.println("Ergebnis von binom2(" + x + ", " + y + "): " + r2);
		int fak = times(times(times(times(times(1, 2), 3), 4), 5), 6); 
		System.out.println("6! = " + fak);
	}
}

//public class ExpressionEvaluation {
//    public int plus(int a, int b) {
//        return a + b;
//    }
//
//    public int minus(int a, int b) {
//        int result = a - b;
//        return result;
//    }
//
//    public int times(int a, int b) {
//        int x = a;
//        int y = b;
//        int result = x * y;
//        return result;
//    }
//
//    public int binom1(int a, int b) {
//        int sum = plus(a, b);
//        int result = times(sum, sum);
//        return result;
//    }
//
//    public int binom2(int a, int b) {
//        int aSquare = times(a, a);
//        int bSquare = times(b, b);
//        int ab = times(a, b);
//        int abDouble = times(ab, 2);
//        int result = plus(aSquare, abDouble);
//        result = plus(result, bSquare);
//        return result;
//    }
//
//    public static void main(String[] args) {
//        ExpressionEvaluation eE = new ExpressionEvaluation();
//        int x = 5;
//        int y = 6;
//        int r1 = eE.binom1(x, y);
//        System.out.println("Ergebnis von binom1(" + x + ", " + y + "): " + r1);
//        int r2 = eE.binom2(x, y);
//        System.out.println("Ergebnis von binom2(" + x + ", " + y + "): " + r2);
//        int fak = eE.times(eE.times(eE.times(eE.times(eE.times(1, 2), 3), 4), 5), 6);
//        System.out.println("6! = " + fak);
//    }
//}

