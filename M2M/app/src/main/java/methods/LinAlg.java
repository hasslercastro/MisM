/**
 * LinAlg
 */
public class LinAlg {

    public static void printMatrix(double[][] A){
        for(int i = 0; i < A.length; i++){
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void printArray(double[] M) {
        System.out.println("");
        for (int i = 0; i < M.length; i++) {
            System.out.print("x" + (i+1) + " ");
            System.out.println(M[i]);

        }
    }

    public static double[][] mult(double[][] A, double[][] B) {

        double[][] result = new double[A.length][B[0].length];
        if (A[0].length != B.length) {
            System.out.println("Dimension Error");
            return result;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }

            }
        }
        return result;
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

    public static double[][] augmentedMatrix(double[][] A, double[] b){
        double[][] Ab = new double [A.length][A[0].length+1];
        if (A.length != b.length){
            System.out.println("Error, can't build the augmented matrix. Wrong dimesions");
            System.exit(0);
            return Ab;
        }else{
            for(int i = 0; i < A.length;i++){
                for (int j = 0; j < A[0].length; j++) {
                    Ab[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < Ab.length;i++){
                Ab[i][Ab[0].length-1] = b[i];
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
            for (int p = 0 ; p <= i; p++) {
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

        printMatrix(result);
        return DefResult;

    }
}