package stackUndQueue;

public class StackTest {

	public static void main(String[] args) {
		Stack neuerStack = new Stack();
		
		neuerStack.top();
		neuerStack.pop();
		System.out.println();
		
		neuerStack.isempty();
		System.out.println();
		
		neuerStack.push(99);
		neuerStack.push(93);
		neuerStack.push(9);
		neuerStack.push(59);
		neuerStack.push(71);
		neuerStack.push(1);
		neuerStack.showStack();
		System.out.println();
		
		neuerStack.pop();
		neuerStack.showStack();
		System.out.println();
		
		neuerStack.isempty();
	}

}
