package  methods;
public class Doolittle extends DirectElimination{

    public Doolittle(double[][] A, double[] b){
        super(A, b);
    }    
    
    @Override
    public void auxFactorization() {
        for (int i = 0; i < this.getL().length; i++) {
            setL(i, i, 1);
        }
    }

    @Override
    public void auxFactorization2(int i, double sum1) {
         this.setU(i, i, this.getA()[i][i] - sum1);
    }
}