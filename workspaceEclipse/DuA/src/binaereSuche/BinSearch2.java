package binaereSuche;

public class BinSearch2
{
	private static int upper;
	private static int lower;
	private static int middle;
	
	private static boolean lowerAndUpper_enabled = false;
	
	private static int counter;
	
	public static int binSearchRek(int[] arr, int key) {
		if(!lowerAndUpper_enabled) {
			lower = 0;
			upper = arr.length-1;
			lowerAndUpper_enabled = true;
		}
		middle = (lower + upper) / 2;
		if(lower > upper) {
			counter++;
			System.out.println(counter);
			counter = 0;
			lowerAndUpper_enabled = false;
			return -1;
		}
		if(arr[middle] > key) {
			counter++;
			upper = middle - 1;
			return binSearchRek(arr, key);
		}
		else if(arr[middle] < key) {
			counter++;
			lower = middle + 1;
			return binSearchRek(arr, key);
		}else {
			System.out.println(counter);
			counter = 0;
			lowerAndUpper_enabled = false;
			return 1;
		}
	}
	
	public static int binSearchIt(int[] arr, int key) {
		lower = 0;
		upper = arr.length-1;
		while(lower <= upper) {
			counter++;
			middle = (lower + upper) / 2;
			if(arr[middle] > key) {
				counter++;
				upper = middle - 1;
			}
			else if(arr[middle] < key) {
				counter++;
				lower = middle + 1;
			}else {
				System.out.println(counter);
				counter = 0;
				return 1;
			}
		}
		System.out.println(counter);
		counter = 0;
		return -1;
	}	
	
	public static void main(String[] args) {
		int[] arr = new int[] {0,1,2,3,4,5,6,11,14,17}; //arr.length = 10
		int tmp;
//		for(int i = 0; i < arr.length; i++) {
//			tmp = binSearchRek(arr, i);
			System.out.println(binSearchRek(arr, 14));
//			if(tmp == 1) {
//				System.out.println("Element " + i + " found at position " + i);
//			}else {
//				System.out.println("Element " + i + " was not found");
//			}
//		}
	}
}