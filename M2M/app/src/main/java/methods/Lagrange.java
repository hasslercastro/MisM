package methods;

public class Lagrange {
    private double[] L;
    private double[] f;
    private double[] points;
   
    public Lagrange(double[] f, double[] points){
        this.L = new double[f.length];
        this.f = f;
        this.points = points;
    }
    public String getPolinomio(){
        String polinomio = "";
        for (int i = 0; i < L.length ; i++) {
            String numerator = "";
            double denominator = 1.0;
            for (int j = 0; j < points.length; j++) {
                if (i != j){
                numerator += "("+"x" + "-"+ points[j]+")";
                denominator *=  (points[i] - points[j]);
                } 
            }
            polinomio += this.f[i]+"(" +numerator + "/"+ denominator + ") + ";
            polinomio = polinomio.replaceAll("--", "+");
        }

        return polinomio.substring(0, polinomio.length()- 2);
    }

    public double getSolution(double x){
        findL(x);
        double result = 0;
        for (int i = 0; i < this.L.length; i++) {
            result += this.L[i] * this.f[i];
        }
        return result;
    }

    public void findL(double x){
        for (int i = 0; i < L.length ; i++) {
            double numerator = 1.0;
            double denominator = 1.0;
            for (int j = 0; j < points.length; j++) {
                if (i != j){
                numerator *= x - points[j];
                denominator *= points[i] - points[j];
                } 
            }
            this.L[i] = numerator / denominator;
        }
    }
    
    public double[] getL() { //this method just can be called after executing 'findL'
        return L;
    }
/*
    public static void main(String[] args) {
        double[] x = {-1, 1, 2, 4};
        double[] y = {7, -1, -8, 2};
        Lagrange a = new Lagrange(y, x);
        System.out.println(a.getSolution(0));
        System.out.println(a.getPolinomio());

    }
    */

}