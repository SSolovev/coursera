package week.three;

import java.util.*;


public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private List<LineSegment> lineArr;
    private Set<Point> included = new HashSet<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        lineArr = new ArrayList<LineSegment>();
//        Arrays.sort(points, points[0].slopeOrder());
        int incl = 0;

        for (int a = 0; a < points.length - 3; a++) {

            if (points[a] == null) {
                throw new NullPointerException();
            }
            if (points[a].slopeTo(points[a + 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
            incl = 0;
            if (included.contains(points[a])) {
                incl++;
            }
            second:
            for (int b = a + 1; b < points.length; b++) {
                if (included.contains(points[b])) {
                    incl++;
                }
                if (incl > 1) {
                    break;
                }
                for (int c = b + 1; c < points.length; c++) {
                    if (included.contains(points[c])) {
                        incl++;
                    }
                    if (incl > 1) {
                        break;
                    }
                    double d1 = points[a].slopeTo(points[b]);
                    double d2 = points[a].slopeTo(points[c]);
                    if (d1 == d2) {
                        for (int d = c + 1; d < points.length; d++) {
                            if (included.contains(points[d])) {
                                incl++;
                            }
                            if (incl > 1) {
                                break;
                            }
//                            if (isIncluded(points[a], points[b], points[c], points[d])) {
//                                break;
//                            }
                            double d3 = points[a].slopeTo(points[d]);
                            if (d1 == d3) {
//                                LineSegment l1 = new LineSegment(points[a], points[b]);
//                                LineSegment l2 = new LineSegment(points[b], points[c]);
//                                LineSegment l3 = new LineSegment(points[c], points[d]);
//
//                                if(!isAdded(l1)){break;}
//                                if(!isAdded(l2)){break;}
//                                if(!isAdded(l3)){break;}
                                lineArr.add(new LineSegment(points[a], points[d]));
                                included.add(points[a]);
                                included.add(points[b]);
                                included.add(points[c]);
                                included.add(points[d]);
                                numberOfSegments++;

                                System.out.println("One line: " + points[a] + " " + points[b] + " " + points[c] + " " + points[d]);
                                break second;
                            }
                        }
                    }

                }
            }
        }
    }   // finds all line segments containing 4 points

    private boolean isAdded(LineSegment a) {
        if (lineArr.contains(a)) {
            return false;
        } else {
            lineArr.add(a);
            return true;
        }
    }

    private boolean isIncluded(Point a, Point b, Point c, Point d) {
        int i = 0;
        if (included.contains(a)) {
            i++;
        }
        if (included.contains(b)) {
            i++;
        }
        if (included.contains(c)) {
            i++;
        }
        if (included.contains(d)) {
            i++;
        }
        return i > 1;
    }

    public int numberOfSegments() {
        return numberOfSegments;
    } // the number of line segments

    public LineSegment[] segments() {
        return lineArr.toArray(new LineSegment[0]);
    }// the line segments
}
