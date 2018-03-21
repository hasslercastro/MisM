package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * FalsePosition
 */
public class FalsePosition {
    private String function;
    private Double a;
    private Double b;
    private Double tolerance;
    private int iterations;
    private String message;

    public FalsePosition(){
        this.function = "";
        this.a = 0.0;
        this.b = 0.0;
        this.tolerance = 0.0;
        this.iterations = 0;
    }

    public FalsePosition(String function, Double a, Double b, Double tolerance, int iterations){
        this.function = function;
        this.a = a;
        this.b = b;
        this.tolerance = tolerance;
        this.iterations = iterations;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the tolerance
     */
    public Double getTolerance() {
        return tolerance;
    }

    /**
     * @return the a
     */
    public Double getA() {
        return a;
    }

    /**
     * @return the b
     */
    public Double getB() {
        return b;
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @return the iterations
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * @param a the a to set
     */
    public void setA(Double a) {
        this.a = a;
    }

    /**
     * @param b the b to set
     */
    public void setB(Double b) {
        this.b = b;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * @param iterations the iterations to set
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    /**
     * @param tolerance the tolerance to set
     */
    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public ArrayList<ArrayList<Double>> eval(){
        ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();
        BigDecimal fxi = null;
        BigDecimal fxs = null;
        BigDecimal fxm = null;
        Double xm = 0.0;
        Expression expression = new Expression(this.function).setPrecision(10);
        fxi = expression.setVariable("x",this.a.toString()).eval();
        fxs = expression.setVariable("x",this.b.toString()).eval();

        if (fxi.doubleValue() == 0.0) {
            System.out.print(this.a);
            System.out.println("Is a root");
            return table;
        }else if (fxs.doubleValue() == 0.0){
            System.out.print(this.b);
            System.out.println("Is a root");
            return table;
        }else if(fxi.doubleValue()*fxs.doubleValue() < 0){
            xm = a - ((expression.setVariable("x",this.a.toString()).eval().doubleValue()*(b-a))/(expression.setVariable("x",this.b.toString()).eval().doubleValue()-expression.setVariable("x",this.a.toString()).eval().doubleValue()));//(this.a+this.b)/2;
            fxm = expression.setVariable("x",xm.toString()).eval();
            int counter = 1;
            Double error = this.tolerance + 1.0;
            ArrayList<Double> iter1 = new ArrayList<>();
            iter1.add((double)counter);
            iter1.add(a);
            iter1.add(b);
            iter1.add(xm);
            iter1.add(fxm.doubleValue());
            iter1.add(0.0);
            table.add(iter1);
            while (error > this.tolerance && fxm.doubleValue() != 0.0 && counter < this.iterations) {
                ArrayList<Double> iter2 = new ArrayList<>();
                if(fxi.doubleValue() * fxm.doubleValue() < 0.0){
                    this.b = xm;
                    fxs = fxm;//expression.setVariable("x",xm.toString()).eval();
                }else{
                    this.a = xm;
                    fxi = fxm;//expression.setVariable("x",xm.toString()).eval();;
                }
                Double xaux = xm;
                xm = a - ((expression.setVariable("x",this.a.toString()).eval().doubleValue()*(b-a))/(expression.setVariable("x",this.b.toString()).eval().doubleValue()-expression.setVariable("x",this.a.toString()).eval().doubleValue()));
                fxm = expression.setVariable("x",xm.toString()).eval();
                error = Math.abs(xm-xaux);
                counter ++;
                iter2.add((double)counter);
                iter2.add(a);
                iter2.add(b);
                iter2.add(xm);
                iter2.add(fxm.doubleValue());
                iter2.add(error);
                table.add(iter2);
            }
            if(fxm.doubleValue() == 0.0){
                this.message = xm.toString()+" Is root";
                System.out.print(xm);
                System.out.println("Is root");
                return table;
            }else if(error < this.tolerance){
                this.message = xm.toString()+" is an aproximation with a tolerance of "+ this.tolerance.toString();
                System.out.print(xm);
                System.out.print(" is an aproximation with a tolerance of ");
                System.out.println(this.tolerance);
                return table;
            }else{
                this.message = "Failed in "+ new Double(this.iterations).toString() + " iterations";
                System.out.print("Failed in ");
                System.out.println(this.iterations);
                System.out.println(" iterations");
                return table;
            }
        }else{
            this.message = "The range is wrong";
            System.out.print("The range is wrong");
            return table;
        }
    }
    
    // public static void main(String[] args) {
    //     FalsePosition b = new FalsePosition(" (e^((3*x)-(12)))+(x*(cos(3*x)))-(x^2)+(4) ", 2.0, 3.0, 0.5E-3, 100);
    //     ArrayList<ArrayList<Double>> res = b.eval();
    //     for (int i = 0; i < res.size(); i++) {
    //         for (int j = 0; j < res.get(i).size(); j++) {
    //             System.out.print(res.get(i).get(j));
    //             System.out.print("   ");
    //         }
    //         System.out.println();
    //     }
    // }
}