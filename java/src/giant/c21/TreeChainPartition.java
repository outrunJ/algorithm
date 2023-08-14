package giant.c21;

import java.util.HashMap;

public class TreeChainPartition {
    private static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int[] origin) {
            MAXN = origin.length;
            arr = origin;
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void add(int tl, int tr, int tc, int l, int r, int rt) {
            if (tl <= l && r <= tr) {
                sum[rt] += tc * (r - l + 1);
                lazy[rt] += tc;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (tl <= mid) {
                add(tl, tr, tc, l, mid, rt << 1);
            }
            if (tr > mid) {
                add(tl, tr, tc, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int tl, int tr, int l, int r, int rt) {
            if (tl <= l && r <= tr) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (tl <= mid) {
                ans += query(tl, tr, l, mid, rt << 1);
            }
            if (tr > mid) {
                ans += query(tl, tr, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }

    public static class TreeChain {
        // 时间戳, dfs序
        private int tim;
        // 节点数
        private int n;
        // head
        private int h;
        // [节点][子节点]
        private int[][] tree;
        // [原坐标]=值
        private int[] val;
        // [原坐标]=父坐标
        private int[] fa;
        // 深度
        private int[] dep;
        // [原坐标]=重儿子坐标
        private int[] son;
        // 节点大小
        private int[] siz;
        // [原坐标]=重链头坐标
        private int[] top;
        // [原坐标]=dfs坐标
        private int[] dfn;
        // [dfs坐标]=值
        private int[] tnw;
        private SegmentTree seg;

        // val, h, tree
        private void initTree(int[] father, int[] values) {
            tim = 0;
            n = father.length + 1;
            tree = new int[n][];
            val = new int[n];
            fa = new int[n];
            dep = new int[n];
            son = new int[n];
            siz = new int[n];
            top = new int[n];
            dfn = new int[n];
            tnw = new int[n--];
            int[] help = new int[n];
            for (int i = 0; i < n; i++) {
                val[i + 1] = values[i];
            }
            for (int i = 0; i < n; i++) {
                if (father[i] == i) {
                    h = i + 1;
                } else {
                    help[father[i]]++;
                }
            }
            tree[0] = new int[0];
            for (int i = 0; i < n; i++) {
                tree[i + 1] = new int[help[i]];
            }
            for (int i = 0; i < n; i++) {
                if (i + 1 != h) {
                    tree[father[i] + 1][--help[father[i]]] = i + 1;
                }
            }
        }

        // u 当前节点, f 父节点
        // fa, dep, siz, son
        private void dfs1(int u, int f) {
            fa[u] = f;
            dep[u] = dep[f] + 1;
            siz[u] = 1;
            int maxSize = -1;
            for (int v : tree[u]) {
                dfs1(v, u);
                siz[u] += siz[v];
                if (siz[v] > maxSize) {
                    maxSize = siz[v];
                    son[u] = v;
                }
            }
        }

        // u 当前节点, t 重链头
        // dfn, top, tnw
        private void dfs2(int u, int t) {
            dfn[u] = ++tim;
            top[u] = t;
            tnw[tim] = val[u];
            if (son[u] != 0) {
                dfs2(son[u], t);
                for (int v : tree[u]) {
                    if (v != son[u]) {
                        dfs2(v, v);
                    }
                }
            }
        }

        public TreeChain(int[] father, int[] values) {
            initTree(father, values);
            dfs1(h, 0);
            dfs2(h, h);
            seg = new SegmentTree(tnw);
            seg.build(1, n, 1);
        }

        // head节点值+val, 只用dfs序不用重链
        public void addSubtree(int head, int val) {
            head++;
            seg.add(dfn[head], dfn[head] + siz[head] - 1, val, 1, n, 1);
        }

        public int querySubtree(int head) {
            head++;
            return seg.query(dfn[head], dfn[head] + siz[head] - 1, 1, n, 1);
        }

        // a到b区间+v
        public void addChain(int a, int b, int v) {
            a++;
            b++;
            while (top[a] != top[b]) {
                if (dep[top[a]] > dep[top[b]]) {
                    seg.add(dfn[top[a]], dfn[a], v, 1, n, 1);
                    a = fa[top[a]];
                } else {
                    seg.add(dfn[top[b]], dfn[b], v, 1, n, 1);
                    b = fa[top[b]];
                }
            }
            if (dep[a] > dep[b]) {
                seg.add(dfn[b], dfn[a], v, 1, n, 1);
            } else {
                seg.add(dfn[a], dfn[b], v, 1, n, 1);
            }
        }

        public int queryChain(int a, int b) {
            a++;
            b++;
            int ans = 0;
            while (top[a] != top[b]) {
                if (dep[top[a]] > dep[top[b]]) {
                    ans += seg.query(dfn[top[a]], dfn[a], 1, n, 1);
                    a = fa[top[a]];
                } else {
                    ans += seg.query(dfn[top[b]], dfn[b], 1, n, 1);
                    b = fa[top[b]];
                }
            }
            if (dep[a] > dep[b]) {
                ans += seg.query(dfn[b], dfn[a], 1, n, 1);
            } else {
                ans += seg.query(dfn[a], dfn[b], 1, n, 1);
            }
            return ans;
        }


    }

    //

    public static class Right {
        private int n;
        private int[][] tree;
        private int[] fa;
        private int[] val;
        private HashMap<Integer, Integer> path;

        public Right(int[] father, int[] value) {
            n = father.length;
            tree = new int[n][];
            fa = new int[n];
            val = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = father[i];
                val[i] = value[i];
            }
            int[] help = new int[n];
            int h = 0;
            for (int i = 0; i < n; i++) {
                if (father[i] == i) {
                    h = i;
                } else {
                    help[father[i]]++;
                }
            }
            for (int i = 0; i < n; i++) {
                tree[i] = new int[help[i]];
            }
            for (int i = 0; i < n; i++) {
                if (i != h) {
                    tree[father[i]][--help[father[i]]] = i;
                }
            }
            path = new HashMap<>();
        }

        public void addSubtree(int head, int value) {
            val[head] += value;
            for (int next : tree[head]) {
                addSubtree(next, value);
            }
        }

        public int querySubtree(int head) {
            int ans = val[head];
            for (int next : tree[head]) {
                ans += querySubtree(next);
            }
            return ans;
        }

        public void addChain(int a, int b, int v) {
            path.clear();
            path.put(a, null);
            while (a != fa[a]) {
                path.put(fa[a], a);
                a = fa[a];
            }
            while (!path.containsKey(b)) {
                val[b] += v;
                b = fa[b];
            }
            val[b] += v;
            while (path.get(b) != null) {
                b = path.get(b);
                val[b] += v;
            }
        }

        public int queryChain(int a, int b) {
            path.clear();
            path.put(a, null);
            while (a != fa[a]) {
                path.put(fa[a], a);
                a = fa[a];
            }
            int ans = 0;
            while (!path.containsKey(b)) {
                ans += val[b];
                b = fa[b];
            }
            ans += val[b];
            while (path.get(b) != null) {
                b = path.get(b);
                ans += val[b];
            }
            return ans;
        }
    }

    //

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] generateFatherArray(int n) {
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(order, i, (int) (Math.random() * (i + 1)));
        }
        int[] ans = new int[n];
        ans[order[0]] = order[0];
        for (int i = 1; i < n; i++) {
            ans[order[i]] = order[(int) (Math.random() * i)];
        }
        return ans;
    }

    public static int[] generateValueArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 50000;
        int maxVal = 100000;
        int times = 1000000;
        int[] father = generateFatherArray(maxLen);
        int[] values = generateValueArray(maxLen, maxVal);
        TreeChain tc = new TreeChain(father, values);
        Right right = new Right(father, values);
        System.out.println("test begin");
        for (int i = 0; i < times; i++) {
            double decision = Math.random();
            if (decision < 0.25) {
                int head = (int) (Math.random() * maxLen);
                int value = (int)(Math.random() * maxVal);
                tc.addSubtree(head, value);
                right.addSubtree(head, value);
            } else if (decision < 0.5) {
                int head = (int) (Math.random() * maxLen);
                if (tc.querySubtree(head) != right.querySubtree(head)) {
                    System.out.println("Wrong1");
                }
            } else if (decision < 0.75) {
                int a = (int) (Math.random() * maxLen);
                int b = (int) (Math.random() * maxLen);
                int value = (int) (Math.random() * maxVal);
                tc.addChain(a, b, value);
                right.addChain(a, b, value);
            } else {
                int a = (int) (Math.random() * maxLen);
                int b = (int) (Math.random() * maxLen);
                if (tc.queryChain(a, b) != right.queryChain(a, b)) {
                    System.out.println("Wrong2");
                }
            }
        }
        System.out.println("test end");
    }
}
