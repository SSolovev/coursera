//package week.three;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by solovevs on 17-Mar-16.
 */
public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that.y == y && that.x == x) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.x - x == 0) {
            return Double.POSITIVE_INFINITY;
        } else if (that.y - y == 0) {
            return +0.0;
        } else {
            return Double.valueOf(that.y - y) / (that.x - x);
        }

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (y == that.y && x == that.x) {
            return 0;
//    } else if (y <= that.y && x < that.x) {
        } else if (y < that.y || (y == that.y && x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new ParashkaComparashka();
    }


    private class ParashkaComparashka implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {

            double slopeVal1 = Point.this.slopeTo(o1);
            double slopeVal2 = Point.this.slopeTo(o2);

            if (slopeVal1 > slopeVal2) {
                return 1;
            } else if (slopeVal1 < slopeVal2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(5913, 5886);
        Point p2 = new Point(3630, 12303);
//        slope:-2.693820224719101 point1:(5913, 5886) point2:(3777, 11640)
//        slope:-2.773500447627574 point1:(5913, 5886) point2:(3679, 12082)
//        slope:-2.8107752956636007 point1:(5913, 5886) point2:(3630, 12303)
//        Exception in thread "main" java.lang.IllegalArgumentException: Comparison method violates its general contract!
        System.out.println(p1.slopeTo(p2));
        System.out.println(p2.slopeTo(p1));
    }
}
