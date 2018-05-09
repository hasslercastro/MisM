package  methods;
/**
 * GaussSeidel
 */
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;
public class GaussSeidel extends IterativeMethod{

    public GaussSeidel(double[][] a, double[] b, double[] x0, double tol, int niter) {
        super(a, b, x0, tol, niter);
        this.setTable(this.eval());
    }

    @Override
    public double[] calculateX1(){
        double[] result = this.getX0().clone();
        for (int i = 0; i < this.getA().length; i++) {
            double sum = 0;
            for (int j = 0; j < this.getX0().length; j++) {
                if (j != i){
                    sum += this.getA()[i][j]*result[j];
                }
            }
            result[i] = (this.getB()[i] - sum) / this.getA()[i][i];
        }
        return result;
    }
}   