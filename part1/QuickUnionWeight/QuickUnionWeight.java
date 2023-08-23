import java.util.Arrays;

public class QuickUnionWeight {

    private int[] id;
    private int[] sz;

    public QuickUnionWeight(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
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
        int i = root(p); // 6
        int j = root(q); // 0
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) { // 2 1
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args) {
        QuickUnionWeight quw = new QuickUnionWeight(10);
        quw.union(4, 3);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(3, 8);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(6, 5);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(9, 4);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(2, 1);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(5, 0);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(7, 2);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        quw.union(6, 1);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");
        System.out.println(quw.connected(7, 3));
        quw.union(7, 3);
        System.out.println(Arrays.toString(quw.id));
        System.out.println(Arrays.toString(quw.sz));
        System.out.println("--------");

    }
}
