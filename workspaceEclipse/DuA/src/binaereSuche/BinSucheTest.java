package binaereSuche;

public class BinSucheTest {

	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		int[] arr2 = new int[100000];
		int[] arr3 = {0,1,2,3,4,5,6,11,14,17};
		for(int i = 0; i < 100000; i++) {
			arr2[i] = i;
		}
		
		int key = 14;
		int key2 = 99999;
//		binItSuche.suche(arr2, key);
		
		int linkerZeiger = arr[0];
		int rechterZeiger = arr[arr.length-1];
		BinSearch.suche(arr3, key, linkerZeiger, rechterZeiger);
		
		int linkerZeiger2 = arr2[0];
		int rechterZeiger2 = arr2[arr2.length-1];
//		BinSearch.suche(arr2, key2, linkerZeiger2, rechterZeiger2);
		
		int linkerZeiger3 = arr3[0];
		int rechterZeiger3 = arr3[arr3.length-1];
		for(int i = 0; i < arr3.length-1; i++) {			
//			BinSearch.suche(arr3, i, linkerZeiger3, rechterZeiger3);
		}
	}

}
