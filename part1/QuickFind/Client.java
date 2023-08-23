import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Client {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFind qf = new QuickFind(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!qf.connected(p, q)) {
                qf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
