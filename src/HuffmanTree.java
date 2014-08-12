import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

//I really wanted these classes to be Generic. I don't know why. I just like it when things are useful in as many applications as possible.
public class HuffmanTree<T extends Comparable<T>> extends BinaryTree<T> {

	public HuffmanTree(Byte[] byteArray) {
		//Populate a hashmap with bytes from byteArray.
		//Duplicates get higher frequency values.
		HashMap<Byte, Integer> values = new HashMap<Byte, Integer>();
		for (byte b : byteArray) {
			if (values.containsKey(b)) {
				values.put(b, values.get(b) + 1);
			} else {
				values.put(b, 1);
			}
		}
		
		// Make a priorityQueue and add everything to it.
		Queue<Node<Byte>> pQueue = new PriorityQueue<Node<Byte>>();
		for (Byte b : values.keySet()) {
			pQueue.add(new Node<Byte>(b, values.get(b)));
		}

		// Make a bunch of empty hybrid nodes out of every other node.
		while (pQueue.size() > 1) {
			pQueue.offer(new Node<Byte>(pQueue.poll(), pQueue.poll()));
		}

		// Last node standing becomes root, if empty, just set it to null.
		root = (pQueue.size() > 0) ? pQueue.poll() : null;
	}

	public HuffmanTree() {
		// TODO Auto-generated constructor stub
	}
	
	private Node getNode(byte b) {
		return root;		
	}
	
	// reads the next byte from a queue of bits by traversing the Huffman tree
	public byte toByte(Bits bits){
		return toByteHelper(bits, root);	
	}
	public byte toByteHelper(Bits bits, Node currentNode){
		//Determine if currentNode has data.
		if(currentNode.data != null){
			//If it does, return that data. Yay.
			return (byte) currentNode.data;
		} else {
			//Otherwise, we gotta go deeper.
			return bits.poll() ? toByteHelper(bits, currentNode.right) : toByteHelper(bits, currentNode.left);
			//Damn I love Ternaries.
		}
	}
	 
	// writes the bits indicated by the Huffman tree for the given byte
	// writes? You mean like prints? Why not return a string or a "Bits"?
	public void fromByte(byte b, Bits bits){
		System.out.println(fromByteHelper(getNode(b)));
	}

	public String fromByteHelper(Node currentNode){
		return currentNode.parent == null ? "" : fromByteHelper(currentNode.parent) + (currentNode.parent.left == currentNode ? "0" : "1" );
	}
}
