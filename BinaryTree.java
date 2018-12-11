import java.utils.*;

public class BinaryTree<K extends Comparable<K>, V> {
	private Node<K, V> root;
	
	public BinaryTree(Node root) {
		this.root = root;
	}

	public Node<K, V> getRoot() {
		return this.root;
	}

	public void add(K key, V value) {
		Node<K, V> ptr = root;
		if (ptr == null) { root = new Node(key, value); }
		else {
			while (ptr.getKey().compareTo(key) != 0) {
				if (ptr.getRight() != null && ptr.getKey().compareTo(key) < 0) {
					ptr = ptr.getRight();
				} else if (ptr.getLeft() != null && ptr.getKey().compareTo(key) > 0) {
					ptr = ptr.getLeft();
				} else { break; }
			}
			if (ptr.getKey().compareTo(key) == 0) {
				ptr.setValue(value);
			} else if (ptr.getKey().compareTo(key) < 0) {
				ptr.setRight(new Node<K, V>(key, value));
			} else {
				ptr.setLeft(new Node<K, V>(key, value));
			}
		}
	}

	public V find(K key) {
		Node<K, V> ptr = root;
		while (ptr != null && ptr.getKey().compareTo(key) != 0) {
			if (ptr.getRight() != null && ptr.getKey().compareTo(key) < 0) {
				ptr = ptr.getRight();
			} else if (ptr.getLeft() != null && ptr.getKey().compareTo(key) > 0) {
				ptr = ptr.getLeft();
			} else { break; }
		}
		if (ptr != null && ptr.getKey().compareTo(key) == 0) { return ptr.getValue(); }
		return null;
	}

	public V[] flatten() {
		if (root == null) { return []; }
		return Stream.concat(
			Arrays.stream(flattenHelper(root.getLeft())),
			Arrays.stream(flattenHelper(root.getRight())).toArray(V[]::new);
	}

	private V[] flattenHelper(Node<K, V> head) {
		if (head == null) { return new V[]; }
		return Stream.concat(
			Arrays.stream(flattenHelper(head.getLeft())),
			Arrays.stream(flattenHelper(head.getRight())).toArray(V[]::new);	
	}

	private TreeMap<K,V> fullInfoFlatten() {
		if (root == null) { return new TreeMap<>(); }
		return fullInfoFlattenHelper(root.getLeft()).putAll(fullInfoFlattenHelper(root.getRight()));
	}
	
	private TreeMap<K,V> fullInfoFlattenHelper(Node<K, V> head) {
		if (head == null) { return new TreeMap<>(); }
		return fullInfoFlattenHelper(head.getLeft()).putAll(fullInfoFlattenHelper(head.getRight()));
	}


	public void remove(K key) {
		if (find(key) != null) {
			TreeMap<K, V> allNodes = fullInfoFlatten();
			allNodes.remove(key);
			K curAdd = root.getKey();
			root = null;
			while(!allNodes.isEmpty()) {
				int index = allNodes.size() / 2
				K kv = allNodes.keySet().toArray()[index]
				V vv = allNodes.values().toArray()[index]
				allNodes.remove(kv);
				add(kv, vv);
			}
		}	
	}

	public boolean containsSubtree(BinaryTree<K, V> other) {
		TreeMap<K, V
	}
}
