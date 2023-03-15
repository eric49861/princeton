import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF wUf = null;
	private int open = 0;
	private int n = -1;
	private int top;
	private int bottom;
	private boolean[][] grid = null;

	// creates n-by-n grid, with all sites initially blocked
	// 0 represents blocked, 1 represents open
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		this.wUf = new WeightedQuickUnionUF(n * n + 2);
		this.n = n;
		this.grid = new boolean[n + 1][n + 1];
		this.top = 0;
		this.bottom = n * n + 1;
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException();
		}
		if (!isOpen(row, col)) {
			this.open++;
			this.grid[row][col] = true;
			int me = (row - 1) * this.n + col;
			
			if (row == 1) {
				wUf.union(top, me);
			} else if (row == this.n) {
				wUf.union(bottom, me);
			}
			
			// 上
			if (row >= 2 && isOpen(row - 1, col)) {
				int up = me - this.n;
				wUf.union(me, up);
			}
			//
			if (row <= this.n - 1 && isOpen(row + 1, col)) {
				int down = me + this.n;
				wUf.union(me, down);
			}
			//
			if (col >= 2 && isOpen(row, col - 1)) {
				int left = me - 1;
				wUf.union(me, left);
			}
			//
			if (col <= this.n - 1 && isOpen(row, col + 1)) {
				int right = me + 1;
				wUf.union(me, right);
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException();
		}
		return this.grid[row][col];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException();
		}
		if (!isOpen(row, col)) {
			return false;
		}
		int me = (row - 1) * this.n + col;
		return wUf.find(me) == wUf.find(top);
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return this.open;
	}

	// does the system percolate?
	public boolean percolates() {
		return wUf.find(bottom) == wUf.find(top);
	}

	// test client (optional)
	public static void main(String[] args) {
		Percolation percolation = new Percolation(20);
		for (int i = 1; i <= 20; i++) {
			percolation.open(i, 1);
		}
		percolation.open(2, 2);
		percolation.open(2, 3);
		System.out.println(percolation.isFull(2, 3));
	}
}
