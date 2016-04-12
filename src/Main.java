import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

  public static void main(String[] args) {
//Date d =new Date();
//    GregorianCalendar.
    GregorianCalendar start = new GregorianCalendar(2016, 0, 29);
    GregorianCalendar end = new GregorianCalendar(2016, 3, 28);
    System.out.println(daysBetween(start.getTime(), end.getTime()));
  }

  public static int daysBetween(Date d1, Date d2) {
    return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
  }

//  public static void fill(Connection c, String val) {
//
//  }

  public static void p(int N) {
    int sum = 0;
    for (int i = 1; i <= N * N * N; i++)
      for (int j = i + 1; j <= N; j++)
        sum++;

    System.out.println(sum);
  }
}
