import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

		

		System.out.println("\nBuilding Huffman Tree based on JC's Image.");
		HuffmanTree<Byte> imageHuffmanTree = null;
		Byte[] imageBytes = null;
		byte[] imageBytes1 = null;
		try {
			File file = new File("compressed.huff");
			InputStream is = new FileInputStream(file);
			imageBytes1 = getBytesFromInputStream(is);
			imageBytes = new Byte[imageBytes1.length];
			int i = 0;
			for (byte b : imageBytes1) {
				imageBytes[i++] = b;
			}
			imageHuffmanTree = new HuffmanTree<Byte>(imageBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Leaves = " + imageHuffmanTree.leaves);
		imageHuffmanTree.print();
		HuffmanCompressor compressor = new HuffmanCompressor();
		byte[] decompressedImageBytes = compressor.decompress(imageHuffmanTree, 54679, imageBytes);
		//This currenthly throws an exception because I need to finish decompression.
		System.out.println("Decompressed file size = " + decompressedImageBytes.length + " bytes");
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
