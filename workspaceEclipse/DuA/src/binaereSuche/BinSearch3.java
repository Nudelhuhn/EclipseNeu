package binaereSuche;

public class BinSearch3
{
	/*
	 * The method returns whether 1 if the key element was found in the array or -1 if the key
	 * element was not found. The method divides the array in half and reuses recursive the half
	 * which contains the key element until the element was found or could not be found. If the
	 * key was found a syso method will print its index in the array. Additionally the count of
	 * the pairwise comparisons is also printed.
	 */
	private static int countRek = 0;
	public static int searchRek(int[] arr, int key, int left, int right) {
		int middle = (left + right) / 2;
		countRek++;
		if(left > right) {
			System.out.println("Element not found" + "\n" + countRek + " pairwise comparisons");
			countRek = 0;
			return -1;
		}
		if(key < arr[middle]) {
			return searchRek(arr, key, left, middle - 1);
		}
		else if(key > arr[middle]) {
			return searchRek(arr, key, middle + 1, right);
		}else {
			System.out.println("Element found at position " + middle + "\n" + countRek + " pairwise comparisons");
			countRek = 0;
			return 1;
		}
	}
	
	public static int searchIt(int[] arr, int key, int left, int right) {
		int middle;
		int counter = 0;
		while(left <= right) {
			counter++;
			middle = (left + right) / 2;
			if(key < arr[middle]) {
				right = middle - 1;
			}
			else if(key > arr[middle]) {
				left = middle + 1;
			}else {
				System.out.println("Element found at position " + middle + "\n" + counter + " pairwise comparisons");
				return 1;
			}
		}
		System.out.println("Element not found" + "\n" + counter + " pairwise comparisons");
		return -1;
	}
	
	
	public static void main(String[] args) {
		int[] arr = {2,3,4,5,6,7,11,23,44};
//		for(int i = 1; i < 10; i*=2) {			
//			searchRek(arr, i, 0, arr.length);
//			searchIt(arr, i, 0, arr.length);
//			System.out.println();
//		}
		
		int[] arr2 = new int[100000];
		for(int i = 0; i < arr2.length; i++) {
			arr2[i] = i;
		}
		
		long tmp = java.lang.System.currentTimeMillis();
		searchRek(arr2, 6, 0, arr2.length-1);
		System.out.println(java.lang.System.currentTimeMillis() - tmp);
		System.out.println();
		tmp = java.lang.System.currentTimeMillis();
		searchIt(arr2, 6, 0, arr2.length-1);
		System.out.println(java.lang.System.currentTimeMillis() - tmp);
	}
}
