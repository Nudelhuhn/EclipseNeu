package bubblesort;

public class Bubblesort
{

	private static long comparisons;
	private static long swaps;
	
	private static void sort(int[] arr) {
		arrAusgeben(arr);
		System.out.println();
		boolean wasSwapped = true;
		
		for(int i = arr.length-1; i > 0 && wasSwapped; i--) {
			wasSwapped = false;
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tmp;
					swaps++;
					
					wasSwapped = true;
				}
				comparisons++;
				/*
				 * Improvement of algorithm (O(n²) => O(n)), by
				 * adding an extra boolean value to query if there was an exchange of elements or not,
				 * because otherwise the algorithm will always do the maximum number of passes, not matter
				 * if the algorithm is already sorted
				 */
			}
			arrAusgeben(arr);
			System.out.println("Anz. Vergleiche: " + comparisons);
			System.out.println("Anz. Vertauschungen: " + swaps);
		}
	}

	private static void arrAusgeben(int[] arr)
	{
		for(int k : arr) {
			System.out.print(k + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		int[] a = {99,33,11,22,1};
		int[] b = {1,2,3,4,5,6};
		int[] c = {6,5,4,3,2,1};
		sort(a);
		comparisons = swaps = 0;
		System.out.println("------------\n");
		sort(b);
		comparisons = swaps = 0;
		System.out.println("------------\n");
		sort(c);
	}

}
