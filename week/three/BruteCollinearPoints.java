package week.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BruteCollinearPoints {
  private int numberOfSegments = 0;
  private List<LineSegment> lineArr;

  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new NullPointerException();
    }
    lineArr = new ArrayList<LineSegment>();
//    Arrays.sort(points, points[0].slopeOrder());
    for (int a = 0; a < points.length - 4; a++) {
      if (points[a] == null) {
        throw new NullPointerException();
      }
      if (points[a].slopeTo(points[a + 1]) == Double.NEGATIVE_INFINITY) {
        throw new IllegalArgumentException();
      }
      for (int b = a + 1; b < points.length; b++) {

        for (int c = b + 1; c < points.length; c++) {
          double d1 = points[a].slopeTo(points[b]);
          double d2 = points[a].slopeTo(points[c]);
          if (d1 == d2) {
            for (int d = c + 1; d < points.length; d++) {
              double d3 = points[a].slopeTo(points[d]);
              if (d1 == d3) {
                lineArr.add(new LineSegment(points[a], points[d]));
                numberOfSegments++;
                System.out.println("One line: "+points[a]+" "+points[b]+" "+points[c]+" "+points[d]);
              }
            }
          }

        }
      }
    }
  }   // finds all line segments containing 4 points

  public int numberOfSegments() {
    return numberOfSegments;
  } // the number of line segments

  public LineSegment[] segments() {
    return lineArr.toArray(new LineSegment[0]);
  }// the line segments
}
