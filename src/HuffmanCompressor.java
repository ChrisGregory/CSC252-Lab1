import java.util.ArrayList;
import java.util.List;

public class HuffmanCompressor {
	public Byte[] compress(HuffmanTree tree, Byte[] byteArray) {
//		tree = new HuffmanTree(byteArray);
//		List<Byte> newByteList = new ArrayList<Byte>();
//		Bits bits = new Bits();
//		for (byte b : byteArray) {
//			tree.fromByte(b, bits);
//		}
//		boolean buildingBytes = true;
//		while (buildingBytes) {
//			byte nextByte = (byte) Integer.parseInt("00000000", 2);
//			for (int i = 0; i < 8; i++) {
//				if (bits.poll()) {
//					nextByte = (byte) (nextByte | (1 << i));
//				} else {
//					nextByte = (byte) (nextByte & ~(1 << i));
//				}
//			}
//			newByteList.add(nextByte);
//		}
//		Byte[] result = (Byte[]) newByteList.toArray();
//		return result;
		return byteArray;
	}

	public byte[] decompress(HuffmanTree tree, int uncompressedLength, Byte[] b) {
		//Okay. We start with a byte array that represents a compressed thing.
		Byte[] byteArray = b;
		
		//We also have a list of things
		int uncompressedSize = uncompressedLength;
		
		//And this tree. Is this the tree the tree of the uncompressed thing? I'm not sure.
		HuffmanTree huffmanTree = tree;
		
		
		return null;
		
		
//		//OKAY Temporarily forget everything below here:
//		List<Byte> newByteList = new ArrayList<Byte>();
//		Bits bits = new Bits();
//		for (byte b : byteArray) {
//			// HOW DO I ADD THESE BYTES TO "bits"?
//		}
//		while (bits.size() > 0) {
//			Byte newByte = tree.toByte(bits);
//			newByteList.add(newByte);
//		}
//		Byte[] result = new Byte[newByteList.size()];
//		result = newByteList.toArray(result);
//		byte[] resultbytes = new byte[result.length];
//		int i = 0;
//		for (Byte b : result) {
//			resultbytes[i++] = b;
//		}
//		return resultbytes;
	}

}