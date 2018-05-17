
public class CubicSpline {
    private double[] points;
    private double[] f;
    private String[] polinomio;
    public CubicSpline(double[] points, double[] f){
        this.points =  points;
        this.f = f;
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
        double[] r = new TotalPivoting(A, B).getSolution();

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
        //Polinomio trazador
        for (int i = 0; i < n-1; i++) {
            this.polinomio[i] = a[i] + "(x-"+this.points[i] +")^3 +" + b[i]+"(x-"+this.points[i] +")^2 +" + c[i]+"(x-"+this.points[i]+") +" + d[i];
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

    public static void main(String[] args) {
        double[] x = {0,1,2,3};
        double[] y = {0,1,1,0};
        CubicSpline q = new CubicSpline(x, y);
        for (int i = 0; i < q.getPolinomio().length; i++) {
            System.out.println(q.getPolinomio()[i]);
        }
    }

}