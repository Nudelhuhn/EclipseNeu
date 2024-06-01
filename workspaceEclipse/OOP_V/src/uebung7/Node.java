package uebung7;

import java.util.ArrayList;
import java.util.List;

class Node<T>
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

	//a)
	public void traverse(ContentHandler<T> handler) {
        handler.handle(content);
        for (Node<T> child : children) {
            child.traverse(handler);
        }
    }
	
	//b)
	/*
	 * Super macht den Objekttyp des nodes beliebig, wenn es nur T wäre, dann
	 * müsste für den Contenthandler jedes mal der genaue Typ angegeben werden.
	 * 
	 * In b) können ja nach Aufgabenstellung B oder dessen Unterklassen als Objekte
	 * übergeben werden, deswegen muss es variabel sein
	 */
	public void traverse1(ContentHandler<? super T> handler) {
        handler.handle(content);
        for (Node<T> child : children) {
            child.traverse1(handler);
        }
    }
}

interface ContentHandler<T>{
	public void handle(T t);
}
