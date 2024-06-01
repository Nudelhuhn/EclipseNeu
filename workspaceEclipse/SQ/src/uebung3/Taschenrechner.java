package uebung3;

public class Taschenrechner
{
	public static int add(int a, int b)
	{
		if(a == 0) {
			return b;
		}else if(b == 0) {
			return a;
		}
		int r = a + b;
		/*
		 * ^ = bitweise prüfendes XOR
         * => das erste Bit (von links) wird beim Überlauf zu 1 (negativ)
		 */
		if (((a ^ r) & (b ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
		return r;
	}

	public static int sub(int a, int b)
	{
		if(a == 0) {
			return b;
		}if(b == 0) {
			return a;
		}
		int r = a - b;
		if (((a ^ b) & (a ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
		return r;
	}

	public static int mul(int a, int b)
	{
		if(a == 1) {
			return b;
		}else if(b == 1) {
			return a;
		}
		long r = (long)a * (long)b;
		if ((int)r != r) {
			throw new ArithmeticException("integer overflow");
		}
		return (int)r;
	}

	public static int div(int a, int b)
	{
		if(a == 1 && b > 1) {
			return 0;
		}
		int q = a/b;
		if ((a & b & q) >= 0)
		{
			return q;
		}
		throw new ArithmeticException("integer overflow");

	}
	
	//Aufgabe 4/5
	
	public static double percent(int baseValue, double percentage) {
		int result;
		if(percentage == 100) {
			result = baseValue;
		}
		else if(percentage == 0) {
			result = 0;
		}
	    result = (int)(baseValue * (percentage / 100));
	    return result;
	}

	public static double calculateVAT(double netValue, double vatRate) {
	    return (netValue * (vatRate / 100));
	}

	public static void main(String[] args)
	{
//		System.out.println(add(100, 100));
//		System.out.println(sub(sub(100, 20), 100));
//		System.out.println(mul(mul(10000, 10000),10000));
//		System.out.println(div(10, 4));
	}
}
