import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

//I really wanted these classes to be Generic. I don't know why. I just like it when things are useful in as many applications as possible.
public class HuffmanTree<T extends Comparable<T>> extends BinaryTree<T> {
	int leaves = 0;
	public HuffmanTree(Byte[] byteArray) {
		// Populate a hashmap with bytes from byteArray.
		// Duplicates get higher frequency values.
		HashMap<Byte, Integer> values = new HashMap<Byte, Integer>();
		for (Byte b : byteArray) {
			if (values.containsKey(b)) {
				values.put(b, values.get(b) + 1);
				//System.out.println("Adding " + b + ". " + b + "'s frequency: " + values.get(b));
			} else {
				values.put(b, 1);
				//System.out.println("Adding " + b + ". " + b + "'s frequency: " + values.get(b));
			}
		}
		// Make a priorityQueue and add everything to it.
		Queue<Node<Byte>> pQueue = new PriorityQueue<Node<Byte>>();
		for (Byte b : values.keySet()) {
			//System.out.println("Adding " + b + " to PriorityQueue. Freq: " + values.get(b));
			pQueue.add(new Node<Byte>(b, values.get(b)));
			leaves++;
		}

		// Make a bunch of empty hybrid nodes out of every other node.
		while (pQueue.size() > 1) {
			//System.out.println("pQueue.size() = " + pQueue.size());
			Node value1 = pQueue.poll();
			Node value2 = pQueue.poll();
			//System.out.println("Combining " + value1.data + "["+value1.weight+"] with " + value2.data + "["+value2.weight+"]");
			Node hybrid = new Node<Byte>(value1, value2);
			pQueue.offer(hybrid);
			//System.out.println("Added Hybrid: " + hybrid.data + "["+hybrid.weight+"]");
			//System.out.println("Children: left:"+hybrid.left.data+" right:"+hybrid.right.data);
		}

		// Last node standing becomes root, if empty, just set it to null.
		root = (pQueue.size() > 0) ? pQueue.poll() : null;
		//printInOrder();
	}

	public HuffmanTree() {
		// TODO Auto-generated constructor stub
	}
	
	public HuffmanTree(Queue<Node<Byte>> pQueue) {

	// Make a bunch of empty hybrid nodes out of every other node.
	while (pQueue.size() > 1) {
		//System.out.println("pQueue.size() = " + pQueue.size());
		Node value1 = pQueue.poll();
		Node value2 = pQueue.poll();
		//System.out.println("Combining " + value1.data + "["+value1.weight+"] with " + value2.data + "["+value2.weight+"]");
		Node hybrid = new Node<Byte>(value1, value2);
		pQueue.offer(hybrid);
		//System.out.println("Added Hybrid: " + hybrid.data + "["+hybrid.weight+"]");
		//System.out.println("Children: left:"+hybrid.left.data+" right:"+hybrid.right.data);
	}

	// Last node standing becomes root, if empty, just set it to null.
	root = (pQueue.size() > 0) ? pQueue.poll() : null;
	}
	
	

	private Node getNode(byte b) {
		Node result = getNode(b, root);
		return result;
	}

	private Node getNode(byte b, Node currentNode) {
		if (currentNode != null) {
			if (currentNode.data != null) {
				if ((byte) currentNode.data == b) {
					return currentNode;
				}
			}
			Node left = getNode(b, currentNode.left);
			Node right = getNode(b, currentNode.right);
			if (left != null) {
				if (left.data != null) {
					if ((byte) left.data == b) {
						return left;
					}
				}
			} else if (right != null) {
				if (right.data != null) {
					if ((byte) right.data == b) {
						return right;
					}
				}
			} else {
				return null;
			}

		} else {
			return null;
		}
		// This shouldnt be called. it was mad at me.
		return null;
	}

	// reads the next byte from a queue of bits by traversing the Huffman tree
	public byte toByte(Bits bits) {
		return toByteHelper(bits, root);
	}

	public Byte toByteHelper(Bits bits, Node currentNode) {
		if(currentNode == null){
			//The byte we're looking for isn't in the tree. Return null.
			return (Byte) null;
		}
		// Determine if currentNode has data.
		if (currentNode.data != null) {
			// If it does, return that data. Yay.
			return (Byte) currentNode.data;
		} else {
			// Otherwise, we gotta go deeper.
			return bits.poll() ? toByteHelper(bits, currentNode.right) : toByteHelper(bits, currentNode.left);
		}
	}

	// writes the bits indicated by the Huffman tree for the given byte
	// writes? You mean like prints? Why not return a string or a "Bits"?
	public void fromByte(Byte b, Bits bits) {
		Node thisBytesNode = getNode(b);
		Bits thisBytesBits = new Bits();
		fromByteHelper(thisBytesNode, thisBytesBits);
		bits.addAll(thisBytesBits);
	}

	public void fromByteHelper(Node currentNode, Bits bits) {
		if (currentNode != null) {
			if (currentNode.parent != null) {
				if (currentNode.parent.right != null) {
					bits.offerFirst(currentNode.parent.right == currentNode);
				} else if (currentNode.parent.left != null) {
					bits.offerFirst(currentNode.parent.left != currentNode);
				}
				fromByteHelper(currentNode.parent, bits);
			}
		}
	}
}
