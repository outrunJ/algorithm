package base.print;

// Z字打印
public class ZigZagMatrix {
    private static void printPath(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR <= dR) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR >= tR) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }
    public static void zigzag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR <= endR) {
            printPath(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        zigzag(matrix);
    }
}
