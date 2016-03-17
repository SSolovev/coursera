package week.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments = 0;
    private List<LineSegment> lineArr = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        Arrays.sort(points, points[0].slopeOrder());
        Arrays.sort(points);
        for (int i = 0; i < points.length - 3; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
            if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
            double d1 = points[i].slopeTo(points[i + 1]);
            double d2 = points[i].slopeTo(points[i + 2]);
            if (d1 == d2) {
                double d3 = points[i].slopeTo(points[i + 3]);
                if (d1 == d3) {
//                    Point[] subPoints=new Point[]{points[i],points[i+1],points[i+2], points[i + 3]};
//                    Arrays.sort(subPoints);
                    lineArr.add(new LineSegment(points[i], points[i + 3]));
                    System.out.println("One line: " + points[i] + " " + points[i + 1] + " " + points[i + 2] + " " + points[i + 3]);
                    i = i + 3;
                    numberOfSegments++;
//                    System.out.println("One line: " + points[i] + " " + points[i+1] + " " + points[i+2] + " " + points[i+3]);
                }
            }
        }
    }// finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numberOfSegments;
    }    // the number of line segments

    public LineSegment[] segments() {
        return lineArr.toArray(new LineSegment[0]);
    }  // the line segments
}