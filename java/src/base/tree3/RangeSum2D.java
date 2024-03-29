package base.tree3;

// https://leetcode.com/problems/range-sum-query-2d-mutable
public class RangeSum2D {
    private int[][] tree;
    private int[][] nums;
    private int n;
    private int m;

    public  RangeSum2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        n = matrix.length;
        m = matrix[0].length;
        tree = new int[n + 1][m + 1];
        nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & -i) {
            for (int j = col + 1; j > 0; j -= j & -j) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    public void update(int row, int col, int val) {
        if (n == 0 || m == 0) {
            return;
        }
        int add = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= n; i += i & -i) {
            for (int j = col + 1; j <= m; j += j & -j) {
                tree[i][j] += add;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (n == 0 || m == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }
}
