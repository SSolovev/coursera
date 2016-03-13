import edu.princeton.cs.algs4.StdIn;

/**
 * Created by sergey on 3/13/16.
 */
public class Subset {


    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        int i = Integer.parseInt(args[0]);
        String s = StdIn.readLine();
//        String[] arr = null;
        if (s != null && s.split(" ").length > 0) {
            String[] arr = s.split(" ");
            RandomizedQueue<String> queue = new RandomizedQueue<String>();
            for (String val : arr) {
                queue.enqueue(val);
            }
            if (arr.length < i) {
                i = arr.length;
            }
            for (int j = 0; j < i; j++) {

                System.out.println(queue.dequeue());
            }
        } else {
            System.out.println(s);
        }


//
//        for (int i = 0; i < 15; i++) {
//            queue.enqueue(i);
//        }
    }
}
