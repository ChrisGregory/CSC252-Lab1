import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import sun.misc.IOUtils;

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

		System.out.println("Building Huffman Tree:");
		Byte[] byteArray = new Byte[] { 45, 56, 67, 78, 89, 12, 23, 34, 45, 23,
				45, 67, 45 };
		HuffmanTree<Byte> hTree = new HuffmanTree<Byte>(byteArray);
		System.out.println();

		System.out.println("Printing Tree:");
		hTree.print();

		byte searchByte = (byte) 45;
		Bits bits = new Bits();
		hTree.fromByte(searchByte, bits);
		System.out.println("\nGetting " + searchByte + " from byte: "
				+ bits.toString());
		System.out.println("\nSending " + bits.toString()
				+ " to get the next byte: " + hTree.toByte(bits));
		
		System.out.println("\n Building a Huffman tree based on the image decoding frequencies.");
		//The following code builds a huffman tree based on the frequencies provided by the zipped lab page.
		HuffmanTree<Byte> imageHuffmanTree = null;
		Byte[] imageBytes = null;
		byte[] imagebytes = null;
		int[] frequenciesOfValues = {423, 116, 145, 136, 130, 165, 179, 197, 148, 125, 954, 156, 143, 145, 164, 241, 107, 149, 176, 153, 121, 164, 144, 166, 100, 138, 157, 140, 119, 138, 178, 289, 360, 120, 961, 195, 139, 147, 129, 192, 119, 146, 138, 184, 137, 196, 163, 331, 115, 160, 127, 172, 176, 181, 149, 194, 138, 154, 163, 167, 196, 174, 250, 354, 142, 169, 170, 209, 205, 179, 147, 245, 108, 179, 148, 186, 131, 160, 112, 219, 118, 204, 164, 154, 154, 175, 189, 239, 126, 145, 185, 179, 149, 167, 152, 244, 189, 257, 234, 208, 179, 170, 171, 178, 184, 189, 203, 184, 204, 208, 187, 163, 335, 326, 206, 189, 210, 204, 230, 202, 415, 240, 275, 295, 375, 308, 401, 608, 2099, 495, 374, 160, 130, 331, 107, 181, 117, 133, 476, 129, 137, 106, 107, 237, 184, 143, 122, 143, 1596, 205, 121, 170, 123, 124, 150, 132, 143, 133, 178, 308, 96, 102, 114, 176, 159, 149, 123, 199, 1156, 119, 144, 237, 131, 155, 143, 225, 92, 125, 117, 138, 135, 154, 124, 137, 121, 143, 149, 141, 177, 159, 247, 384, 302, 120, 95, 140, 87, 1460, 155, 199, 111, 198, 147, 182, 91, 148, 119, 233, 445, 1288, 138, 133, 122, 170, 156, 257, 143, 149, 180, 174, 132, 151, 193, 347, 91, 119, 135, 182, 124, 152, 109, 175, 152, 159, 166, 224, 126, 169, 145, 220, 119, 148, 133, 158, 144, 185, 139, 168, 244, 145, 167, 167, 262, 214, 293, 402};
		Queue<Node<Byte>> pQueue = new PriorityQueue<Node<Byte>>();
		for(int i = -128; i <= 127; i++){
			pQueue.add(new Node<Byte>((byte) i, frequenciesOfValues[i + 128]));
		}
		imageHuffmanTree = new HuffmanTree(pQueue);
		System.out.println("Leaves = " + imageHuffmanTree.leaves);
		imageHuffmanTree.print();
		
		//This gets the bytes from the compressed image
		try {
			File file = new File("compressed.huff");
			InputStream is = new FileInputStream(file);
			imagebytes = getBytesFromInputStream(is);
			imageBytes = new Byte[imagebytes.length];
			int i = 0;
			for (byte b : imagebytes) {
				imageBytes[i++] = b;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HuffmanCompressor compressor = new HuffmanCompressor();
		byte[] decompressedImageBytes = compressor.decompress(imageHuffmanTree, 54679, imageBytes);
		//This currenthly throws an exception because I need to finish decompression.
		
		FileOutputStream fos;
		try {
			String decompressedFilename = "decompressed.huff";
			fos = new FileOutputStream("decompressedFilename");
			fos.write(decompressedImageBytes);
			fos.close();
			System.out.println("Decompressed this file & saved as " + decompressedFilename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static byte[] getBytesFromInputStream(InputStream is) {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
			byte[] buffer = new byte[0xFFFF];

			for (int len; (len = is.read(buffer)) != -1;)
				os.write(buffer, 0, len);

			os.flush();

			return os.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}

}
