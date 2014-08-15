import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class HuffmanCompressor {
	public Byte[] compress(HuffmanTree tree, Byte[] byteArray) {
		//For each byte in the Array, shove it through the tree and get it's new encoding.
		List<Byte> newByteList = new ArrayList<Byte>();
		Bits bits = new Bits();
		
		for (byte b : byteArray) {
			tree.fromByte(b, bits);
		}
		boolean buildingBytes = true;
		while (buildingBytes) {
			byte nextByte = (byte) Integer.parseInt("00000000", 2);
			for (int i = 0; i < 8; i++) {
				if (bits.poll()) {
					nextByte = (byte) (nextByte | (1 << i));
				} else {
					nextByte = (byte) (nextByte & ~(1 << i));
				}
			}
			newByteList.add(nextByte);
		}
		Byte[] result = (Byte[]) newByteList.toArray();
		return result;
	}

	public byte[] decompress(HuffmanTree tree, int uncompressedLength,
			Byte[] byteArray) {
		System.out.println("Decompressing Byte[].");
		System.out.println("Compressed Message ByteCount: " + byteArray.length);
		// For each byte,
		// Get each byte's value from the huffman tree.
		// Compose the new values.

		List<Byte> newByteList = new ArrayList<Byte>();
		Bits bits = new Bits();
		//Add each byte to bits.
		for (Byte b : byteArray) {
			boolean[] bitsInByte = bits(b);
			b = Byte.valueOf(Integer.toString(b.intValue()));
			for (int i = 0; i < 8; i++) {
				bits.add(bitsInByte[i]);
			}
		}
		System.out.println("ByteCount = " + bits.size() / 8); //This is 52389 with JC's thing.
		//Get the codes using bits.
		while(bits.size() > 0){
			newByteList.add(tree.toByte(bits));
		}
		System.out.println("ByteCount = " + newByteList.size()); //This is 55035
		
		// Bits now represents byteArray after huffman decoding.
		System.out.println("NewByteListBuilt.");
		System.out.println("Transferring from Bytes to bytes.");
		byte[] resultbytes = new byte[newByteList.size()];
		int i = 0;
		for (Byte b : newByteList) {
			resultbytes[i++] = b;
		}
		System.out.println("Returning decompressed Byte Array.");
		System.out.println("Decompressed ByteCount: " + resultbytes.length);
		return resultbytes;
	}
	
	static boolean[] bits(byte b) {
		  int n = 8;
		  final boolean[] set = new boolean[n];
		  while (--n > 0) {
		    set[n] = (b & 0x80) == 0;
		    b <<= 1;
		  }
		  return set;
		}
}