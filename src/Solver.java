import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;


public class Solver {

    private boolean solvable = false;
    private int moves = -1;
    private Stack<Board> pathStack = new Stack<>();

    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            int m1 = o1.manhattan() + o1.moves;
            int m2 = o2.manhattan() + o2.moves;
            if (m1 > m2) {
                return 1;
            } else if (m1 < m2) {
                return -1;
            } else {
                return 0;
            }
        }
    };


    public Solver(Board initial) {

        if (initial.isGoal()) {
            pathStack.push(initial);
            solvable = true;
            moves = 0;
        } else {
            Node n = resolveAndGetMoves(initial);
            if (n != null) {
                solvable = true;
                moves = n.moves;
                pathStack.push(n.board);
                Node previous = n.previous;
                while (previous != null) {
                    pathStack.push(previous.board);
                    previous = previous.previous;
                }
            }


        }
    }

    public boolean isSolvable() {
        return solvable;
    }         // is the initial board solvable?

    public int moves() {
        return moves;
    }

    private class Node {
        private Board board;
        private Node previous;
        private int moves;

        public Node(Board current, Node previous, int moves) {
            this.board = current;
            this.previous = previous;
            this.moves = moves;
        }

        public int manhattan() {
            return board.manhattan();
        }

        public int hamming() {
            return board.hamming();
        }

    }

    private Node resolveAndGetMoves(Board current) {
        MinPQ<Node> queue = new MinPQ<>(comparator);
        MinPQ<Node> queueTwin = new MinPQ<>(comparator);
        Board twin = current.twin();

        queue.insert(new Node(current, null, 0));
        queueTwin.insert(new Node(twin, null, 0));

        while (!queue.isEmpty()) {
            Node minTwin = queueTwin.delMin();
            Node min = queue.delMin();

            if (min.board.isGoal()) {
                return min;
            } else if (minTwin.board.isGoal()) {
                return null;
            } else {
                Iterable<Board> neighbors = min.board.neighbors();
                for (Board n : neighbors) {
                    if (min.previous == null || !n.equals(min.previous.board)) {
                        queue.insert(new Node(n, min, min.moves + 1));
                    }
                }

                Iterable<Board> neighborsTwin = minTwin.board.neighbors();
                for (Board n : neighborsTwin) {
                    if (minTwin.previous == null || !n.equals(minTwin.previous.board)) {
                        queueTwin.insert(new Node(n, minTwin, minTwin.moves + 1));
                    }
                }
            }
        }
        return null;
    }

    public Iterable<Board> solution() {
        if (isSolvable()) {
            return pathStack;
        } else {
            return null;
        }

    }     // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
//        System.out.println("MANH: " + initial.manhattan());
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
}
