import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by solovevs on 12-Apr-16.
 */
public class KdTree {
    private class Node {
        private Point2D val;
        private Node right;
        private Node left;

        public Node(Point2D current) {
            val = current;
        }


    }

    private Node root;
    private int size = 0;

    public KdTree() {

    }                          // construct an empty set of points

    public boolean isEmpty() {
        return root == null;
    }                      // is the set empty?

    public int size() {
        return size;
    }                    // number of points in the set

    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(p);
        } else {
            findAndInsert(root, p, true);
        }
        size++;
    }              // add the point to the set (if it is not already in the set)


    private void findAndInsert(Node root, Point2D p, boolean compareX) {
        if (compareX) {
            if (p.x() < root.val.x()) {
                if (root.left == null) {
                    root.left = new Node(p);
                } else {
                    findAndInsert(root.left, p, false);
                }

            } else {
                if (root.right == null) {
                    root.right = new Node(p);
                } else {
                    findAndInsert(root.right, p, false);
                }
            }
        } else {
            if (p.y() < root.val.y()) {
                if (root.left == null) {
                    root.left = new Node(p);
                } else {
                    findAndInsert(root.left, p, true);
                }

            } else {
                if (root.right == null) {
                    root.right = new Node(p);
                } else {
                    findAndInsert(root.right, p, true);
                }
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            return false;
        } else {
//      return findAndProcess(root, p, true, new Search());
            return find(root, p, true);
        }

    }

    private boolean find(Node root, Point2D p, boolean compareX) {
        if (root.val.equals(p)) {
            return true;
        }
        if (compareX) {
            if (p.x() < root.val.x()) {
                if (root.left == null) {
                    return false;
                } else {
                    find(root.left, p, false);
                }

            } else {
                if (root.right == null) {
                    return false;
                } else {
                    find(root.right, p, false);
                }
            }
        } else {
            if (p.y() < root.val.y()) {
                if (root.left == null) {
                    return false;
                } else {
                    find(root.left, p, true);
                }

            } else {
                if (root.right == null) {
                    return false;
                } else {
                    find(root.right, p, true);
                }
            }
        }
        return false;
    }

    public void draw() {
        if (root != null) {
            drawPoints(root);
        }
    }          // draw all points to standard draw

    private void drawPoints(Node n) {
        n.val.draw();
        if (n.left != null) {
            drawPoints(n.left);
        }
        if (n.right != null) {
            drawPoints(n.right);
        }
    }

    private void findRange(RectHV rect, Node n, List<Point2D> s, boolean compareX) {
        Point2D p = n.val;

        if (rect.xmin() <= p.x() && rect.xmax() >= p.x()) {
            if (rect.ymin() <= p.y() && rect.ymax() >= p.y()) {
                s.add(p);
            }
        }
        if (compareX) {
            if (rect.xmin() <= p.x() && n.left != null) {
                findRange(rect, n.left, s, false);
            }
            if (rect.xmax() >= p.x() && n.right != null) {
                findRange(rect, n.right, s, false);
            }
        } else {
            if (rect.ymin() <= p.y() && n.left != null) {
                findRange(rect, n.left, s, true);
            }
            if (rect.ymax() >= p.y() && n.right != null) {
                findRange(rect, n.right, s, true);
            }
        }

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        List<Point2D> list = new ArrayList<>();
        if (root != null) {
            findRange(rect, root, list, true);
        }
        return list;
    }

    // all points that are inside the rectangle
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        double dist = Double.MAX_VALUE;
        Point2D nearestP = null;

        if (root != null) {
            nearestP = near(root, p, true, dist, null);
        }
        return nearestP;
    }      // a nearest neighbor in the set to point p; null if the set is empty

    private Point2D near(Node n, Point2D p, boolean compareX, double oldDistance, Point2D oldPoint) {

//  double dictForComparing=dist;
        Point2D pointN = n.val;
        double dist = pointN.distanceTo(p);
        Point2D pointNew = null;

        if (dist < oldDistance) {
            pointNew = pointN;
        } else {
            dist = oldDistance;
            pointNew = oldPoint;
        }
//        Point2D pointNew1 = null, pointNew2 = null;
        if (compareX) {
            if (p.x() < pointN.x() && n.left != null) {
                pointNew = near(n.left, p, false, dist, pointNew);
//                if (n.right != null) {
//                    pointNew2 = near(n.right, p, false, dist, pointNew);
//                }

                if (pointNew.distanceTo(p) >= dist && n.right != null) {
                    pointNew = near(n.right, p, false, dist, pointNew);
                }
            } else if (n.right != null) {
                pointNew = near(n.right, p, false, dist, pointNew);
//                if (n.left != null) {
//                    pointNew2 = near(n.left, p, false, dist, pointNew);
//                }
                if (pointNew.distanceTo(p) >= dist && n.left != null) {
                    pointNew = near(n.left, p, false, dist, pointNew);
                }
            }
        } else {
            if (p.y() < pointN.y() && n.left != null) {
                pointNew = near(n.left, p, true, dist, pointNew);
//                if (n.right != null) {
//                    pointNew2 = near(n.right, p, true, dist, pointNew);
//                }
                if (pointNew.distanceTo(p) >= dist && n.right != null) {
                    pointNew = near(n.right, p, true, dist, pointNew);
                }
            } else if (n.right != null) {
                pointNew = near(n.right, p, true, dist, pointNew);
//                if (n.left != null) {
//                    pointNew2 = near(n.left, p, true, dist, pointNew);
//                }
                if (pointNew.distanceTo(p) >= dist && n.left != null) {
                    pointNew = near(n.left, p, true, dist, pointNew);
                }
            }

        }
//        if(pointNew1 == null && pointNew2 == null){
//            return pointNew;
//        }else if (pointNew1 == null) {
//            pointNew = pointNew2;
//        } else if (pointNew2 == null) {
//            pointNew = pointNew1;
//        }else if(pointNew1.distanceTo(p) < pointNew2.distanceTo(p)) {
//            pointNew = pointNew1;
//        } else {
//            pointNew = pointNew2;
//        }
        return pointNew;
    }


    public static void main(String[] args) {
    }
}
