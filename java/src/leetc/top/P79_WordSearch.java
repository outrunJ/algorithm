package leetc.top;

public class P79_WordSearch {
    private static boolean process(char[][] b, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
            return false;
        }
        if (b[i][j] != w[k]) {
            return false;
        }
        char tmp = b[i][j];
        b[i][j] = 0;
        boolean ans = process(b, i - 1, j, w, k + 1)
                || process(b, i + 1, j, w, k + 1)
                || process(b, i, j - 1, w, k + 1)
                || process(b, i, j + 1, w, k + 1);
        b[i][j] = tmp;
        return ans;
    }
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
