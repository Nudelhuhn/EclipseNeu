package searchTree;

//Einzelner Knoten (Node) eines Baums (Trees)
class Node {

	public int data; // Der int-Wert (Schluessel), der die Daten des Knotens repräsentiert
	public Node left; // Referenz auf linken Sohn
	public Node right; // Referenz auf rechten Sohn

	// Konstruktor, dem der Schluesselwert des Knotens übergeben wird.
	public Node(int value) {
		data = value;
	}
}
