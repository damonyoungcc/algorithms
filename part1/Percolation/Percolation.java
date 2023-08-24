public class Percolation {
    int[] id;

    // creates n-by-n grid, with all sites initially blocked
    // 初始化一个 n * n 网格，所有的节点都设置为 blocked
    public Percolation(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = 0;
        }
    }

    // opens the site (row, col) if it is not open already
    // 根据 行和列 设置某个节点为 open状态，
    public void open(int row, int col) {

    }

    // is the site (row, col) open?
    // 查看某个节点 是不是 open 状态
    public boolean isOpen(int row, int col) {
        return false;

    }

    //
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;

    }

    // returns the number of open sites
    // 返回有多少个 节点是 open状态
    public int numberOfOpenSites() {
        return 0;

    }

    // does the system percolate?
    // 系统是否是渗透
    public boolean percolates() {
        return false;

    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
