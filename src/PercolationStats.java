import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] openOnEveryIter;
    private double iterations = 0.0;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Error!");
        }

        iterations = T;
        openOnEveryIter = new double[T];
        for (int a = 0; a < T; a++) {
            Percolation p = new Percolation(N);
            int iter = 0;
            while (!p.percolates()) {
                int i;
                int j;
                do {
                    i = StdRandom.uniform(1, N + 1);
                    j = StdRandom.uniform(1, N + 1);
                } while (p.isOpen(i, j));

                p.open(i, j);
                iter++;

            }
            openOnEveryIter[a] = iter * 1.0 / (N * N);
        }

    }     // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(openOnEveryIter);
    }                      // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(openOnEveryIter);
    }                    // sample standard deviation of percolation threshold

    public double confidenceLo() {

        return mean() - (1.96 * stddev() / Math.sqrt(iterations));
    }              // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(iterations));
    }              // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int N = 200;
        int T = 100;
        if (args.length > 0) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean= " + ps.mean());
        System.out.println("stddev= " + ps.stddev());
        System.out.println("95% confidence interval= " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}


