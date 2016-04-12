import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }                          // construct an empty set of points

    public boolean isEmpty() {
        return points.isEmpty();
    }                      // is the set empty?

    public int size() {
        return points.size();
    }                    // number of points in the set

    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (!points.contains(p)) {
            points.add(p);
        }
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return points.contains(p);
    }            // does the set contain point p?

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }          // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        List<Point2D> set = new ArrayList<>();
        for (Point2D p : points) {
            if (rect.xmin() <= p.x() && rect.xmax() >= p.x()) {
                if (rect.ymin() <= p.y() && rect.ymax() >= p.y()) {
                    set.add(p);
                }
            }
        }
        return set;
    }        // all points that are inside the rectangle

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        double dist = Double.MAX_VALUE;
        Point2D nearestP = null;
        for (Point2D a : points) {
            double tmpDist = p.distanceTo(a);
            if (!a.equals(p) && tmpDist <= dist) {
                dist = tmpDist;
                nearestP = a;
            }
        }
        return nearestP;
    }      // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
    }               // unit testing of the methods (optional)
}
