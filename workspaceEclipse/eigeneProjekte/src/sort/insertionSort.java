package sort;

//ALT + Shift + T

public class insertionSort {
	public static void insertionSortIterativ(int[] a) {
		int z1 = 0;
		int z2 = 1;
		int[] b = a;

		while (z2 < b.length) {
			if (b[z1] > b[z2]) {
				while ((b[z1] > b[z2]) && z1 >= 0) {
					z1--;
					if(z1 < 0) {
						break;
					}
				}
				int zv = b[z2];
				int i = z2;
				while (i > z1) {
					if (i - 1 == z1) {
						b[i] = zv;
						z1 = z2;
						z2++;
					} else {
						b[i] = b[i - 1];
						i--;
					}
				}
			} else {
				z1++;
				z2++;
			}
		}
		System.out.print("{");
		for (int j = 0; j < b.length; j++) {
			System.out.print(b[j] + ",");
		}
		System.out.print("}");
	}
}
