//javac -Djava.ext.dirs=../ ...
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomWord{
	public static void main(String[] argv){
		double p = 1;
		int i = 1;
		String now = null;
		while(!StdIn.isEmpty()){
			String word = StdIn.readString();
			if(now == null){
				now = word;
			}else{
				if(StdRandom.bernoulli(1.0 / i)){
					now = word;
				}
			}
			i++;
		}
		StdOut.println(now);
	}
}