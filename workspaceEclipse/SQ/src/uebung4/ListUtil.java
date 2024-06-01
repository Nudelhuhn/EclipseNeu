package uebung4;

public class ListUtil {
	/* ausnahmsweise keine main-Methode */
	
	/* liefert Anzahl der Listenelemente */
	public static int getLength(int[] list) {
		return list.length;
	}
	
	/* Pruefung auf leere Liste */
	public static boolean isEmpty(int[] list){
		return (list.length == 0);
	}
	
	/* liefert maximales Element */
	public static int getLargest(int[] list) {
        int max = Integer.MIN_VALUE;

        if (isEmpty(list)) {
             throw new RuntimeException("Liste ist leer.");
        }
        
        for (int i = 0; i < list.length; i++) {
            if (list[i] > max) {
                max = list[i];
            }
        }
        return max;
    }
	
	/* liefert minimales Element */
	public static int getSmallest(int[] list) {
        int min = Integer.MAX_VALUE;

        if (isEmpty(list)) {
            throw new RuntimeException("Liste ist leer.");
        }
        
        for (int i = 0; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
            }
        }
        return min;
    }

	public static void main(String[] args) {
		int[] a = null;
		System.out.println(getLength(a));
	}
}
