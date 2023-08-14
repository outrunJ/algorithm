package leetc.top;

import java.util.PriorityQueue;

public class P407_TrappingRainWaterII {
    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] isEnter = new boolean[n][m];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int col = 0; col < m - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < n - 1; row++) {
            isEnter[row][m - 1] = true;
            heap.add(new Node(heightMap[row][m - 1], row, m - 1));
        }
        for (int col = m - 1; col > 0; col--) {
            isEnter[n - 1][col] = true;
            heap.add(new Node(heightMap[n - 1][col], n - 1, col));
        }
        for (int row = n - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }
        int water = 0, max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row, c = cur.col;
            if (r > 0 && !isEnter[r - 1][c]) {
                water += Math.max(0, max - heightMap[r - 1][c]);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            if (r < n - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < m - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }
}
