package methods;

/**
 * @author Hassler Castro Cuesta - Joshua Sanchez Alvarez- Edwin Rengifo Villa - Camilo Villa Restrepo
 */
public class SimpleGauss{
    private double [] solution;
    private double [][] elimination;

    public SimpleGauss(double [][] A, double [] b){
        this.elimination = gaussElimination(A, b);
        this.solution = gaussSolution(this.elimination, A.length-1);
    }

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

    public static double[][] gaussElimination(double[][] A, double[] b) {
        int n = A.length;
        double[][] Ab = augmentedMatrix(A, b);
        for (int i = 0; i < n - 1; i++) {
            //printMatrix(Ab);
            for (int j = i + 1; j < n; j++) {
                if(Ab[i][i] == 0){
                    for(int p = i+1 ; p < n ; p++ ){
                        if(Ab[i][p] != 0){
                            Ab = swapRows(Ab, p , i);
                            break;
                        }
                    }
                
                }
                double m = Ab[j][i] / Ab[i][i];
                for (int k = i; k < n + 1; k++) {
                    Ab[j][k] = (double) Ab[j][k] - m * Ab[i][k];
                }
            }
        }
        return Ab;
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

        //printMatrix(result);

    }
    
     public static void multVector(double[][] A, double[] B) {

        double[][] result = new double[A.length][B.length];
        if (A.length != B.length) {
            System.out.println("Dimension Error");
            return;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k];
                }

            }
        }

        //printMatrix(result);

    }

    public static double[] gaussSolution(double[][] Ab, int n) {
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
            for(int i = 0; i < M.length; i++) {
                double aux = 0;
                aux = M[i][col1];
                M[i][col1] = M[i][col2];
                M[i][col2] = aux;
            }
        }
        return M;
    }
}