import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private class DuqueIterator implements Iterator<Item> {

		private Node<Item> currentNode = headNode;

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item ansItem = currentNode.value;
			currentNode = currentNode.next;
			return ansItem;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private class Node<Item> {
		public Item value;
		public Node<Item> next;

		public Node() {
		}

		public Node(Item value, Node<Item> next) {
			this.value = value;
			this.next = next;
		}
	}

	private Node<Item> headNode;
	private Node<Item> tailNode;
	private int size;

	public Deque() {
		headNode = null;
		tailNode = null;
		size = 0;
	}

	// verify the argument is null or not
	private void valid(Item value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
	}

	public boolean isEmpty() {
		return headNode == null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		valid(item);

		if (headNode == null) {
			headNode = new Node<Item>(item, null);
			tailNode = headNode;
		} else {
			Node<Item> tmpNode = new Node<Item>(item, headNode);
			headNode = tmpNode;
		}
		size++;
	}

	public void addLast(Item item) {
		valid(item);

		if (headNode == null) {
			headNode = new Node<>(item, null);
			tailNode = headNode;
		} else {
			tailNode.next = new Node<>(item, null);
			tailNode = tailNode.next;
		}
		size++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item ansItem = headNode.value;
		if (size == 1) {
			headNode = null;
			tailNode = null;
		} else {
			headNode = headNode.next;
		}
		size--;
		return ansItem;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> node = headNode;
		Item ansItem = null;
		if (size == 1) {
			headNode = null;
			tailNode = null;
			ansItem = node.value;
		} else {
			while (node.next != tailNode) {
				node = node.next;
			}
			ansItem = tailNode.value;
			node.next = null;
			tailNode = node;
		}
		size--;
		return ansItem;
	}

	public Iterator<Item> iterator() {
		return new DuqueIterator();
	}

	public static void main(String[] args) {
		Deque<Integer> queue = new Deque<>();
		queue.addFirst(1);
		queue.isEmpty();
		queue.removeLast();
		queue.addFirst(4);
		queue.removeLast();
		for (int s : queue) {
			System.out.println(s);
		}
	}
}
