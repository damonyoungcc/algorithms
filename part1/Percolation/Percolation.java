
/**
 * 思路
 * 创建一个N * N的网格，和一个N * N的状态记录数组 网格每个site值为索引， 状态数组初始状态都置为 0
 * 创建两个属性 top 和 bottom，top 值为 -1 bottom值为 N * N + 1，状态都标记为 1
 * 随机将 某个 site 的状态 设为 1，随机取 r c 的值，判断这个 site状态是不是 1，true什么都不变 false 就设为1
 * 设为 1 后，就将获取它 上下左右的四个 site，判断是不是状态是不是 1，是 1，则把该site和这个site进行 union
 * 如果是 r =1 第一行，则不设置top，如果是 r = N - 1行不设置bottom
 * 每次设置后，都判断一下 -1 和 N * N是否渗透
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int top; // top 虚位，当第一行有 site 状态变为 1时，将top 和 它进行 union 操作
    private final int bottom; // bottom 虚位，当最后一行有 site 状态变为 1时，将top 和 它进行 union 操作
    private int number; // 当有 site 状态变为 1时，将 number + 1
    private final boolean[][] state; // 初始化一个二维数组 row col 记录每个 site 的状态
    private final WeightedQuickUnionUF wquf;
    private final int size; // 记录初始化的 N 的值

    // creates n-by-n grid, with all sites initially blocked
    // 初始化一个 n * n 网格，所有的节点都设置为 blocked
    public Percolation(int n) {

        // n 个值，网格第一个元素到最后一个元素是 【0, n * n - 1】
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n must be greater than zero");
        }
        top = n * n; // 在网络的数字后面再加两位 n * n 为 top， 默认都是开启状态
        bottom = n * n + 1; // 在网络的数字后面再加两位 n * n + 1 为 bottom 默认都是开启状态
        wquf = new WeightedQuickUnionUF(n * n + 2);
        number = 0; // 记录渗透后site状态为开启的个数
        state = new boolean[n][n]; // 记录每个site的状态的二元 boolean 数组
        size = n; // 记录下初始化的n的值
    }

    // opens the site (row, col) if it is not open already
    // 根据 行和列 设置某个节点为 open状态，
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        // 随机选中一个site，首先要判断这个 site是不是开启状态，不是开启状态再执行，是开启状态则不执行
        if (!this.isOpen(row, col)) {
            setSiteOpen(row, col);
            updateNumberOfOpenSites();
            int topSite = getIndexByRowAndCol(row - 1, col);
            int rightSite = getIndexByRowAndCol(row, col + 1);
            int bottomSite = getIndexByRowAndCol(row + 1, col);
            int leftSite = getIndexByRowAndCol(row, col - 1);

            int currentSite = getIndexByRowAndCol(row, col);
            // 先判断随机的 site 是不是第一行和最后一行，是就先和 top 和 bottom 进行union操作，再和上下左右进行操作

            // 左上角 右上角 右下角 左下角 第一行 最后一行 第一列 最后一列
            // 左上角
            if (row == 1 && col == 1) {
                union(currentSite, top);
                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, currentSite);
                }
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, currentSite);
                }
                // 右上角
            } else if (row == 1 && col == size) {
                union(currentSite, top);
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, bottomSite);
                }
                // 右下角
            } else if (row == size && col == size) {
                union(currentSite, bottom);
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }
                // 左下角
            } else if (row == size && col == 1) {
                union(currentSite, bottom);

                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, rightSite);
                }
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
            } else if (row == 1) {
                union(currentSite, top);
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, bottomSite);
                }
                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, rightSite);
                }
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }

            } else if (row == size) {
                union(currentSite, bottom);
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, rightSite);
                }
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }

            } else if (col == 1) {
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, rightSite);
                }
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, bottomSite);
                }
            } else if (col == size) {
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, bottomSite);
                }
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }

            } else {
                // top
                if (isOpen(row - 1, col)) {
                    union(currentSite, topSite);
                }
                // bottom
                if (isOpen(row + 1, col)) {
                    union(currentSite, bottomSite);
                }
                // left
                if (isOpen(row, col - 1)) {
                    union(currentSite, leftSite);
                }

                // right
                if (isOpen(row, col + 1)) {
                    union(currentSite, rightSite);
                }

            }
            // 不是第一行或者最后一行判断他的上下左右有没有开启状态的，有就进行union操作
        }
    }

    // 设置某个site为开启状态
    private void setSiteOpen(int row, int col) {
        state[row - 1][col - 1] = true;
    }

    private void union(int source, int target) {
        wquf.union(source, target);
    }

    private int getIndexByRowAndCol(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    // is the site (row, col) open?
    // 查看某个节点 是不是 open 状态
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        return state[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int currentSiteIndex = getIndexByRowAndCol(row, col);
        return isOpen(row, col) && wquf.find(currentSiteIndex) == wquf.find(top);
    }

    // returns the number of open sites
    // 返回有多少个 节点是 open状态
    public int numberOfOpenSites() {
        return number;
    }

    private void updateNumberOfOpenSites() {
        number++;
    }

    // does the system percolate?
    // 系统是否是渗透
    public boolean percolates() {
        return wquf.find(top) == wquf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        Percolation pl = new Percolation(n);

        while (!pl.percolates()) {
            int i = StdRandom.uniformInt(1, n + 1);
            int j = StdRandom.uniformInt(1, n + 1);
            pl.open(i, j);
        }

        System.out.println(pl.numberOfOpenSites());
    }
}
