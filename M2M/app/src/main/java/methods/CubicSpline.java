package methods;
import java.math.BigDecimal;
import java.math.MathContext;

import methods.com.udojava.evalex.Expression;

public class CubicSpline {
    private double[] points;
    private double[] f;
    private String[] polinomio;
    private MathContext mc;

    public CubicSpline(double[] points, double[] f){
        this.points =  points;
        this.f = f;
        this.mc = new MathContext(5);
        this.eval();
    }

    public void eval(){
        int n = points.length;
        this.polinomio = new String[n-1];
        // H values
        double[] h =new double[n - 1];
        for (int i = 0; i < n -1; i++) {
            h[i] =this.points[i+1] - this.points[i];
        }

        //Equations system
        double[][] A = new double[n-2][n-2];
        double[] B = new double[n-2];
        double[] S = new double[n];
        A[0][0] = 2*(h[0]+h[1]);
        A[0][1] = h[1];
        B[0] = 6*((this.f[2]-this.f[1]) / h[1] -(this.f[1]- this.f[0]) / h[0]);
        for (int i = 1; i < n-3; i++) {
            A[i][i-1] = h[i];
            A[i][i] = 2*(h[i]+h[i+1]);
            A[i][i+1] = h[i+1];
            B[i] = 6*((this.f[i+2]-this.f[i+1]) / h[i+1] -(this.f[i+1]- this.f[i]) / h[i]);
        }
        A[n-3][n-4] = h[n-3];
        A[n-3][n-3] = 2*(h[n-3]+h[n-2]);
        B[n-3] = 6*((this.f[n-1]-this.f[n-2])/h[n-2] - (this.f[n-2]-this.f[n-3])/h[n-3]);

        //Solve equations system
        double[] r = new SimpleGauss(A, B).getSolution();

        for (int i = 1; i < n-1; i++) {
            S[i] = r[i-1];
        }
        //S
        S[0] = 0;
        S[n-1] = 0;

        //coefficient
        double[] a = new double[n-1];
        double[] b = new double[n-1];
        double[] c = new double[n-1];
        double[] d = new double[n-1];

        for (int i = 0; i < n-1; i++) {
            a[i] = (S[i+1]-S[i]) / 6*h[i];
            b[i] = S[i]/2;
            c[i] = (this.f[i+1]-this.f[i])/h[i] - (2*h[i]*S[i]+h[i]*S[i+1])/6;
            d[i] = this.f[i];
        }
        //Spliner polynomial
        double[][] resullt = new double[4][n];
        for (int j = 0; j < n-1; j++) {
            resullt[0][j] = a[j];
            resullt[1][j] = b[j] - a[j]*3*points[j];
            resullt[2][j] = c[j] - b[j]*2*points[j] + a[j]*3*Math.pow(points[j], 2);
            resullt[3][j] = d[j] - c[j]*points[j] + b[j]*Math.pow(points[j], 2) - a[j]*Math.pow(points[j], 3);
        }
        for (int i = 0; i < points.length-1; i++) {
            double a1 = new BigDecimal(resullt[0][i]).round(mc).doubleValue();
            double b1 = new BigDecimal(resullt[1][i]).round(mc).doubleValue();
            double c1 = new BigDecimal(resullt[2][i]).round(mc).doubleValue();
            double d1 = new BigDecimal(resullt[3][i]).round(mc).doubleValue();
            this.polinomio[i] += a1 + "*x^3 + " + b1+"*x^2 + "+c1+"*x + "+ d1 +"      "+ points[i]+" <= x < "+points[i+1];
            this.polinomio[i] = this.polinomio[i].replace("nunull","").replace("+ -","-");
            this.polinomio[i] = this.polinomio[i].replace("null","");
        }
    }
    public String[] getPolinomio() {
        return polinomio;
    }

    public void printMatrix(double[][] M) {
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

    public double getResult(double x1){
        double result = 0;
        double m = 0;
        for (int i = 0; i < this.points.length - 1; i++) {
            if(x1 >= this.points[i] && x1 < this.points[i + 1]){

                System.out.println(polinomio[i]);
                Expression expression = new Expression(polinomio[i].split("     ")[0]).setPrecision(16);
                return expression.setVariable("x", String.valueOf(x1)).eval().doubleValue();
            }
        }
        return -6.66; // Error, the polynomial is not defined at this point
    }

    /**
    public static void main(String[] args) {
        double[] x = {0,1,2,3};
        double[] y = {0,1,1,0};
        CubicSpline q = new CubicSpline(x, y);
        for (int i = 0; i < q.getPolinomio().length; i++) {
            System.out.println(q.getPolinomio()[i]);
        }
    }*/

}