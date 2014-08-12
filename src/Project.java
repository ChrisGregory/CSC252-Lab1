import java.util.Random;

public class Project {
	/*
	 * MILESTONE 1: Create a class called HuffmanTree which takes a byte array
	 * of data in a constructor to build itself. The result should be a Huffman
	 * Tree that will be used in a future milestone to encode and decode
	 * messages. Implement some way of printing out the Huffman tree to the
	 * screen for pass-off reasons only. Pass off your Huffman tree to the
	 * instructor.
	 * 
	 * Questions I'm likely to ask: What is the performance of your algorithm?
	 * Consider the best case where the data is uniform (one character repeated
	 * over and over) and the worst case where each data element is different.
	 * What data does your Node class hold onto? How will you use this data to
	 * help you traverse it when encoding and decoding?
	 * 
	 * MILESTONE 2: Augment your HuffmanTree class with the following methods:
	 * 
	 * public byte toByte(Bits bits); // reads the next byte from a queue of
	 * bits by traversing the Huffman tree
	 * 
	 * public void fromByte(byte b, Bits bits); // writes the bits indicated by
	 * the Huffman tree for the given byte
	 * 
	 * These methods are simple, especially if you write them recursively. In
	 * fact, please write them recursively.
	 * 
	 * NOTE: Bits is a class of my creation. It is available in the attached
	 * jar.
	 * 
	 * Questions I will likely ask you: What debugging strategies did you use to
	 * confirm that your encoding is correct?
	 */
	public static void main(String[] args) {

		System.out.println("Tree 1. Integers.");
		Byte[] byteArray = new Byte[]{45,56,67,78,89,12,23,34,45,23,45,67,45};
		HuffmanTree<Byte> hTree = new HuffmanTree<Byte>(byteArray);
		hTree.print();
		
		System.out.println("Tree 2. Integers.");
		HuffmanTree<Integer> hTree2 = new HuffmanTree<Integer>();
		hTree2.insert(5);
		hTree2.insert(8);
		hTree2.insert(7);
		hTree2.insert(9);
		hTree2.insert(2);
		hTree2.insert(3);
		hTree2.insert(1);
		hTree2.print();

	}
}
