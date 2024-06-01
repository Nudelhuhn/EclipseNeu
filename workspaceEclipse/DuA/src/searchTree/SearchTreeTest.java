package searchTree;

public class SearchTreeTest {

	public static void main(String[] args) {
		SearchTree st = new SearchTree();
		st.insert(2);
		st.insert(10);
		st.insert(-6);
		st.insert(4);
		st.insert(46);
		System.out.println(st.countNodes());
		st.insert(0);
		st.insert(2);
		st.insert(5);
		st.insert(-5);
		st.insert(4);
		st.insert(7);
		st.insert(11);
		st.insert(1);
		System.out.println(st.countNodes());

		System.out.println("\n preorder:");
		System.out.println("<" + st.preorder() + ">");

		System.out.println(" inorder:");
		System.out.println("<" + st.inorder() + ">");

		System.out.println(" postorder:");
		System.out.println("<" + st.postorder() + ">");

		System.out.println(" breitendurchlauf:");
		System.out.println("<" + st.breitendurchlauf() + ">");

		st.delete(0);
		st.delete(8);
		st.delete(2);
		st.delete(4);
		st.delete(11);

		System.out.println("\n preorder:");
		System.out.println("<" + st.preorder() + ">");

		System.out.println(" inorder:");
		System.out.println("<" + st.inorder() + ">");

		System.out.println(" postorder:");
		System.out.println("<" + st.postorder() + ">");

		System.out.println(" breitendurchlauf:");
		System.out.println("<" + st.breitendurchlauf() + ">");

	}

}
