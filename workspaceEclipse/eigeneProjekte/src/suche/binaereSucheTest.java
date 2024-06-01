package suche;

public class binaereSucheTest {

	public static void main(String[] args) {
		
		int[] array = {2,7,45,12,923,1,5,312,8,78,543,987,23,3,4};
		
		binaereSuche.sucheIterativ(array, 4);
		System.out.println();
		binaereSuche.sucheRekursiv(array, 4);

	}

}
