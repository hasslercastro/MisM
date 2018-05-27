package methods;

public class LinearSpline {
    private double[] points;
    private double[] f;
    private String[] polinomio;

    public LinearSpline(double[] points, double[] f){
        this.points = points;
        this.f = f;
        this.polinomio = new String[this.points.length - 1];
        this.makePolinomio();
    }

    public double getResult(double x){
        double result = 0;
        double m = 0;
        for (int i = 0; i < this.points.length; i++) {
            if(x >= this.points[i] && x < this.points[i + 1]){
                m = (this.f[i + 1] - this.f[i]) / (this.points[i + 1] - this.points[i]);
                result = m*(x - this.points[i]) - this.f[i];
                return result;
            }
        }
        return -6.66; // Error, the polynomial is not defined at this point
    }

    public void makePolinomio() {
        double m;
        for (int i = 0; i < this.polinomio.length; i++) {
            m = (this.f[i + 1] - this.f[i]) / (this.points[i + 1] - this.points[i]);
            this.polinomio[i] =  m+"*x + " + (-m*this.points[i] + this.f[i]) + "";
        }
    }

    public String[] getPolinomio() {
        return polinomio;
    }

    /*
    public static void main(String[] args) {
        double[] points = {0,1,2,3};
        double[] f = {0,1,1, 0};
        LinearSpline a = new LinearSpline(points, f);
        for (int i = 0; i < a.getPolinomio().length; i++) {
            System.out.println(a.getPolinomio()[i]);    
        }
        
    }
    */

    
}