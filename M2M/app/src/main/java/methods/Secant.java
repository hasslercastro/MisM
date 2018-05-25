package methods;

import android.util.Log;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Secant
 */
public class Secant {
    private Double tolerance;
    private Double x0;
    private Double x1;
    private Double niter;
    private String function;
    private String message;

    public Secant(Double tolerance, Double x0, Double x1, Double niter, String function) {
        this.tolerance = tolerance;
        this.x0 = x0;
        this.x1 = x1;
        this.niter = niter;
        this.function = function;
        this.message = "";
    }

    public Secant() {
        this.tolerance = 0.0;
        this.x0 = 0.0;
        this.x1 = 0.0;
        this.niter = 0.0;
        this.function = "";
        this.message = "";
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @return the niter
     */
    public Double getNiter() {
        return niter;
    }

    /**
     * @return the tolerance
     */
    public Double getTolerance() {
        return tolerance;
    }

    /**
     * @return the x0
     */
    public Double getX0() {
        return x0;
    }

    /**
     * @return the x1
     */
    public Double getX1() {
        return x1;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param niter the niter to set
     */
    public void setNiter(Double niter) {
        this.niter = niter;
    }

    /**
     * @param tolerance the tolerance to set
     */
    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * @param x0 the x0 to set
     */
    public void setX0(Double x0) {
        this.x0 = x0;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public ArrayList<ArrayList<Double>> eval() {
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        MathContext mc = new MathContext(5);
        ArrayList<Double> iteration = new ArrayList<>();
        Expression expressionF = new Expression(this.function).setPrecision(16);
        BigDecimal fx0 = expressionF.setVariable("x", this.x0.toString()).eval();
        if (fx0.doubleValue() == 0.0) {
            this.message = this.x0.toString() + " is a root";
            return resultTable;
        } else {
            BigDecimal fx1 = expressionF.setVariable("x", this.x1.toString()).eval();
            int counter = 0;
            Double error = this.tolerance + 1.0;
            BigDecimal den = new BigDecimal(fx1.doubleValue() - fx0.doubleValue());
            iteration.add((double) counter);
            iteration.add(new BigDecimal(this.x0).round(mc).doubleValue());
            iteration.add(fx0.round(mc).doubleValue());
            iteration.add(0.0);
            resultTable.add(iteration);
            ArrayList<Double> iteration2 = new ArrayList<>();
            iteration2.add((double) counter + 1.0);
            iteration2.add(new BigDecimal(this.x1).round(mc).doubleValue());
            iteration2.add(fx1.round(mc).doubleValue());
            iteration2.add(0.0);
            resultTable.add(iteration2);
            counter = counter + 2;
            while (error > this.tolerance && fx1.doubleValue() != 0.0 && den.doubleValue() != 0.0 && counter < niter) {
                ArrayList<Double> iterationN = new ArrayList<>();
                BigDecimal x2 = new BigDecimal(x1 - ((fx1.doubleValue() * (this.x1 - this.x0)) / (den.doubleValue())));
                error = Math.abs(x2.doubleValue() - this.x1.doubleValue());
                this.x0 = this.x1;
                fx0 = fx1;
                this.x1 = x2.doubleValue();
                fx1 = expressionF.setVariable("x", this.x1.toString()).eval();
                den = new BigDecimal(fx1.doubleValue() - fx0.doubleValue());
                iterationN.add((double) counter);
                iterationN.add(x2.round(mc).doubleValue());
                iterationN.add(fx1.round(mc).doubleValue());
                iterationN.add(new BigDecimal(error).round(mc).doubleValue());
                resultTable.add(iterationN);
                counter++;
            }
            if (fx1.doubleValue() == 0.0) {
                this.message = this.x1.toString() + " is a root";
                return resultTable;
            } else if (error < this.tolerance) {
                this.message = this.x1.toString() + " is an approximation to the root with absolute error " + error.toString();
                return resultTable;
            } else if (den.doubleValue() == 0.0) {
                this.message = "There is a possible mutiple root";
                return resultTable;
            } else {
                this.message = "The method failed in " + String.valueOf(this.niter) + " iterations";
                return resultTable;
            }
        }
    }
}