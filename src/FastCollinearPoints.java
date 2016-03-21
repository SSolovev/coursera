//package week.three;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments = 0;
    private List<LineSegment> lineArr = new ArrayList<>();

    public FastCollinearPoints(Point[] copyPoints) {
        if (copyPoints == null) {
            throw new NullPointerException();
        }
        Stopwatch sw = new Stopwatch();
        Point[] points = Arrays.copyOf(copyPoints, copyPoints.length);

        for (int a = 0; a < points.length; a++) {

            if (points[a] == null) {
                throw new NullPointerException();
            }
            Point sortPoint = copyPoints[a];
            Comparator<Point> comparator = copyPoints[a].slopeOrder();

            for (int b = 0; b < points.length; b++) {
//        if (sortPoint.toString().equals("(9000, 1000)")) {
//          System.out.println("++++++++++++++++++++++++++");
//        }
                int lt = 0;
                int i = 0;
                int gt = points.length - 1;
                Point v = copyPoints[b];
                while (i <= gt) {

                    int less = comparator.compare(v, points[i]);
                    if (less > 0) {
                        exchange(points, i++, lt++);
                    } else if (less < 0) {
                        exchange(points, i, gt--);
                    } else {
                        i++;
                    }

                }
//                System.out.println("sortPoint: " + sortPoint + " pointB: " + points[b]);
//                System.out.println("lt: " + lt + " gt: " + gt);
//                System.out.println(Arrays.toString(points));
//                System.out.println("++++++++++++++++++++++++++");

                if (gt - lt > 1) {

                    Arrays.sort(points, lt, gt + 1);

                    if (sortPoint.compareTo(points[lt]) <= 0 && v.compareTo(points[lt]) == 0) {
                        numberOfSegments++;
                        lineArr.add(new LineSegment(sortPoint, points[gt]));
                    }

                }

            }


        }
        System.out.println(sw.elapsedTime());


    }// finds all line segments containing 4 or more points

    private void exchange(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }


    public int numberOfSegments() {
        return numberOfSegments;
    }    // the number of line segments

    public LineSegment[] segments() {
        return lineArr.toArray(new LineSegment[0]);
    }  // the line segments
}