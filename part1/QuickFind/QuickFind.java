import java.util.Arrays;

public class QuickFind {

    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];

    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);
        qf.union(4, 3);
        System.out.println(Arrays.toString(qf.id));
        qf.union(3, 5);
        System.out.println(Arrays.toString(qf.id));
        qf.union(5, 6);
        System.out.println(Arrays.toString(qf.id));
        System.out.println(qf.connected(1, 9));
        System.out.println(qf.connected(5, 6));
    }
}
