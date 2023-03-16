import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private class StackIterator implements Iterator<Item>{
		
		private Node<Item> currentNode = headNode;
		
		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public Item next() {
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
		public Node(){}
		public Node(Item value, Node<Item> next){
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
		if(value == null) {
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
		
		if(headNode == null) {
			headNode = new Node<Item>(item, null);
			tailNode = headNode;
		}else {
			Node<Item> tmpNode = new Node<Item>(item, headNode);
			headNode = tmpNode;
		}
		size++;
	}

	public void addLast(Item item) {
		valid(item);
		
		if(headNode == null) {
			headNode = new Node<>(item, null);
			tailNode = headNode;
		}else {
			tailNode.next = new Node<>(item, null);
			tailNode = tailNode.next;
		}
		size++;
	}

	public Item removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> ansNode = headNode;
		if(size == 1) {
			headNode = null;
			tailNode = null;
			return ansNode.value;
		}
		headNode = headNode.next;
		size--;
		
		return ansNode.value;
	}

	public Item removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> node = headNode;
		if(size == 1) {
			headNode = null;
			tailNode = null;
			return node.value;
		}
		while(node.next != tailNode) {
			node = node.next;
		}
		Item ansItem = tailNode.value;
		tailNode = node;
		size--;
		return ansItem;
	}

	public Iterator<Item> iterator() {
		return new StackIterator();
	}
	
	public static void main(String[] args) {
		Deque<String> queue = new Deque<>();
		queue.addFirst("Hello World");
		queue.addFirst("I");
		queue.removeFirst();
		queue.removeFirst();
		queue.addLast("eric");
		for (String s : queue) {
			System.out.println(s);
		}
	}
}


