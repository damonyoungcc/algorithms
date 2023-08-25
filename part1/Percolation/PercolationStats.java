import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int t;
    private final double[] pList;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        t = trials;
        pList = new double[t];
        for (int index = 0; index < trials; index++) {
            Percolation pl = new Percolation(n);
            while (!pl.percolates()) {
                int i = StdRandom.uniformInt(0, n);
                int j = StdRandom.uniformInt(0, n);
                if (!pl.isOpen(i, j)) {
                    pl.open(i, j);
                }
            }
            double p = (double) pl.numberOfOpenSites() / (n * n);
            StdOut.println(p);
            pList[index] = p;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pList);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0;

    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(8000, 10);
        StdOut.println("mean                    = " + ps.mean());
    }
}
