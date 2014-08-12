
public class HuffmanCompressor {
	public byte[] compress(HuffmanTree tree, byte[] byteArray){
		return byteArray;
		
	}
	public byte[] decompress(HuffmanTree tree, int uncompressedLength, byte[] byteArray){
		return byteArray;
		
	}
	public String decompress(Byte[] bytes, Node node) {
		if (node.data != null) {
			// We found the letter. Return it.
			return "" + node.data;
		} else {
			if (bytes.length > 0) {
				for (int i = 0; i < bytes.length; i++) {
					if (bytes[i] != null) {
						if (bytes[i] == 0) {
							bytes[i] = null;
							return decompress(bytes, node.left);
						} else {
							bytes[i] = null;
							return decompress(bytes, node.left);
						}
					}
				}
			}
		}
		return "";
	}
}
