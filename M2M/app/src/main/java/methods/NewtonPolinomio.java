package methods;

public class NewtonPolinomio {
    private double[][] table;
    private double[] y;

    public NewtonPolinomio(double[] x, double[] f){
        this.table = new double[x.length][x.length + 1];
        this.y = x;
        for (int i = 0; i < x.length; i++) {
            this.table[i][0] = x[i];
            this.table[i][1] = f[i];
        }
    }

    public double[][] getTable() {
        return table;
    }

    public double getSolution(double x){
        double result = 0.0;
        for (int i = 0; i < this.table[0].length - 1; i++) {
            double aux = this.table[i][i + 1];
            for (int j = 0; j < i; j++) {
                aux *= x - this.y[j];
            }
            result += aux;
        }
        return result;
    }

    public double[] getCoefficient(){
        double[] coefficient = new double[this.table.length];
        for (int i = 0; i < this.table[0].length - 1; i++) {
            coefficient[i] = this.table[i][i + 1];    
        }

        return coefficient;
    }
    public String getPolinomio(){
        String polinomio = "";
        double[] coefficien = this.getCoefficient();
        int n = y.length - 2;
        for (int i = 0; i < this.y.length - 1; i++) {
            polinomio += "("+coefficien[i]+")x^"+ n+ " + ";
            n--;
        }
        return polinomio;
    }

    public void eval(){
        for (int i = 2; i < this.table[0].length; i++) {
            for (int j = i - 1; j < this.table.length; j++) {
                this.table[j][i] = ((this.table[j - 1][i - 1] - this.table[j][i - 1])) / (this.table[j - i + 1][0] - this.table[j][0]);
            }
        }
    }
    /*
    public static void main(String[] args) {
        double[] x = {0, 1.2, 1.4, 1.6, 1.8, 2.0};//{-1, 1, 2, 4};
        double[] y = {0.6747, 0.8491, 1.1214, 1.4921, 1.9607, 2.5258};//{7, -1, -8, 2};
        NewtonPolinomio a = new NewtonPolinomio(x, y);
        a.eval();
        System.out.println(a.getSolution(1.2));
        System.out.println(a.getPolinomio());

    } 
    */   
}