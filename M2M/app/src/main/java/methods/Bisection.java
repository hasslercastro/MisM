package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Bisection
 */
public class Bisection {
    private String function;
    private Double a;
    private Double b;
    private Double tolerance;
    private int iterations;
    private String message;

    public Bisection() {
        this.function = "";
        this.a = 0.0;
        this.b = 0.0;
        this.tolerance = 0.0;
        this.iterations = 0;
    }

    public Bisection(String function, Double a, Double b, Double tolerance, int iterations) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.tolerance = tolerance;
    }

    public int iterationsNeeded(Double a, Double b, Double tolerance) {
        return (int) Math.ceil(Math.log(b - a) - Math.log(tolerance) / Math.log(2));
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

    public String getMessage() {
        return this.message;
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

    public ArrayList<ArrayList<Double>> bisection() {
        this.iterations = iterationsNeeded(this.a, this.b, this.tolerance);
        ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();
        MathContext mc = new MathContext(5);
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
            xm = (this.a + this.b) / 2;
            fxm = expression.setVariable("x", xm.toString()).eval();
            int counter = 1;
            Double error = this.tolerance + 1.0;
            ArrayList<Double> iter1 = new ArrayList<>();
            iter1.add((double) counter);
            iter1.add(new BigDecimal(a).round(mc).doubleValue());
            iter1.add(new BigDecimal(b).round(mc).doubleValue());
            iter1.add(new BigDecimal(xm).round(mc).doubleValue());
            iter1.add(fxm.round(mc).doubleValue());
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
                xm = ((this.a + this.b) / 2);
                fxm = expression.setVariable("x", xm.toString()).eval();
                error = Math.abs(xm - xaux);
                counter++;
                iter2.add((double) counter);
                iter2.add(new BigDecimal(a).round(mc).doubleValue());
                iter2.add(new BigDecimal(b).round(mc).doubleValue());
                iter2.add(new BigDecimal(xm).round(mc).doubleValue());
                iter2.add(fxm.round(mc).doubleValue());
                iter2.add(new BigDecimal(error).round(mc).doubleValue());
                table.add(iter2);
            }
            if (fxm.doubleValue() == 0.0) {
                this.message = xm.toString() + " is a root";
                return table;
            } else if (error < this.tolerance) {
                this.message = xm.toString() + " is an approximation to the root with absolute error "
                        + error.toString();
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