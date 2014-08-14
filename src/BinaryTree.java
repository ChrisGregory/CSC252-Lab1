import java.util.ArrayList;
import java.util.List;

public abstract class BinaryTree<T extends Comparable<T>> {
	Node root = null;
	int levels = 0;
	int count = 0;

	public boolean insert(T data){
		boolean result = false;
		if (root != null) {
			result = insert(root, data);
		} else {
			root = new Node<T>(data);
			result = true;
		}
		if (result == true) {
			count++;
		}
		return result;
	}
	
	private boolean insert(Node rootNode, T data) {
		boolean result = false;
		if (data.compareTo((T) rootNode.data) < 0) { // Had to switch this from
														// "<" to compareTo()
														// now that I'm using
														// generics.
			if (rootNode.left != null) {
				result = insert(rootNode.left, data);
			} else {
				rootNode.left = new Node<T>(data, rootNode);
				result = true;
			}
		} else if (data.compareTo((T) rootNode.data) > 0) { // Same here.
			if (rootNode.right != null) {
				result = insert(rootNode.right, data);
			} else {
				rootNode.right = new Node<T>(data, rootNode);
				result = true;
			}
		}
		return result;
	}

	public boolean delete(T data){
		boolean result = false;
		/**
		 * Let node X be the node with the value we need to delete, and let node
		 * Y be a node in the tree we need to find to take node X's place, and
		 * let node Z be the actual node we take out of the tree.
		 * 
		 * Steps to consider when deleting a node in an AVL tree are the
		 * following: 1: If node X is a leaf or has only one child, skip to step
		 * 5. (node Z will be node X) 2: Otherwise, determine node Y by finding
		 * the largest node in node X's left sub tree (in-order predecessor) or
		 * the smallest in its right sub tree (in-order successor). 3: Replace
		 * node X with node Y (remember, tree structure doesn't change here,
		 * only the values). In this step, node X is essentially deleted when
		 * its internal values were overwritten with node Y's. 4: Choose node Z
		 * to be the old node Y. 5: Attach node Z's subtree to its parent (if it
		 * has a subtree). If node Z's parent is null, update root. (node Z is
		 * currently root) 6: Delete node Z. 7: Retrace the path back up the
		 * tree (starting with node Z's parent) to the root, adjusting the
		 * balance factors as needed.
		 */
		return result;
	}

	public void print() {
		String nullString = "null";
		if (root != null) {
			int treeDepth = root.depth();
			List[] levels = new List[treeDepth];
			for(int i = 0; i < levels.length; i++){
				levels[i] = new ArrayList<Node>();
			}
			levels = getLevelsList(levels);
			
			//Actually print everything.
			for(int i = 0; i < levels.length; i++){
				System.out.print("Lv" + i + ": ");
				for(int j = 0; j < levels[i].size(); j++)
				{
					Node node = (Node)levels[i].get(j);		
					System.out.print(node.data + "["+node.weight+"]|P=" + (node.parent == null ? "NULL" : node.parent.data +"["+node.parent.weight+"]") + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("There is no data in this tree. Root is null.");
		}
	}
	public void printInOrder() {
		printInOrder(root);
	}
	public void printInOrder(Node currentNode) {
		if (currentNode.left != null) {
			printInOrder(currentNode.left);
		}
		System.out.print(currentNode.data + "["+currentNode.weight+"]|P=" + (currentNode.parent == null ? "NULL" : currentNode.parent.data+"["+currentNode.parent.weight+"]")+ " ");
		if (currentNode.right != null) {
			printInOrder(currentNode.right);
		}
	}

	private boolean rightRotation(Node<T> rotationalRoot) {
		boolean result = false;
		Node<T> pivot = rotationalRoot.left;
		rotationalRoot.left = pivot.right;
		pivot.right.parent = rotationalRoot;
		pivot.right = rotationalRoot;
		rotationalRoot.parent = pivot;
		result = true;
		return result;
	}

	private boolean leftRotation(Node<T> rotationalRoot) {
		boolean result = false;
		Node<T> pivot = rotationalRoot.right;
		rotationalRoot.right = pivot.left;
		pivot.left.parent = rotationalRoot;
		pivot.left = rotationalRoot;
		rotationalRoot.parent = pivot;
		result = true;
		return result;
	}

	
	private List[] getLevelsList(List[] levels) {
		return getLevelsList(levels, root);
	}
	
	//Recurrence Relation: T(n) = 1 + 2(T(n/2ish)), T(1) = 1;
	private List[] getLevelsList(List[] levels, Node node) {
		levels[node.level()].add(node);
		if (node.left != null) {
			levels = getLevelsList(levels, node.left);
		}
		if (node.right != null) {
			levels = getLevelsList(levels, node.right);
		}
		return levels;
	}

	public List<T> preOrderTraversal(Node node) {
		return preOrderTraversal(node, new ArrayList<T>());
	}

	//Recurrence Relation: T(n) = 1 + 2(T(n/2ish)), T(1) = 1;
	private List<T> preOrderTraversal(Node node, List<T> list) {
		list.add((T) node.data);
		if (node.left != null) {
			list.addAll(preOrderTraversal(node.left, list));
		}
		if (node.right != null) {
			list.addAll(preOrderTraversal(node.right, list));
		}
		return list;
	}

	public List<T> inOrderTraversal(Node node) {
		return inOrderTraversal(node, new ArrayList<T>());
	}

	//Recurrence Relation: T(n) = 1 + 2(T(n/2ish)), T(1) = 1;
	private List<T> inOrderTraversal(Node node, List<T> list) {
		if (node.left != null) {
			list.addAll(inOrderTraversal(node.left, list));
		}
		list.add((T) node.data);
		if (node.right != null) {
			list.addAll(inOrderTraversal(node.right, list));
		}
		return list;
	}

	public List<T> postOrderTraversal(Node node) {
		return postOrderTraversal(node, new ArrayList<T>());
	}

	//Recurrence Relation: T(n) = 1 + 2(T(n/2ish)), T(1) = 1;
	private List<T> postOrderTraversal(Node node, List<T> list) {
		if (node.left != null) {
			list.addAll(postOrderTraversal(node.left, list));
		}
		if (node.right != null) {
			list.addAll(postOrderTraversal(node.right, list));
		}
		list.add((T) node.data);
		return list;
	}
}
