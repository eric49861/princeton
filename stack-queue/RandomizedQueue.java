import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private class QueueIterator implements Iterator<Item> {
		private Item[] iterItems;
		private int length;

		public QueueIterator() {
			iterItems = (Item[]) new Object[size];
			length = size;
			for (int i = 0; i < size; i++) {
				iterItems[i] = arr[i];
			}
		}

		@Override
		public boolean hasNext() {
			return length != 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int index = StdRandom.uniformInt(length);
			Item item = iterItems[index];
			iterItems[index] = iterItems[length - 1];
			iterItems[length - 1] = item;
			return iterItems[--length];
		}
	}

	private int size;
	private Item[] arr;

	public RandomizedQueue() {
		arr = (Item[]) new Object[10];
	}

	// verify the argument is null or not
	private void valid(Item value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void resize(int cap) {
		Item[] newArr = (Item[]) new Object[cap];
		for (int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	public void enqueue(Item item) {
		valid(item);
		if (size == arr.length) {
			resize(arr.length * 2);
		}
		arr[size++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int index = StdRandom.uniformInt(size);
		Item item = arr[index];
		arr[index] = arr[size - 1];
		arr[size - 1] = item;

		if (size - 1 < arr.length / 4) {
			resize(arr.length / 2);
		}

		return arr[--size];
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniformInt(size);

		return arr[index];
	}

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	public static void main(String[] args) {
		String[] ss = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		for (String s : ss) {
			queue.enqueue(s);
		}
		Iterator<String> iterator = queue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
