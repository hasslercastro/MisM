package methods;
public class RelaxedJacobi extends IterativeMethod{
    private double w;
    
    public RelaxedJacobi(double[][] a, double[] b, double[] x0, double tol, int niter, double w) {
        super(a, b, x0, tol, niter);
        this.w = w;
        this.setTable(this.eval());
    }

    @Override
    public double[] calculateX1(){
        double[] result = new double[this.getB().length];
        for (int i = 0; i < this.getA().length; i++) {
            double sum = 0;
            for (int j = 0; j < this.getX0().length; j++) {
                if (j != i){
                    sum += this.getA()[i][j]*this.getX0()[j];
                }
            }
            result[i] = (1 - this.w)*result[i] + (w/ this.getA()[i][i])*(this.getB()[i] - sum);
        }
        return result;
    }

    @Override //Norm2
    public double Norm(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; i++) {
            result += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(result);
    }
}