import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int trials;
	private double[] results = null;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		this.trials = trials;
		this.results = new double[trials];
		int row = 0;
		int col = 0;
		for (int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(n);
			while (!percolation.percolates()) {
				while (true) {
					row = StdRandom.uniformInt(1, n + 1);
					col = StdRandom.uniformInt(1, n + 1);
					if (percolation.isOpen(row, col)) {
						continue;
					}
					break;
				}
				percolation.open(row, col);

			}
			this.results[i] = (double) percolation.numberOfOpenSites() / (1.0d * n * n);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(results);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(results);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(trials);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(trials);
	}

	// test client (see below)
	public static void main(String[] args) {
	    int n = Integer.parseInt(args[0]);
	    int trials = Integer.parseInt(args[1]);
		PercolationStats pStats = new PercolationStats(n, trials);

		System.out.println("mean                    = " + pStats.mean());
		System.out.println("stddev                  = " + pStats.stddev());
		System.out.println("95% confidenceinterval  = [" + pStats.confidenceLo() + ", " + pStats.confidenceHi() + "]");
	}
}
