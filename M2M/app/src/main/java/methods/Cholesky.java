package  methods;
public class Cholesky extends DirectElimination{

    public Cholesky(double[][] A, double[] b){
        super(A, b);
    }    
    
    @Override
    public void auxFactorization() {
        for (int i = 0; i < this.getL().length; i++) {
            setU(i, i, 1);
            setL(i, i, 1);
        }
    }

    @Override
    public void auxFactorization2(int i, double sum1) {
         this.setL(i, i, Math.sqrt(this.getA()[i][i] - sum1));
         this.setU(i, i, this.getL()[i][i]);
    }
}