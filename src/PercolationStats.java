/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  double[] openOnEveryIter;
  int experimentRepeating = 0;
  int size = 0;

  public PercolationStats(int N, int T) {
    if (N <= 0 || T <= 0) {
      throw new IllegalArgumentException("Wrong args!");
    }
    experimentRepeating = T;
    size = N;
    openOnEveryIter = new double[T];
    for (int a = 0; a < T; a++) {
      Percolation p = new Percolation(N);
      int iter = 0;
      while (!p.percolates()) {
        int i = StdRandom.uniform(1, N + 1);
        int j = StdRandom.uniform(1, N + 1);
        if (!p.isOpen(i, j)) {
          p.open(i, j);
          iter++;
        }
      }
//            System.out.println("Popitka: " + (a + 1) + " Itercii: " + iter);
//            System.out.println("openAmount/N*N: "+p.openAmount+"/" +(N*N) +"= " +  (p.openAmount*1.0/(N*N)));
      openOnEveryIter[a] = p.openAmount * 1.0 / (N * N);
    }

  }     // perform T independent experiments on an N-by-N grid

  public double mean() {
    return StdStats.mean(openOnEveryIter);
  }                      // sample mean of percolation threshold

  public double stddev() {
    return StdStats.stddev(openOnEveryIter);
  }                    // sample standard deviation of percolation threshold

  public double confidenceLo() {
    double sqrtT=Math.sqrt(experimentRepeating);
    double a=1.96 * stddev()/sqrtT;
    return mean() - a;
  }              // low  endpoint of 95% confidence interval

  public double confidenceHi() {
    double sqrtT=Math.sqrt(experimentRepeating);
    double a=1.96 * stddev()/sqrtT;
    return mean() + a;
  }              // high endpoint of 95% confidence interval

  public static void main(String[] args) {
    int T = 20;
    int N = 10;
    if (args.length != 0) {
      T = Integer.parseInt(args[0]);
      N = Integer.parseInt(args[1]);
    }

    PercolationStats ps = new PercolationStats(200, 100);
    System.out.println("mean= " + ps.mean());
    System.out.println("stddev= " + ps.stddev());
    System.out.println("95% confidence interval= " + ps.confidenceLo() + ", " + ps.confidenceHi());

  }    // test client (described below)
}
