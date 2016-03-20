package week.three;

import java.util.*;

public class FastCollinearPoints {
    private int numberOfSegments = 0;
    private List<LineSegment> lineArr = new ArrayList<>();

    private void exchange(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;

    }

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
//        Arrays.sort(points);
//    Arrays.sort(points, points[0].slopeOrder());
        Point[] copyPoints = Arrays.copyOf(points, points.length);

        for (int a = 0; a < points.length; a++) {

            if (points[a] == null) {
                throw new NullPointerException();
            }
//            Arrays.sort(points, copyPoints[a].slopeOrder());

            Point sortPoint = copyPoints[a];
            Comparator comparator = copyPoints[a].slopeOrder();

            for (int b = a + 1; b < points.length; b++) {
                if (sortPoint.toString().equals("(4096, 20992)")) {
                    System.out.println("++++++++++++++++++++++++++");
                }
                int lt = 0;
                int i = 0;
                int gt = points.length - 1;
                Point v = copyPoints[b];
                while (i <= gt) {
//                int less =v.compareTo(points[i]);

                    int less = comparator.compare(v, points[i]);

                    if (less > 0) {
                        exchange(points, i++, lt++);
                    } else if (less < 0) {
                        exchange(points, i, gt--);
                    } else {
                        i++;
                    }

                }
                System.out.println("sortPoint: " + sortPoint + " pointB: " + points[b]);
                System.out.println("lt: " + lt + " gt: " + gt);
                System.out.println(Arrays.toString(points));
                System.out.println("++++++++++++++++++++++++++");

                if (gt - lt > 1) {

                    Arrays.sort(points, lt, gt + 1);
                    if (sortPoint.compareTo(points[lt]) <= 0 && v.compareTo(points[lt]) == 0) {
                        numberOfSegments++;
                        lineArr.add(new LineSegment(sortPoint, points[gt]));
//                        break;
                    }


                }

            }


        }
        System.out.println("SEGMENTS: " + numberOfSegments);
    }// finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numberOfSegments;
    }    // the number of line segments

    public LineSegment[] segments() {
        return lineArr.toArray(new LineSegment[0]);
    }  // the line segments
}