package giant.c39;

// 来自京东
// 给定一个二维数组matrix，matrix[i][j] = k代表:
// 从(i,j)位置可以随意往右跳<=k步，或者从(i,j)位置可以随意往下跳<=k步
// 如果matrix[i][j] = 0，代表来到(i,j)位置必须停止
// 返回从matrix左上角到右下角，至少要跳几次
// 已知matrix中行数n <= 5000, 列数m <= 5000
// matrix中的值，<= 5000
public class JumpGameOnMatrix {
    private static int process(int[][] map, int row, int col) {
        if (row == map.length - 1 && col == map[0].length - 1) {
            return 0;
        }
        if (map[row][col] == 0) {
            return Integer.MAX_VALUE;
        }
        int next = Integer.MAX_VALUE;
        for (int down = row + 1; down < map.length && (down - row) <= map[row][col]; down++) {
            next = Math.min(next, process(map, down, col));
        }
        for (int right = col + 1; right < map[0].length && (right - col) <= map[row][col]; right++) {
            next = Math.min(next, process(map, row, right));
        }
        return next != Integer.MAX_VALUE ? (next + 1) : next;
    }

    public static int jump1(int[][] map) {
        return process(map, 0, 0);
    }

    //

    private static class SegmentTree {
        private int[] min;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int n = size + 1;
            min = new int[n << 2];
            change = new int[n << 2];
            update = new boolean[n << 2];
            update(1, size, Integer.MAX_VALUE, 1, size, 1);
        }

        private void pushUp(int rt) {
            min[rt] = Math.min(min[rt << 1], min[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                min[rt << 1] = change[rt];
                min[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }

        public void update(int tl, int tr, int tc, int l, int r, int rt) {
            if (tl <= l && r <= tr) {
                update[rt] = true;
                change[rt] = tc;
                min[rt] = tc;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (tl <= mid) {
                update(tl, tr, tc, l, mid, rt << 1);
            }
            if (tr > mid) {
                update(tl, tr, tc, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int tl, int tr, int l, int r, int rt) {
            if (tl <= l && r <= tr) {
                return min[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (tl <= mid) {
                left = query(tl, tr, l, mid, rt << 1);
            }
            if (tr > mid) {
                right = query(tl, tr, mid + 1, r, rt << 1 | 1);
            }
            return Math.min(left, right);
        }
    }

    public static int jump2(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] map = new int[n + 1][m + 1];
        for (int a = 0, b = 1; a < n; a++, b++) {
            for (int c = 0, d = 1; c < m; c++, d++) {
                map[b][d] = arr[a][c];
            }
        }
        SegmentTree[] rowTrees = new SegmentTree[n + 1];
        for (int i = 1; i <= n; i++) {
            rowTrees[i] = new SegmentTree(m);
        }
        SegmentTree[] colTrees = new SegmentTree[m + 1];
        for (int i = 1; i <= m; i++) {
            colTrees[i] = new SegmentTree(n);
        }
        rowTrees[n].update(m, m, 0, 1, m, 1);
        colTrees[m].update(n, n, 0, 1, n, 1);
        for (int col = m - 1; col >= 1; col--) {
            if (map[n][col] != 0) {
                int left = col + 1;
                int right = Math.min(col + map[n][col], m);
                int next = rowTrees[n].query(left, right, 1, m, 1);
                if (next != Integer.MAX_VALUE) {
                    rowTrees[n].update(col, col, next + 1, 1, m, 1);
                    colTrees[col].update(n, n, next + 1, 1, n, 1);
                }
            }
        }
        for (int row = n - 1; row >= 1; row--) {
            if (map[row][m] != 0) {
                int up = row + 1;
                int down = Math.min(row + map[row][m], n);
                int next = colTrees[m].query(up, down, 1, n, 1);
                if (next != Integer.MAX_VALUE) {
                    rowTrees[row].update(m, m, next + 1, 1, m, 1);
                    colTrees[m].update(row, row, next + 1, 1, n, 1);
                }
            }
        }
        for (int row = n - 1; row >= 1; row--) {
            for (int col = m - 1; col >= 1; col--) {
                if (map[row][col] != 0) {
                    int left = col + 1;
                    int right = Math.min(col + map[row][col], m);
                    int next1 = rowTrees[row].query(left, right, 1, m, 1);
                    int up = row + 1;
                    int down = Math.min(row + map[row][col], n);
                    int next2 = colTrees[col].query(up, down, 1, n, 1);
                    int next = Math.min(next1, next2);
                    if (next != Integer.MAX_VALUE) {
                        rowTrees[row].update(col, col, next + 1, 1, m, 1);
                        colTrees[col].update(row, row, next + 1, 1, n, 1);
                    }
                }
            }
        }
        return rowTrees[1].query(1, 1, 1, m, 1);
    }

    //

    private static int[][] randomMatrix(int n, int m, int v) {
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (int) (Math.random() * v);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int times = 10000;
        int len = 10;
        int val = 8;
        System.out.println("test begin");
        for (int i = 0; i < times; i++) {
            int n = (int) (Math.random() * len) + 1;
            int m = (int) (Math.random() * len) + 1;
            int[][] map = randomMatrix(n, m, val);
            int ans1 = jump1(map);
            int ans2 = jump2(map);
            if (ans1 != ans2) {
                System.out.println("Wrong");
            }
        }
        System.out.println("test end");
    }
}
