package leetc.top;

public class P130_SurroundedRegions {
    private static void can(char[][] board, int i, int j, boolean[] ans) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            ans[0] = false;
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '.';
            can(board, i - 1, j, ans);
            can(board, i + 1, j, ans);
            can(board, i, j - 1, ans);
            can(board, i, j + 1, ans);
        }
    }

    private static void change(char[][] board, int i, int j, char can) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return;
        }
        if (board[i][j] == '.') {
            board[i][j] = can == 'T' ? 'X' : 'O';
            change(board, i - 1, j, can);
            change(board, i + 1, j, can);
            change(board, i, j - 1, can);
            change(board, i, j + 1, can);
        }
    }

    public static void solve1(char[][] board) {
        boolean[] ans = new boolean[1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    ans[0] = true;
                    can(board, i, j, ans);
                    board[i][j] = ans[0] ? 'T' : 'F';
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char can = board[i][j];
                if (can == 'T' || can == 'F') {
                    board[i][j] = '.';
                    change(board, i, j, can);
                }
            }
        }
    }

    //

    private static void free(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'F';
        free(board, i + 1, j);
        free(board, i - 1, j);
        free(board, i, j + 1);
        free(board, i, j - 1);
    }

    public static void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int n = board.length, m = board[0].length;
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') {
                free(board, 0, j);
            }
            if (board[n - 1][j] == 'O') {
                free(board, n - 1, j);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            if (board[i][m - 1] == 'O') {
                free(board, i, m - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
