import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int unionLastIndex;
    private int nGrid;
    private int[] arr;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Error!");
        }
        nGrid = N;
        arr = new int[N * N];
        unionLastIndex = N * N + 1;
        uf = new WeightedQuickUnionUF(unionLastIndex + 1);
    }

    public void open(int i, int j) {
        int index = getIndex(i, j);

        if (index < nGrid) {
            uf.union(0, index + 1);
        }
        if (index >= nGrid * (nGrid - 1)) {
            uf.union(index + 1, unionLastIndex);
        }

        arr[index] = 1;
        checkAdj(index, i, j);

    }

    public boolean isOpen(int i, int j) {
        return arr[getIndex(i, j)] == 1;

    }     // is site (row i, column j) open?

    public boolean isFull(int i, int j) {
        int index = getIndex(i, j);
        if (arr[index] != 1) {
            return false;
        } else if (uf.connected(0, index + 1)) {
            return true;
        }
        return false;
    }     // is site (row i, column j) full?

    public boolean percolates() {
        if (uf.connected(0, unionLastIndex)) {
            return true;
        }
        return false;
    }
    // does the system percolate?

    private int getIndex(int row, int column) {
        validateIndexes(row, column);
        int j = column - 1;
        int i = (row - 1) * nGrid;
        int index = j + i;
        return index;
    }

    private void validateIndexes(int i, int j) {
        if (i <= 0 || i > nGrid || j <= 0 || j > nGrid) {
            throw new IndexOutOfBoundsException("Error!");
        }
    }

    private void checkAdj(int index, int row, int column) {

        if (column > 1) {
            int left = index - 1;
            connectAdj(index, left);
        }
        if (column < nGrid) {
            int right = index + 1;
            connectAdj(index, right);
        }
        if (row > 1) {
            int up = getIndex(row - 1, column);
            connectAdj(index, up);
        }
        if (row < nGrid) {
            int down = getIndex(row + 1, column);
            connectAdj(index, down);
        }
    }

    private void connectAdj(int index, int adj) {
        if (arr[adj] > 0 && !uf.connected(index + 1, adj + 1)) {
            uf.union(index + 1, adj + 1);
        }
    }

    private void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
            if ((i + 1) % nGrid == 0) {
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        p.open(1, 1);
//        p.open(2, 3);
//        p.open(3, 3);
//        p.open(3, 1);
//        p.open(2, 1);
//        p.open(1, 1);
        p.print();
        System.out.println(p.percolates());
    }
}