import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
		while (!StdIn.isEmpty()) {
			randomizedQueue.enqueue(StdIn.readString());
		}
		Iterator<String> iterator = randomizedQueue.iterator();
		int i = 0;
		while (iterator.hasNext() && i < Integer.parseInt(args[0])) {
			System.out.println(iterator.next());
			i++;
		}
	}

}
