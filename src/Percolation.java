/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 0 1 2 3 4
 * 5 6 7 8 9
 * 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
 *
 * 1,3 -1 0,2 2*5 +0
 *
 * @author sergey
 */
public class Percolation {

    private int size;
    private int nGrid;
    private int[] arr;
    private WeightedQuickUnionUF uf;
    public int openAmount = 0;

    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException("Wrong args!");
        }
        nGrid = N;
        arr = new int[N * N];
        uf = new WeightedQuickUnionUF(N * N);
    }               // create N-by-N grid, with all sites blocked

    public void open(int i, int j) {
        int index = getIndex(i,j);
//        System.out.println("OPEN:i= "+i+"j="+j+"index: "+index+" ; arr.length:"+arr.length+" ;");
        arr[index] = 1;
        openAmount++;
        checkAdj(index, i, j);

    }

    // open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) {
        return getIndex(j, i) == 1;

    }     // is site (row i, column j) open?

    public boolean isFull(int i, int j) {
        for (int k = (i - 1) * nGrid; k < k + nGrid; k++) {
            if (arr[k] < 1) {
                return false;
            }
        }
        return true;
    }     // is site (row i, column j) full?

    public boolean percolates() {
        for (int i = 0; i < nGrid; i++) {
            for (int j = nGrid * (nGrid - 1); j < arr.length; j++) {
                if (uf.connected(i, j)) {
                    return true;
                }
            }

        }
        return false;
    }
    // does the system percolate?

    public int getIndex(int row,int column) {
//        int j = column!=0?(column - 1):column;
//        int i = row!=0?(row - 1) * nGrid:row;
        int j = column - 1;
        int i = (row - 1) * nGrid;
        int index = j + i;
        return index;
    }

    private void checkAdj(int index, int row, int column) {
//        System.out.println("checkAdj: index="+index+" row="+row+" column="+column);
        if (column > 1) {
            int left = index - 1;
            connectAdj(index, left);
        }
        if (column < nGrid - 1) {
            int right = index + 1;
            connectAdj(index, right);
        }
        if (row > 1) {
            int up = getIndex(column, row - 1);
            connectAdj(index, up);
        }
        if (row < nGrid - 1) {
            int down = getIndex(column, row + 1);
            connectAdj(index, down);
        }
    }

    private void connectAdj(int index, int adj) {

        if (arr[adj] > 0 && !uf.connected(index, adj)) {
            uf.union(index, adj);
        }
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
            if ((i + 1) % nGrid == 0) {
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 3);
        p.print();
        System.out.println(p.percolates());
        p.open(4, 3);
        p.print();
        System.out.println(p.percolates());
        p.open(5, 3);
        p.print();
        System.out.println(p.percolates());
    }  // test client (optional)
}
