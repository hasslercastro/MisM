

package methods;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;

/**
 * This class contains all the similar stuff of the iterative methods, in order to avoid repeating code.
 */

public class IterativeMethod {
    private double[][] A;
    private double[] b, x0;
    private double tol;
    private int niter;
    private String msg;
    private ArrayList<ArrayList<Double>> table;

    public IterativeMethod(double[][] a, double[] b, double[] x0, double tol, int niter) {
        this.A = a;
        this.b = b;
        this.x0 = x0;
        this.tol = tol;
        this.niter = niter;
        this.msg= "";
    }

    public ArrayList<ArrayList<Double>> eval() {
        int counter = 0;
        ArrayList<ArrayList<Double>> table = new ArrayList<>(); 
        double dispersion = this.tol + 1;
        double[] x1 = new double[this.x0.length];
        table.add(this.make_row(counter, 0.0));//First iteration
        while ((dispersion > this.tol) && (counter < this.niter)) {
            x1 = this.calculateX1(); 
            dispersion = this.Norm(this.x0, x1);
            this.x0 = x1;
            counter++;
            table.add(this.make_row(counter, dispersion));
        }
        if (dispersion < this.tol) {
            return table;
        } else {
            this.msg = "The method failed in " + this.niter + "iterations"; 
            return table;
        }
    }

    //This method will be overridden by this class children, so right now it's useless.
    public double[] calculateX1(){
        double[] result = {0.0};
        return result;
    };

    public ArrayList<Double> make_row(int counter, double dispersion){
        ArrayList<Double> row = new ArrayList<Double>();
        row.add(counter*1.0);
        for (double x : this.x0) { row.add(x); }
        row.add(dispersion);
        return row;
    }
    //InfinityNorm by default
    public double Norm(double[] a, double[] b){
        double maxx = 0;
        for (int i = 0; i < a.length; i++) {
            maxx = Math.max(Math.abs(a[i] - b[i]), maxx);
       }
       return maxx;
    }

    public double[] getSolution(){return this.getX0();}
    public double[][] getA() {
        return A;
    }
    public double[] getB() { return b;}
    public String getMsg() {
        return msg;
    }
    public int getNiter() {
        return niter;
    }
    public double getTol() {
        return tol;
    }
    public double[] getX0() {
        return x0;
    }
    public ArrayList<ArrayList<Double>> getTable(){ return  this.table;};
    public void setTable(ArrayList<ArrayList<Double>> tabla) {
        this.table = tabla;
    }
}