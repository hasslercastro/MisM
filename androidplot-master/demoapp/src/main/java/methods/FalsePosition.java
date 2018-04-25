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

    public FalsePosition() {
        this.function = "";
        this.a = 0.0;
        this.b = 0.0;
        this.tolerance = 0.0;
        this.iterations = 0;
    }

    public FalsePosition(String function, Double a, Double b, Double tolerance, int iterations) {
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

    public ArrayList<ArrayList<Double>> eval() {
        ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();
        BigDecimal fxi = null;
        BigDecimal fxs = null;
        BigDecimal fxm = null;
        Double xm = 0.0;
        Expression expression = new Expression(this.function).setPrecision(16);
        fxi = expression.setVariable("x", this.a.toString()).eval();
        fxs = expression.setVariable("x", this.b.toString()).eval();

        if (fxi.doubleValue() == 0.0) {
            this.message = this.a.toString() + " is a root";
            return table;
        } else if (fxs.doubleValue() == 0.0) {
            this.message = this.b.toString() + " is a root";
            return table;
        } else if (fxi.doubleValue() * fxs.doubleValue() < 0) {
            xm = a - ((expression.setVariable("x", this.a.toString()).eval().doubleValue() * (b - a)) / (expression.setVariable("x", this.b.toString()).eval().doubleValue() - expression.setVariable("x", this.a.toString()).eval().doubleValue()));
            fxm = expression.setVariable("x", xm.toString()).eval();
            int counter = 1;
            Double error = this.tolerance + 1.0;
            ArrayList<Double> iter1 = new ArrayList<>();
            iter1.add((double) counter);
            iter1.add(a);
            iter1.add(b);
            iter1.add(xm);
            iter1.add(fxm.doubleValue());
            iter1.add(0.0);
            table.add(iter1);
            while (error > this.tolerance && fxm.doubleValue() != 0.0 && counter < this.iterations) {
                ArrayList<Double> iter2 = new ArrayList<>();
                if (fxi.doubleValue() * fxm.doubleValue() < 0.0) {
                    this.b = xm;
                    fxs = fxm;
                } else {
                    this.a = xm;
                    fxi = fxm;
                }
                Double xaux = xm;
                xm = a - ((expression.setVariable("x", this.a.toString()).eval().doubleValue() * (b - a))
                        / (expression.setVariable("x", this.b.toString()).eval().doubleValue()
                                - expression.setVariable("x", this.a.toString()).eval().doubleValue()));
                fxm = expression.setVariable("x", xm.toString()).eval();
                error = Math.abs(xm - xaux);
                counter++;
                iter2.add((double) counter);
                iter2.add(a);
                iter2.add(b);
                iter2.add(xm);
                iter2.add(fxm.doubleValue());
                iter2.add(error);
                table.add(iter2);
            }
            if (fxm.doubleValue() == 0.0) {
                this.message = xm.toString() + " is a root";
                return table;
            } else if (error < this.tolerance) {
                this.message = xm.toString() + " is an approximation to the root with absolute error " + error.toString();
                return table;
            } else {
                this.message = "The method failed in " + String.valueOf(this.iterations) + " iterations";
                return table;
            }
        } else {
            this.message = "The interval is inadequate";
            return table;
        }
    }
}