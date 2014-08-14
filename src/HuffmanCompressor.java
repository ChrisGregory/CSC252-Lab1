import java.util.ArrayList;
import java.util.List;

public class HuffmanCompressor {
	public Byte[] compress(HuffmanTree tree, Byte[] byteArray) {
		tree = new HuffmanTree(byteArray);
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

	public byte[] decompress(HuffmanTree tree, int uncompressedLength, Byte[] byteArray) {
		//OKAY Temporarily forget everything below here:
		List<Byte> newByteList = new ArrayList<Byte>();
		Bits bits = new Bits();
		for (Byte b : byteArray) {
			String s = b.toString();
			int i = Integer.parseInt(s, 2);//HOW DO I HANDLE NEGATIVE NUMBERS?
			System.out.println();
		}
		while (bits.size() > 0) {
			Byte newByte = tree.toByte(bits);
			newByteList.add(newByte);
		}
		Byte[] result = new Byte[newByteList.size()];
		result = newByteList.toArray(result);
		byte[] resultbytes = new byte[result.length];
		int i = 0;
		for (Byte b : result) {
			resultbytes[i++] = b;
		}
		return resultbytes;
	}

}