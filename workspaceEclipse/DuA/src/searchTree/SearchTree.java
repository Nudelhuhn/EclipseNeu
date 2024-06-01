package searchTree;

//Binärer Suchbaum, der aus einzelnen Node-Objekten aufgebaut ist.
//Jeder Wert kann im Baum nur einmal vorkommen.
public class SearchTree {

	private Node root; // Referenz auf die Wurzel des Baumes

	// Konstruktor zum Erzeugen des leeren Baumes
	public SearchTree() {
		root = null;
	}

	// Methode zum Einfügen eines Nodes in den Baum
	public void insert(int value) {
		Node elem = root;
		Node toInsert = new Node(value);
		if (root == null) {
			root = toInsert;
		} else {
			if (!member(value)) {
				while (!member(value)) {
					if (elem.data > value) {
						if (elem.left == null)
							elem.left = toInsert;
						else
							elem = elem.left;
					} else {
						if (elem.right == null)
							elem.right = toInsert;
						else
							elem = elem.right;
					}
				}
			}
		}
	}

	// Löscht Knoten mit dem übergebenen Wert aus dem Baum, falls ein
	// solcher vorhanden ist.
	public void delete(int value) {
		Node toDelete = find(value);

		// Node nicht gefunden
		if (toDelete == null) {
			return;
		} else {
			// Wenn zu löschender Knoten nichtleere linke und rechte
			// Teilbäume hat, wird der zu löschende Knoten durch das
			// kleinste Node des rechten Teilbaumes ersetzt
			if (toDelete.left != null && toDelete.right != null) {
				SearchTree temp = new SearchTree();
				temp.root = toDelete;
				Node minRightTree = temp.rightTree().getMin();

				toDelete.data = minRightTree.data;
				if (toDelete.right == minRightTree) {
					toDelete.right = minRightTree.right;
				} else {
					temp.rightTree().delete(minRightTree.data);
				}
			} else {
				Node parent = findParent(toDelete);

				// Zu löschender Knoten ist ein Blatt
				if (toDelete.left == null && toDelete.right == null) {
					// Zu löschendes Node ist die Wurzel
					if (parent == null)
						root = null;
					else if (parent.left == toDelete)
						parent.left = null;
					else
						parent.right = null;
				} else {
					// Zu löschender Knoten ist die Wurzel
					if (parent == null) {
						if (toDelete.left == null)
							root = toDelete.right;
						else
							root = toDelete.left;
					} else if (parent.left == toDelete) {
						if (toDelete.left == null)
							parent.left = toDelete.right;
						else
							parent.left = toDelete.left;
					} else // parent.right == toDelete
					{
						if (toDelete.left == null)
							parent.right = toDelete.right;
						else
							parent.right = toDelete.left;
					}
				}
			}
		}
	}

	// Hilfsmethode, nur aus delete() heraus benutzt
	// liefert den minimalen Knoten eines binären Suchbaums
	private Node getMin() {
		Node elem = root;
		if (elem.left != null) {
			while (elem.left != null) {
				elem.left = elem;
			}
			return elem;
		} else {
			return root;
		}
	}

	// Hilfsmethode, nur aus delete() heraus benutzt
	// liefert zu einem gegebenen Knoten child den Vater-Knoten
	private Node findParent(Node child) {
		if (root == child || root == null)
			return null;
		else if (root.left == child || root.right == child)
			return root;
		else {
			Node temp = leftTree().findParent(child);
			if (temp != null)
				return temp;
			else
				return rightTree().findParent(child);
		}
	}

	// Testet, ob der Baum leer ist.
	public boolean isEmpty() {
		return root == null;
	}

	// Sucht einen Knoten mit dem übergebenen Wert im Suchbaum. Wenn ein
	// solcher vorhanden ist, wird eine Referenz auf diesen Knoten
	// zurückgegeben, sonst null.
	public Node find(int value) {
		Node elem = root;
		if (elem == null)
			return null;
		else if (elem.data == value)
			return elem;
		else if (elem.data > value)
			return leftTree().find(value);
		else
			return rightTree().find(value);
	}

	// Prüft, ob ein Knoten mit dem übergebenen Wert im Baum vorhanden ist
	public boolean member(int value) {
		return (find(value) != null);
	}

	public SearchTree leftTree() {
		if (root == null) {
			System.out.println("Der leere Baum hat keinen linken Teilbaum!");
			return null;
		}
		SearchTree leftTree = new SearchTree();
		leftTree.root = this.root.left;
		return leftTree;
	}

	public SearchTree rightTree() {
		if (root == null) {
			System.out.println("Der leere Baum hat keinen rechten Teilbaum!");
			return null;
		}
		SearchTree rightTree = new SearchTree();
		rightTree.root = this.root.right;
		return rightTree;
	}

	// Gibt die preorder - Darstellung eines Baumes als String zurück
	public String preorder() {
		// root, root.left, root.right
		if (isEmpty())
			return "";
		else
			return (root.data + " " + leftTree().preorder() + rightTree().preorder());
	}

	// Gibt die inorder - Darstellung eines Baumes als String zurück
	public String inorder() {
		// root.left, root, root.right
		if (isEmpty())
			return "";
		else
			return (leftTree().inorder() + root.data + " " + rightTree().inorder());
	}

	// Gibt die postorder - Darstellung eines Baumes als String zurück
	public String postorder() {
		// root.left, root.right, root
		if (isEmpty())
			return "";
		else
			return (leftTree().postorder() + rightTree().postorder() + " " + root.data);
	}

	// Gibt die Werte der Knoten des Baumes im Breitendurchlauf
	// als String zurück
	public String breitendurchlauf() {
		if (isEmpty())
			return "";
		else {
			ElementQueue q = new ElementQueue();
			q.enqueue(root);
			String erg = "";
			while (!q.isEmpty()) {
				Node elem = q.dequeue();
				erg += elem.data + " ";
				if (elem.left != null)
					q.enqueue(elem.left);
				if (elem.right != null)
					q.enqueue(elem.right);
			}
			return erg;
		}
	}
	
	public int countNodesRecursive(Node node) {
	    if (node == null)
	        return 0;
	        return 1 + countNodesRecursive(node.left) + countNodesRecursive(node.right);
	}

	public int countNodes() {
	    return this.countNodesRecursive(root);
	}
}