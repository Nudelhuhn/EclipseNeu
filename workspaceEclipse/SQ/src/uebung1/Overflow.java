package uebung1;
import java.math.BigInteger;

import uebung4.ListUtil;

public class Overflow {
	
	public static int[] myArray = new int[2];
	
	public static int addExact(int x, int y) {
		int r = x + y;
		if ((x >= 0 && y >= 0 && r < 0) || (x < 0 && y < 0 && r >= 0)) {
			throw new ArithmeticException("My own int overflow");
		}
		return r;
	}

	public static void main(String[] argc) {
		int largest = ListUtil.getLargest(myArray);
		
// Mathematisch gesehen gilt folgendes:
// a * a / a = a, also 100.000 * 100.000 /100.000 = 100.000
		
		//100.000*100.000 kann durch Integer nicht dargestellt werden => Overflow a*a = 1410065408
		int a = 100000;
		System.out.println("Ergebnis: " + (a * a / a));
		System.out.println("---------------");
		int value;
		value = Integer.MAX_VALUE - 1;
		for (int i = 0; i < 4; i++, value++) {
			System.out.println(value);
		}
		System.out.println("---------------");
		BigInteger largeValue = new BigInteger(Integer.MAX_VALUE + "");
		for (int i = 0; i < 4; i++) {
			System.out.println(largeValue);
			largeValue = largeValue.add(BigInteger.ONE);
		}
		System.out.println("---------------");
		value = Integer.MAX_VALUE - 1;
		for (int i = 0; i < 4; i++) {
			System.out.println(value);
			value = addExact(value, 1);
		}
		
		
	}
}
