package stackUndQueue;

public class QueueTest {

	public static void main(String[] args) {
		Queue test = new Queue();
		
		test.front();
		System.out.println();
		
		test.dequeue();
		System.out.println();
		
		test.enqueue1(12);
		test.front();
		System.out.println(test.front1());
		test.enqueue1(1);
		test.enqueue1(33);
		test.enqueue1(93);
		test.enqueue1(7);
//		test.dequeue();
//		System.out.println(test.front1());
		test.enqueue1(1);
		test.enqueue1(33);
		test.enqueue1(93);
		test.enqueue1(7);
//		test.enqueue(91);
//		test.front();
//		test.enqueue(91);
//		test.dequeue();
//		test.front();
//		test.enqueue(4);
//		test.showQueue();
//		test.front();
//		test.enqueue(2);
	}

}
