package uebung8;

import java.util.Arrays;

/**
 * This class implements the binary search algorithm and demonstrates it with examples.
 * @author Anne Lui
 */
public class BinarySearch {
	
	/**
	 * Finds an element in a sorted int array using the binary search approach.
	 * It compares the value of the key to the middle element of the array. 
	 * If they aren't equal, eliminate one half of the search area as the key can't be contained in it, and continue searching in the other half following the same procedure.
	 * This gets repeated until either the key value is found in the array or the search area is empty, meaning that the key does not exist in the array.
	 * 
	 * @param sortedArray The <b>sorted</b> array to be searched
	 * @param key The value to be searched for
	 * @return Index of the search key in the array if it's contained; otherwise -1
	 */
	public static int binarySearch(int[] sortedArray, int key) {
		
		// Define the initial search area as the full length of the array
		// left bound of the search area
		int leftIndex = 0;
		// right bound of the search area
		int rightIndex = sortedArray.length - 1;
		// position of the middle element
		int middleIndex;
		// index of the key in the array. initialize with -1 as 'not found'
		int keyIndex = -1;

		// continue searching as long as the bounds of the search area don't exceed each other
		while(leftIndex <= rightIndex) {
			// calculate new middleIndex as the floor of (rightIndex + leftIndex)/2
			middleIndex = (rightIndex + leftIndex) / 2;
			
			// check whether the middle element is equal to the key value. if yes, save the position and stop searching.
			if(sortedArray[middleIndex] == key) {
				keyIndex = middleIndex;
				break;
			}
			
			// the middle element is not equal to the key. determine the new search area. as the array is sorted, we can eliminate one half of the search area:
			// if the middle element is smaller than the key value, continue the search in the right half of the search area. 
			// 		the elements in the left half of the search area are smaller than the middle element (as the array is sorted) and are therefore also smaller than the key. 
			// 		-> the key can't be found in the left half.
			// 		The new left bound of the search area starts 1 position to the right of the current middle element.
			// otherwise the middle element is bigger than the key value. continue the search in the left half of the search area.
			
			if(sortedArray[middleIndex] < key) {	
				leftIndex = middleIndex + 1;		
			} else {
				rightIndex = middleIndex - 1;		
			}
		}
		
		return keyIndex;
	}
	
	
	/**
	 * Demonstrates the usage of {@link #binarySearch(int[], int)}.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] array = {12, 49, 22, 4, 202, 60, 32, 11, 6, 42};
		Arrays.sort(array);
		
		System.out.println(binarySearch(array, 11));
	}
	
}
