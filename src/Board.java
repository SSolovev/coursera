import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by solovevs on 31-Mar-16.
 */
public class Board {

    private int dimSize;
    private int[][] blocks;

    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }
        dimSize = blocks.length;
        this.blocks = blocks;
    }

    ;          // construct a board from an N-by-N array of blocks

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dimSize;
    }

    ;                  // board dimension N

    public int hamming() {
        int counter = 0;
        for (int j = 0; j < dimSize; j++) {
            for (int i = 0; i < dimSize; i++) {
                int properValue = j * dimSize + i + 1;
                if (blocks[j][i] != properValue && blocks[j][i] != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // number of blocks out of place

    public int manhattan() {
        int counter = 0;
        for (int j = 0; j < dimSize; j++) {
            for (int i = 0; i < dimSize; i++) {
                int properValue = j * dimSize + i + 1;
                if (blocks[j][i] != properValue && blocks[j][i] != 0) {
                    int properInd = properValue - 1;
                    int currentInd = blocks[j][i] - 1;
                    int diff = Math.abs(currentInd - properInd);
                    if (diff > dimSize) {
                        int a = diff / dimSize;
                        int b = diff % dimSize;
                        counter += a;
                        if (b > 0) {
                            counter += b;
                        }
                    } else {
                        counter += diff;
                    }
//          counter++;
                }
            }
        }
        return counter;
    }

    // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return hamming() == 0;
    }

    // is this board the goal board?

    public Board twin() {
        int i = 0;
        int j = 0;
        do {
            i = StdRandom.uniform(dimSize);
            j = StdRandom.uniform(dimSize);
        } while (blocks[i][j] == 0);

        if (i > 0 && blocks[i - 1][j] != 0)
            return changeAndMakeBoard(blocks, i, j, i - 1, j);
        if (j > 0 && blocks[i][j - 1] != 0)
            return changeAndMakeBoard(blocks, i, j, i, j - 1);
        if (i < dimSize - 1 && blocks[i + 1][j] != 0)
            return changeAndMakeBoard(blocks, i, j, i + 1, j);
//        if (j < dimSize - 1&& blocks[i][j+1]!=0)
        return changeAndMakeBoard(blocks, i, j, i, j + 1);

    }

    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        if (y instanceof Board) {

            return Arrays.deepEquals(this.blocks, ((Board) y).blocks);
        } else {
            return false;
        }

    }

    // does this board equal y?

    public Iterable<Board> neighbors() {
        List<Board> neighborList = new ArrayList<>();

        for (int j = 0; j < dimSize; j++) {
            for (int i = 0; i < dimSize; i++) {
                if (blocks[j][i] == 0) {

                    if (i > 0) {
                        neighborList.add(changeAndMakeBoard(blocks, j, i, j, i - 1));
                    }
                    if (j > 0) {
                        neighborList.add(changeAndMakeBoard(blocks, j, i, j - 1, i));
                    }
                    if (i < dimSize - 1) {
                        neighborList.add(changeAndMakeBoard(blocks, j, i, j, i + 1));
                    }
                    if (j < dimSize - 1) {
                        neighborList.add(changeAndMakeBoard(blocks, j, i, j + 1, i));
                    }

                    return neighborList;
                }
            }
        }
        return neighborList;
    }

    // all neighboring boards

    private Board changeAndMakeBoard(int[][] arr, int j1, int i1, int j2, int i2) {
        int[][] newArr = new int[dimSize][dimSize];
        for (int i = 0; i < dimSize; i++) {
            newArr[i] = Arrays.copyOf(arr[i], dimSize);
        }

        int tmp = newArr[j1][i1];
        newArr[j1][i1] = newArr[j2][i2];
        newArr[j2][i2] = tmp;
        return new Board(newArr);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimSize + "\n");
        for (int i = 0; i < dimSize; i++) {
            for (int j = 0; j < dimSize; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
//    int[][] tst = {{0, 1, 3},
//            {4, 2, 5},
//            {7, 8, 6}};
        int[][] tst = {{8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}};
        Board b = new Board(tst);
        System.out.println(b);
//    System.out.println("======");
//    for (Board n : b.neighbors()) {
//      System.out.println(n);
//    }
        System.out.println("Manh: " + b.manhattan());
        System.out.println("Hamming: " + b.hamming());
    }

    // unit tests (not graded)

}