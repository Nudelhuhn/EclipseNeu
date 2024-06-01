package stackUndQueue;

public class Stack {

	private int stackGroeße = 100;
	private int[] arr = new int[stackGroeße];
	private int zeiger;

	// legt neuen leeren Stack an (ist also wie empty())
	public Stack() {
		zeiger = 0;
	}

	// gibt true zurück, falls der stack leer sein sollte
	public boolean isempty() {
		System.out.println(zeiger == 0);
		return zeiger == 0;
	}

	// legt ein neues Element auf den stack
	public void push(int elem) {
		if(zeiger < stackGroeße) {
			arr[zeiger] = elem;
			zeiger++;
		}else {
			System.out.println("Stack ist voll");
		}
	}

	// entfernt das oberste Element des Stacks
	public void pop() {
		if (zeiger > 0) {
			zeiger--;
			arr[zeiger] = 0;
		}else {
			System.out.println("Von einem leeren Stack, können keine Elemente entfernt werden");
		}
	}

	// zeigt das aktuelle Element
	public void top() {
		if(zeiger == 0) {
			System.out.println("Stack ist leer");
		}else {
			System.out.println(arr[zeiger - 1]);
		}
	}
	
	//Stack anzeigen lassen
	public void showStack() {
		if(zeiger > 0) {
			for(int i = zeiger - 1; i >= 0; i--) {
				System.out.println(arr[i]);
			}
		}else {
			System.out.println("Stack ist leer");
		}
	}
}
