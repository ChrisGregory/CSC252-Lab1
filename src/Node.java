public class Node<T extends Comparable<T>> implements Comparable {
	public final T data;
	public Node left;
	public Node right;
	public Node parent; // This is only really neccesary because I wanted a
						// simple "Node.level()" method.
						// After additional research, this is also neccesary for
						// removal. So it's in forever now.
	public int weight;// huffman nodes all appear to have a weight value. Look
						// into this.

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	public Node(T data, Node parent) {
		this.data = data;
		this.parent = parent;
	}

	public Node(T data, int weight, Node parent) {
		this.data = data;
		this.weight = weight;
		this.parent = parent;
	}

	public Node(Node<T> node1, Node<T> node2) {
		
			this.data = null;
			this.parent = null;
			if(node1.data == null || node2.data == null){
				this.left = node1.compareTo(node2) < 0 ? node1 : node2;
				this.right = node1.compareTo(node2) > 0 ? node1 : node2;
			} else {
				this.left = node1.data.compareTo(node2.data) < 0 ? node1 : node2;
				this.right = node1.data.compareTo(node2.data) > 0 ? node1 : node2;
			}
			this.left.parent = this;
			this.right.parent = this;
			this.weight = node1.weight + node2.weight;
	}

	public int balanceFactor() {
		int lengthOfLeftSide = depth(left);
		int lengthOfRightSide = depth(right);
		return lengthOfLeftSide - lengthOfRightSide;
	}

	public int depth() {
		return depth(this);
	}

	public int level() {
		return parent == null ? 0 : parent.level() + 1;
	}

	private int depth(Node node) {
		int leftDepth = 0;
		int rightDepth = 0;
		if (left != null) {
			leftDepth = left.depth();
		}
		if (right != null) {
			rightDepth = right.depth();
		}
		return Math.max(leftDepth, rightDepth) + 1;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

	@Override
	public int compareTo(Object arg0) {
		Node otherNode = (Node) arg0;
		int result = weight - otherNode.weight;
		return result;
	}
}
