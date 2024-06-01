package uebung7;

public class Klasse {

	public static void main(String[] args) {
		
		int[] numbers = {0, 2, 3, 5, 10, 17, 40, 44, 55, 99};

		System.out.println(method(numbers, 0));
		System.out.println(method(numbers, 3));
		System.out.println(method(numbers, 5));
		System.out.println(method(numbers, 17));
		System.out.println(method(numbers, 18));
		System.out.println(method(numbers, 44));
		System.out.println(method(numbers, 55));
		System.out.println(method(numbers, 101));
	}
	
	public static int method(int[] l0, int l4) {
		int l3, l1 = 0;
		int l2 = l0.length+1;
		
		while(l1 < l2) {
			l3 = (l2 + l1) / 2;
			int l5 = l0[l3];
			if(l0[l3] > l4) {
				l2 = l3 - 1;		
			} else if(l5 < l4) {
				l1 = l3 + 1;			
			}
			if(l5 == l4) {			
				return l3;
			}
		}
		return -1;
	}
	
}
