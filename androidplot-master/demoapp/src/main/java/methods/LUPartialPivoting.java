/**
 * @author Hassler Castro Cuesta - Joshua Sanchez Alvarez- Edwin Rengifo Villa -
 *         Camilo Villa Restrepo
 */
public class LUPartialPivoting {

    private double[][] L;
    private double[][] U;
    private double[][][] result;

    /**
     * @return the l
     */
    public double[][] getL() {
        return L;
    }

    /**
     * @return the u
     */
    public double[][] getU() {
        return U;
    }

    /**
     * @return the result
     */
    public double[][][] getResult() {
        return result;
    }

    public LUPartialPivoting(double[][] A, double[] b){
        this.result = LUPartialPivotingM(A);
        this.L = this.result[0];
        this.U = this.result[1];
    }

    // public static void main(String[] args) {
    //     double[][] A = { { -7, 2, -3, 4 }, { 5, -1, 14, -1 }, { 1, 9, -7, 5 }, { -12, 13, -8, -4 } };
    //     double[] b = { -12, 13, 31, -32 };
    //     LUPartialPivoting luPartialPivoting = new LUPartialPivoting(A, b);
    //     printMatrix(luPartialPivoting.getL());
    //     printMatrix(luPartialPivoting.getU());
    // }

    public static double[][][] LUPartialPivotingM(double[][] A) {
        double[][] L = new double[A.length][A.length];
        double[][] U = new double[A.length][A.length];
        double[][][] res = new double[3][A.length][A.length];
        double[][][] res2 = new double[3][A.length][A.length];
        int n = A.length;
        double mult;
        double[][] marks = new double[A.length][A.length];
        for (int i = 0; i < marks.length; i++) {
            marks[i][i] = 1;
        }
        for (int k = 0; k < n; k++) {

            res2 = partialPivoting(A, n, k, marks, L);
            A = res2[0];
            marks = res2[1];
            L = res2[2];

            for (int i = k + 1; i < n; i++) {
                mult = A[i][k] / A[k][k];
                L[i][k] = mult;
                for (int j = k; j < n; j++) {
                    A[i][j] = (A[i][j] - (mult * A[k][j]));
                }
            }
            for (int j = 0; j < A.length; j++) {
                U[k][j] = A[k][j];
            }
        }
        for (int i = 0; i < L.length; i++) {
            L[i][i] = 1;
        }
        res[0] = L;
        res[1] = U;
        res[2] = marks;
        return res;
    }

    public static double[][][] partialPivoting(double[][] Ab, int n, int k, double[][] marks, double[][] L) {
        double[][][] result = new double[3][Ab.length][Ab.length];
        double max = Math.abs(Ab[k][k]);
        int maxRow = k;
        for (int s = k + 1; s < n; s++) {
            if (Math.abs(Ab[s][k]) > max) {
                max = Math.abs(Ab[s][k]);
                maxRow = s;
            }
        }
        if (max == 0) {
            System.out.println("The system doesn't have solution");
            return result;
        } else {
            if (maxRow != k) {
                Ab = swapRows(Ab, maxRow, k);
                marks = swapRows(marks, maxRow, k);
                L = swapRows(L, maxRow, k);

            }
        }
        result[0] = Ab;
        result[1] = marks;
        result[2] = L;

        return result;
    }

    public static void printMatrix(double[][] M) {
        System.out.println("\n");
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println("");
    }

    public static void printArray(double[] M) {
        System.out.println("");
        for (int i = 0; i < M.length; i++) {
            System.out.print("x" + i + " ");
            System.out.println(M[i]);

        }
    }

    public static void mult(double[][] A, double[][] B) {

        double[][] result = new double[A.length][B[0].length];
        if (A[0].length != B.length) {
            System.out.println("Dimension Error");
            return;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }

            }
        }
    }

    public static double[][] swapRows(double[][] M, int row1, int row2) {
        if (row1 > M.length - 1 || row2 > M.length - 1) {
            System.err.println("Error, can't swap that rows");
            System.exit(0);
        } else {
            for (int i = 0; i < M[0].length; i++) {
                double aux = 0;
                aux = M[row1][i];
                M[row1][i] = M[row2][i];
                M[row2][i] = aux;
            }
        }
        return M;
    }

    public static double[][] swapColumns(double[][] M, int col1, int col2) {
        if (col1 > M[0].length - 1 || col2 > M[0].length - 1) {
            System.err.println("Error, can't swap that columns");
            System.exit(0);
        } else {
            for (int i = 0; i < M.length; i++) {
                double aux = 0;
                aux = M[i][col1];
                M[i][col1] = M[i][col2];
                M[i][col2] = aux;
            }
        }
        return M;
    }

    public static double[][] augmentedMatrix(double[][] A, double[] b) {
        double[][] Ab = new double[A.length][A[0].length + 1];
        if (A.length != b.length) {
            System.out.println("Error, can't build the augmented matrix. Wrong dimesions");
            System.exit(0);
            return Ab;
        } else {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    Ab[i][j] = A[i][j];
                }
            }
            for (int i = 0; i < Ab.length; i++) {
                Ab[i][Ab[0].length - 1] = b[i];
            }
            return Ab;
        }
    }

    public static double[] regressiveSubstitution(double[][] Ab, int n) {
        
        double[] x = new double[n + 1];
        x[n] = Ab[n][n + 1] / Ab[n][n];
        double sumatoria = 0;

        for (int i = n - 1; i >= 0; i--) {
            sumatoria = 0;
            for (int p = i + 1; p <= n; p++) {
                sumatoria += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][n + 1] - sumatoria) / Ab[i][i];
        }
        return x;
    }

    public static double[] progressiveSubstitution(double[][] Ab, int n) {
        
        double[] x = new double[n + 1];
        x[0] = Ab[0][n + 1] / Ab[0][0];
        double sumatoria = 0;

        for (int i = 1; i <= n; i++) {
            sumatoria = 0;
            for (int p = 0; p <= i; p++) {
                sumatoria += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][n + 1] - sumatoria) / Ab[i][i];
        }
        return x;
    }

    public static double[] multVector(double[][] A, double[] B) {

        double[][] result = new double[A.length][B.length];
        double[] DefResult = new double[B.length];

        if (A.length != B.length) {
            System.out.println("Dimension Error");
            return DefResult;
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k];
                }

            }
        }
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                DefResult[i] = result[i][j];
            }
        }
        return DefResult;
    }
}