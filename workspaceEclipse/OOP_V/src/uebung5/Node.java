package uebung5;

import java.util.*;

public class Node
{
	private int number;
	private List<Node> children;

	public Node()
	{
		children = new ArrayList<Node>();
	}

	public void addChild(Node node)
	{
		children.add(node);
	}

	public void setRandomNumber()
	{
		number = (int) (Math.random() * 201) - 100;
		for (Node child : children)
		{
			child.setRandomNumber();
		}
	}

	// a)
	public int getMin()
	{
		int min = number;
		for (Node child : children)
		{
			if (child.getMin() < min)
			{
				min = child.getMin();
			}
		}
		return min;
	}

	// b)
	public int countNodes()
	{
		int sumOfNodes = number;
		for (Node child : children)
		{
			sumOfNodes += child.countNodes();
		}
		return sumOfNodes;
	}

	// c)
	public int countLeaves()
	{
		int sumOfLeaves = 0;
		if (children.isEmpty())
		{
			sumOfLeaves = number;
		}
		for (Node child : children)
		{
			sumOfLeaves += child.countLeaves();
		}
		return sumOfLeaves;
	}

	// d)
	public int countNumber(int number)
	{
		int counter = 0;
		if (this.number == number)
		{
			counter++;
		}
		for (Node child : children)
		{
			counter += child.countNumber(number);
		}
		return counter;
	}

	// e)
	public boolean existsNumber(int number)
	{
		boolean checkForNumber = false;
		if (this.number == number)
		{
			checkForNumber = true;
		}
		if (!checkForNumber)
		{
			for (Node child : children)
			{
				checkForNumber = child.existsNumber(number);
			}
		}
		return checkForNumber;
	}

	// f)
	public int getMinIt()
	{
		int min = this.number;
		Stack<Node> stack = new Stack<>();
		stack.push(this);

		while (!stack.isEmpty())
		{
			Node current = stack.pop();
			if (current.number < min)
			{
				min = current.number;
			}
			for (Node child : current.children)
			{
				stack.push(child);
			}
		}
		return min;
	}

	public boolean existsNumberIt(int number)
	{
		Stack<Node> stack = new Stack<>();
		stack.push(this);

		while (!stack.isEmpty())
		{
			Node current = stack.pop();
			if (current.number == number)
			{
				return true;
			}
			for (Node child : current.children)
			{
				stack.push(child);
			}
		}
		return false;
	}

	public void print()
	{
		print(0); // Beginn mit Einrückungstiefe 0
	}

	private void print(int depth)
	{
		if (depth == 0)
		{
			System.out.println(number);
		} else
		{
			System.out.println("| ".repeat(depth - 1) + "|.." + number);
		}
		for (Node child : children)
		{
			child.print(depth + 1);
		}
	}

	public static void main(String[] args)
	{
		Node root = new Node();
		for (int i = 0; i < 2; i++)
		{
			Node gen1 = new Node();
			root.addChild(gen1);
			for (int j = 0; j < 2; j++)
			{
				Node gen2 = new Node();
				gen1.addChild(gen2);
				for (int k = 0; k < 2; k++)
				{
					Node gen3 = new Node();
					gen2.addChild(gen3);
				}
			}
		}
		root.setRandomNumber();
		root.print();
		System.out.println(root.getMin());
		System.out.println(root.countNodes());
		System.out.println(root.countLeaves());
		System.out.println(root.countNumber(7));
		System.out.println(root.existsNumber(12));
		System.out.println(root.getMinIt());
	}
}
