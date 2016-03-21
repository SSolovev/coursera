//package week.three;


import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private List<LineSegment> lineArr;

    public BruteCollinearPoints(Point[] pointArr) {
        if (pointArr == null) {
            throw new NullPointerException();
        }
        Stopwatch sw = new Stopwatch();
        Point[] points = Arrays.copyOf(pointArr, pointArr.length);
        lineArr = new ArrayList<>();
        Arrays.sort(points);
//    boolean isAdded;
        for (int a = 0; a < points.length - 3; a++) {

            if (points[a] == null) {
                throw new NullPointerException();
            }
//      isAdded = false;
            for (int b = a + 1; b < points.length - 2; b++) {
//        if (isAdded) {
//          break;
//        }

                validate(points[a], (points[b]));

                for (int c = b + 1; c < points.length - 1; c++) {
//          if (isAdded) {
//            break;
//          }
                    validate(points[b], (points[c]));
                    double slopeAB = points[a].slopeTo(points[b]);
                    double slopeAC = points[a].slopeTo(points[c]);
                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < points.length; d++) {
                            validate(points[c], (points[d]));
                            if (slopeAC == points[a].slopeTo(points[d])) {
//                if(points[a].compareTo(points[b])<0 && points[b].compareTo(points[c])<0 && points[c].compareTo(points[d])<0){
//                if (points[a].compareTo(points[b]) < 0 && points[c].compareTo(points[d]) < 0) {
//                System.out.println(points[a] + " " + points[b] + " " + points[c] + " " + points[d]);
                                numberOfSegments++;
                                lineArr.add(new LineSegment(points[a], points[d]));
//                  isAdded = true;
//                  break;
//                }
                            }
                        }
                    }

                }
            }
        }
        System.out.println(sw.elapsedTime());
//    System.out.println(numberOfSegments / 4);
    }   // finds all line segments containing 4 points

    private void validate(Point a, Point b) {
        if (a.slopeTo(b) == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments() {

        return numberOfSegments;
    } // the number of line segments

    public LineSegment[] segments() {
        return lineArr.toArray(new LineSegment[0]);
    }// the line segments
}
