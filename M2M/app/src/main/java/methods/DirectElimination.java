/**
 * DirectElimination
 */
public class DirectElimination {

    private double[][] A;
    private double[] b;
    private double[][] L;
    private double[][] U;

    public DirectElimination(double[][] A, double[] b){
        this.A = A;
        this.b = b;
        this.L = new double[A.length][A.length];
        this.U = new double[A.length][A.length];
        this.eval();
    }
    public double[][] getA() {
        return A;
    }
    public double[] getB() {
        return b;
    }
    public double[][] getL() {
        return L;
    }
    public double[][] getU() {
        return U;
    }
    public void setL(int i,int j, double l) {
        this.L[i][j] = l;
    }
    public void setU(int i,int j, double u) {
        this.U[i][j] = u;
    }
    public void eval(){
        double [][][] res = new double[2][A.length][A.length];
        this.auxFactorization();
        double sum1;
        for (int k = 0; k < A.length; k++) {
            sum1 = 0;
            for (int p = 0; p < k; p++) {
                sum1 += this.L[k][p] * this.U[p][k];
            }
            this.auxFactorization2(k, sum1);
            for (int i = k+1; i < A.length; i++) {
                sum1 = 0;
                for (int p = 0; p < k; p++) {
                sum1 += this.L[i][p] * this.U[p][k];
                }
                this.L[i][k] = (this.A[i][k] - sum1)/this.U[k][k];
            
            }
            for (int j = k+1; j < this.A.length; j++) {
                sum1 = 0;
                for (int p = 0; p < k ; p++) {
                    sum1 += this.L[k][p] * this.U[p][j];
                }
                this.U[k][j] = (this.A[k][j] - sum1)/this.L[k][k];
            }
        }
    }
    //These methods will be overriden depending of each method(Doolittle, Crout, Cholesky).
    public void auxFactorization(){}
    public void auxFactorization2(int i, double sum1){}

}