package uebung7_SS24;

import java.util.ArrayList;
import java.util.List;


interface ContentHandler<T>
{
	public void handle(T t);
}

//public class Node<T> implements ContentHandler<T>
//{
//	private T content;
//	private List<Node<T>> children;
//
//	public Node(T t)
//	{
//		content = t;
//		children = new ArrayList<Node<T>>();
//	}
//
//	public void add(Node<T> node)
//	{
//		children.add(node);
//	}
//
//	public T getContent()
//	{
//		return content;
//	}
//	
//	public void traverse(ContentHandler<T> contentHandler) {
//		contentHandler.handle(content);
//		for(Node<T> child : children) {
//			child.traverse(child);
//		}
//	}
//
//	@Override
//	public void handle(T t)
//	{
//		System.out.print(getContent());
//	}
//	
//	
//	public static void main(String[] args) {
//		Node<String> root = new Node<String>("root\n");
//		Node<String> root_child1 = new Node<String>("/");
//		Node<String> root_child2 = new Node<String>("   \\\n");
//		Node<String> root_child2_child1 = new Node<String>("    /");
//		Node<String> root_child2_child2 = new Node<String>("\\");
//		
//		root.add(root_child1);
//		root.add(root_child2);
//		root_child2.add(root_child2_child1);
//		root_child2.add(root_child2_child2);
//		
//		root.traverse(root);
//	}
//}


// b)
public class Node<T extends A> implements ContentHandler<T>
{
	private T content;
	private List<Node<T>> children;

	public Node(T t)
	{
		content = t;
		children = new ArrayList<Node<T>>();
	}

	public void add(Node<T> node)
	{
		children.add(node);
	}

	public T getContent()
	{
		return content;
	}
	
	public void traverse(ContentHandler<? super T> contentHandler) {
		contentHandler.handle(content);
		for(Node<T> child : children) {
			child.traverse(child);
		}
	}

	@Override
	public void handle(T t)
	{
		System.out.print(getContent());
	}
	
	
	public static void main(String[] args) {
		Node<B> root = new Node<B>(new B());
		Node<B> root_child1 = new Node<B>(new B());
		Node<B> root_child2 = new Node<B>(new B());
		Node<B> root_child2_child1 = new Node<B>(new B());
		Node<B> root_child2_child2 = new Node<B>(new B());
		
		root.add(root_child1);
		root.add(root_child2);
		root_child2.add(root_child2_child1);
		root_child2.add(root_child2_child2);
		
		root.traverse(root);
	}
}


class A{}
	class B extends A{}
		class D extends B{}
		class E extends B{}
	class C extends A{}