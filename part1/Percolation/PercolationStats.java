import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int t;
    private final double[] pList;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("Hold up, N & T have to be greater than zero.");
        }

        t = trials;
        pList = new double[t];
        for (int index = 0; index < trials; index++) {
            Percolation pl = new Percolation(n);
            while (!pl.percolates()) {
                int i = StdRandom.uniformInt(1, n + 1);
                int j = StdRandom.uniformInt(1, n + 1);
                pl.open(i, j);
            }
            double p = (double) pl.numberOfOpenSites() / (n * n);
            pList[index] = p;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pList);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pList);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(t));
    }

    public static void main(String[] args) {
        int n = 100;
        int t = 10000;
        // int n = Integer.parseInt(args[0]);
        // int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
