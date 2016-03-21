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

        Point[] points = Arrays.copyOf(pointArr, pointArr.length);
        lineArr = new ArrayList<>();
        Arrays.sort(points);

        for (int a = 0; a < points.length - 3; a++) {
            if (points[a] == null) {
                throw new NullPointerException();
            }
            for (int b = a + 1; b < points.length - 2; b++) {
                validate(points[a], (points[b]));
                for (int c = b + 1; c < points.length - 1; c++) {
                    validate(points[b], (points[c]));
                    double slopeAB = points[a].slopeTo(points[b]);
                    double slopeAC = points[a].slopeTo(points[c]);
                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < points.length; d++) {
                            validate(points[c], (points[d]));
                            if (slopeAC == points[a].slopeTo(points[d])) {
                                numberOfSegments++;
                                lineArr.add(new LineSegment(points[a], points[d]));
                            }
                        }
                    }

                }
            }
        }
    }

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
