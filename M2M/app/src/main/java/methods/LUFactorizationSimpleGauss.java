/**
 *@author Hassler Castro Cuesta - Joshua Sanchez Alvarez- Edwin Rengifo Villa - Camilo Villa Restrepo
 */
public class LUFactorizationSimpleGauss{
    double [][] L;
    double [][] U;

    public LUFactorizationSimpleGauss(double[][] A){
        double[][][] LU = LUGauss(A);
        this.L = LU[0];
        this.U = LU[1];
    }

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

    public static double[][][] LUGauss(double[][] A) {
        double [][] L = new double[A.length][A.length];
        double [][] U = new double[A.length][A.length];
        double [][][] res = new double[2][A.length][A.length];
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(A[i][i] == 0){
                    for(int p = i+1 ; p < n ; p++ ){
                        if(A[i][p] != 0){
                            A = swapRows(A, p , i);
                            break;
                        }
                    }
                
                }
                double m = A[j][i] / A[i][i];
                    L[j][i] = m;
                for (int k = i; k < n; k++) {
                    A[j][k] = (double) A[j][k] - m * A[i][k];
                }
            }
            for (int k = 0; k < A.length; k++) {
                    U[i][k] = A[i][k];
            }
        }
        for (int i = 0; i < L.length; i++) {
            L[i][i] = 1;
        }
        res[0] = L;
        res[1] = U;
        return res;
    }

    public static void printMatrix(double[][] A){
        for(int i = 0; i < A.length; i++){
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
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
}