
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
    private static int top;
    private static int bottom;
    private static int number;
    private static int[][] state;
    WeightedQuickUnionUF wquf;

    // creates n-by-n grid, with all sites initially blocked
    // 初始化一个 n * n 网格，所有的节点都设置为 blocked
    public Percolation(int n) {
        top = n * n + 1;
        bottom = n * n + 2;
        wquf = new WeightedQuickUnionUF(n * n + 2);
        number = 0;
        state = new int[n][n];
    }

    // opens the site (row, col) if it is not open already
    // 根据 行和列 设置某个节点为 open状态，
    public void open(int row, int col) {
        if (!this.isOpen(row, col)) {
            state[row][col] = 1;
            this.updateNumberOfOpenSites();
        }
    }

    // is the site (row, col) open?
    // 查看某个节点 是不是 open 状态
    public boolean isOpen(int row, int col) {
        return state[row][col] == 1;
    }

    //
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
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
        return this.wquf.find(top) == this.wquf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int N = 10;
        Percolation pl = new Percolation(10);

        while (!pl.percolates()) {
            int i = StdRandom.uniformInt(1, N + 1);
            int j = StdRandom.uniformInt(1, N + 1);
            System.out.println(i);
            System.out.println(j);
            if (!pl.isOpen(i, j)) {
                pl.open(i, j);
                number += 1;
            }
        }

        System.out.println(pl.numberOfOpenSites());
    }
}
