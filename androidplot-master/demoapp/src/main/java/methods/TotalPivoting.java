/**
 * @author Hassler Castro Cuesta - Joshua Sanchez Alvarez- Edwin Rengifo Villa -
 *         Camilo Villa Restrepo
 */
public class TotalPivoting {

    static int[] marks;
    private double[][] elimination;
    private double[] solution;

    /**
     * @return the elimination
     */
    public double[][] getElimination() {
        return elimination;
    }

    /**
     * @return the solution
     */
    public double[] getSolution() {
        return solution;
    }

    public TotalPivoting(double[][] A, double[] b) {
        this.elimination = gaussianEliminationTotalPivoting(A, b, A.length);
        this.solution = regressiveSubstitution(this.elimination, A.length - 1);
    }

    // public static void main(String[] args) {
    //     double[][] A = { { -7, 2, -3, 4 }, { 5, -1, 14, -1 }, { 1, 9, -7, 13 }, { -12, 13, -8, -4 } };
    //     double[] b = { -12, 13, 31, -32 };
    //     TotalPivoting totalPivoting = new TotalPivoting(A, b);
    //     printMatrix(totalPivoting.getElimination());
    //     printArray(totalPivoting.getSolution());
    // }

    public static double[][] gaussianEliminationTotalPivoting(double[][] A, double[] b, int n) {
        marks = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            marks[i] = i + 1;
        }
        double[][] Ab = augmentedMatrix(A, b);
        double mult;
        for (int k = 0; k < n - 1; k++) {
            Ab = totalPivoting(Ab, n, k);
            for (int i = k + 1; i < n; i++) {
                mult = Ab[i][k] / Ab[k][k];
                for (int j = k; j < n + 1; j++) {
                    Ab[i][j] = (Ab[i][j] - (mult * Ab[k][j]));
                }
            }
        }
        return Ab;
    }

    public static double[][] totalPivoting(double[][] Ab, int n, int k) {
        double max = 0;
        int maxRow = k;
        int maxCol = k;
        for (int r = k; r < n; r++) {
            Ab = putZeros(Ab);
            for (int s = k; s < n; s++) {
                if (Math.abs(Ab[r][s]) > max) {
                    max = Math.abs(Ab[r][s]);
                    maxRow = r;
                    maxCol = s;
                }
            }
        }
        if (max == 0) {
            System.out.println("The system doesn't have solution");
            return Ab;
        } else {
            if (maxRow != k) {
                Ab = swapRows(Ab, maxRow, k);
            }
            if (maxCol != k) {
                Ab = swapColumns(Ab, maxCol, k);
                int aux;
                aux = marks[maxCol];
                marks[maxCol] = marks[k];
                marks[k] = aux;
            }
        }
        return Ab;
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
            System.out.print("x" + marks[i] + " ");
            System.out.println(M[i]);
        }
    }

    public static double[][] putZeros(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (Math.abs(A[i][j]) < 1E-14) {
                    A[i][j] = 0.0;
                }
            }
        }
        return A;
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

}