import java.util.Arrays;

import java.math.*;
/**
 * QuadraticSplines
 */
public class QuadraticSplines {
    private double[] x;
    private double[] y;
    private String[] polinomio;
    
    public QuadraticSplines(double[] x, double[] y){
        this.x = x;
        this.y = y;
        this.eval();
    }

    public double[][] processParams(){
        double points[][] = new double[this.x.length][2];
        for (int i = 0; i < this.x.length; i++) {
            points[i][0] = this.x[i];
            points[i][1] = this.y[i];
        }
        return points;
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

    public void eval(){
        double[][] points = this.processParams();
        int n = points.length - 1;
        double[] indepentVector = new double[n*3];
        double[][] matrix = new double[n*3][n*3];
        int j = 0;
        int k = 0;
        for (int i = 0; i < n*2; i+=2) {
            matrix[i][j] = Math.pow(points[k][0], 2);
            matrix[i][j+1] = points[k][0];
            matrix[i][j+2] = 1;

            matrix[i+1][j] = Math.pow(points[k+1][0], 2);
            matrix[i+1][j+1] = points[k+1][0];
            matrix[i+1][j+2] = 1;

            j += 3;
            k++;
        }
        j = 1;
        k = 0;
        for (int i = n*2; i < n*3-1; i++) {
            matrix[i][k] = 2*points[j][0];
            matrix[i][k+1] = 1;

            matrix[i][k + 3] = -2 * points[j][0];
            matrix[i][k+4] = -1;

            j++;
            k += 3;
        }

        //first derivate 0
        matrix[n*3-1][0] = 1;

        //indepent vector
        indepentVector[0] = points[0][1];
        j = 1;
        for (int i = 1; i < n; i++) {
            indepentVector[j] = points[i][1];
            indepentVector[j+1] = points[i][1];
            j +=2;
        }
        double[] sol = new PartialPivoting(matrix, indepentVector).getSolution();
        this.polinomio = this.generateEquation(sol);
    }

    public String[] generateEquation(double[] coefficient){
        int n = this.y.length - 1;
        String[] result =new String[n];
        int k = 0;
        for (int i = 0; i < n * 3; i+=3) {
            result[k] = coefficient[i]+ "x^2 +" + coefficient[i+1] + "x + " + coefficient[i+2]; 
            k++;
        }
        return result;
    }
    /**
     * @return the polinomio
     */
    public String[] getPolinomio() {
        return polinomio;
    }
    
    public static void main(String[] args) {
        double[] x = {0,1,2,3};
        double[] y = {0,1,1,0};
        QuadraticSplines q = new QuadraticSplines(x, y);
        for (int i = 0; i < q.getPolinomio().length; i++) {
            System.out.println(q.getPolinomio()[i]);
        }
    }
}