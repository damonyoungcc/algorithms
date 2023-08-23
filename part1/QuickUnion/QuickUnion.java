import java.util.Arrays;

public class QuickUnion {

    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // 回溯到最上层的父节点
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // 判断是否有同样的最上层父节点
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public static void main(String[] args) {
        QuickUnion qf = new QuickUnion(10);
        System.out.println(Arrays.toString(qf.id));
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
