import edu.princeton.cs.algs4.*;

import java.util.Comparator;

/**
 * Created by solovevs on 31-Mar-16.
 */
public class Solver {

    //  private Board board;
//  private MinPQ<Board> result;
    private boolean solvable = false;
    private int moves = -1;
    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {


            int o1Moves = o1.getMoves();
            int o2Moves = o2.getMoves();

            int o1Ham = o1.hamming() + o1Moves;
            int o2Ham = o2.hamming() + o2Moves;

            if (o1Ham > o2Ham) {
                return 1;
            } else if (o1Ham < o2Ham) {
                return -1;
            } else {
                int o1Man = o1.manhattan() + o1Moves;
                int o2Man = o2.manhattan() + o2Moves;
                if (o1Man > o2Man) {
                    return 1;
                } else if (o1Man < o2Man) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }
    };
    private Queue<Board> pathQueue = new Queue<>();
//    private MinPQ<Board> pathQueueTwin = new MinPQ<Board>(comparator);

    public Solver(Board initial) {
        Board twin = initial.twin();
//        System.out.println("Initial: \n"+initial);
//        System.out.println("Twin: \n"+twin);
        if (initial.isGoal()) {
            pathQueue.enqueue(initial);
            solvable = true;
        } else if (twin.isGoal()) {
            solvable = false;
        } else {
//            findResult(initial, null, pathQueue, twin, null);
            Node n = ResolveAndGetMoves(initial);
            if (n != null) {
                System.out.println("Result: " + n.getBoard());
                solvable = true;
                moves = n.getMoves();
                Node previous = n.getPrevious();
                pathQueue.enqueue(n.getBoard());
                while (previous != null) {
                    pathQueue.enqueue(previous.getBoard());
                    previous = previous.getPrevious();
                }
            }


        }
//    board = initial;
    }          // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return solvable;
    }         // is the initial board solvable?

    public int moves() {
        if (isSolvable()) {
            return moves;
        } else {
            return -1;
        }

    }       // min number of moves to solve initial board; -1 if unsolvable

//    private MinPQ<Board> queue = new MinPQ<>(comparator);
//    private MinPQ<Board> queueTwin = new MinPQ<>(comparator);
//
//    private void findResult(Board current, Board previous, MinPQ<Board> pathQueue,
//                            Board currentTwin, Board previousTwin) {
//        if (previous == null) {
//            pathQueue.insert(current);
//        }
//
//
////        MinPQ<Board> queue = new MinPQ<>(comparator);
////        MinPQ<Board> queueTwin = new MinPQ<>(comparator);
//        Iterable<Board> neighbors = current.neighbors();
//        Iterable<Board> neighborsTwin = currentTwin.neighbors();
//        for (Board n : neighbors) {
//            if (!n.equals(previous)) {
//                queue.insert(n);
//            }
//        }
//        for (Board nt : neighborsTwin) {
//            if (!nt.equals(previousTwin)) {
//                queueTwin.insert(nt);
//            }
//        }
//        Board min = queue.delMin();
//        Board minTwin = queueTwin.delMin();
//        moves++;
//        pathQueue.insert(min);
//
//        if (min.isGoal()) {
//            return;
//        } else if (minTwin.isGoal()) {
//            solvable = false;
//            return;
//        } else {
//            findResult(min, current, pathQueue,
//                    minTwin, currentTwin);
//        }
//
//    }

    private class Node {
        private Board current;

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            Node node = (Node) o;
//
//            if (moves != node.moves) return false;
//            if (current != null ? !current.equals(node.current) : node.current != null) return false;
//            return previous != null ? previous.equals(node.previous) : node.previous == null;
//
//        }
//
//        @Override
//        public int hashCode() {
//            int result = current != null ? current.hashCode() : 0;
//            result = 31 * result + (previous != null ? previous.hashCode() : 0);
//            result = 31 * result + moves;
//            return result;
//        }

        public Node getPrevious() {

            return previous;
        }

        public Board getBoard() {

            return current;
        }

        private Node previous;

        public int manhattan() {
            return current.manhattan();
        }

        public int hamming() {
            return current.hamming();
        }

        public int getMoves() {
            return moves;
        }

        private int moves;

        public Node(Board current, Node previous, int moves) {
            this.current = current;
            this.previous = previous;
            this.moves = moves;
        }
    }

    private Node ResolveAndGetMoves(Board current) {
//        int movesLocal = 0;
        MinPQ<Node> queue = new MinPQ<Node>(comparator);
        MinPQ<Node> queueTwin = new MinPQ<Node>(comparator);
        Board twin = current.twin();

        queue.insert(new Node(current, null, 0));
        queueTwin.insert(new Node(twin, null, 0));

//        Board previous = null;
//        Board previousTwin = null;

        while (!queue.isEmpty()) {
            Node minTwin = queueTwin.delMin();
            Node min = queue.delMin();
//            System.out.println("++++++++++++++++++\nMoves: " + movesLocal);
//            System.out.println(min);
//            pathQueue.push(min.getBoard());
            if (min.getBoard().isGoal()) {
                return min;
            } else if (minTwin.getBoard().isGoal()) {
                return null;
            } else {
//                movesLocal++;
                Iterable<Board> neighbors = min.getBoard().neighbors();
                for (Board n : neighbors) {
                    if (min.getPrevious() == null || !n.equals(min.getPrevious().getBoard())) {
                        queue.insert(new Node(n, min, min.getMoves() + 1));
                    }
                }
//                previous = min.getBoard();

                Iterable<Board> neighborsTwin = minTwin.getBoard().neighbors();
                for (Board n : neighborsTwin) {
                    if (minTwin.getPrevious() == null || !n.equals(minTwin.getPrevious().getBoard())) {
                        queueTwin.insert(new Node(n, minTwin, minTwin.getMoves() + 1));
                    }
                }
//                previousTwin = minTwin.getBoard();
            }
        }
        return null;
    }

    public Iterable<Board> solution() {
        if (isSolvable()) {
            return pathQueue;
        } else {
            return null;
        }

    }     // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {

//        int[][] tst = {{0, 1, 3},
//                {4, 2, 5},
//                {7, 8, 6}};
//
//        int[][] tst = {{1, 2, 3},
//                {4, 5, 6},
//                {8, 7, 0}};
//        int[][] tst = {{0, 1},
//                       {2, 3}};
//        Board b = new Board(tst);
////    System.out.println(b);
//
//        Solver s = new Solver(b);
//        System.out.println("moves: " + s.moves());
//
//        System.out.println("res: ");
//        for (Board sol : s.solution()) {
//            System.out.println(sol);
//        }
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

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
