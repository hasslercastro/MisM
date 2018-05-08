package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Newton {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionF;
    private String diffFunction;
    private String message;

    public Newton(Double tolerance, BigDecimal x, int niter, String functionF, String diffFunction) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
        this.diffFunction = diffFunction;
    }

    public Newton() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
        this.diffFunction = "";
    }

    /**
     * @return the diffFunction
     */
    public String getDiffFunction() {
        return diffFunction;
    }

    /**
     * @return the functionF
     */
    public String getFunctionF() {
        return functionF;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the niter
     */
    public int getNiter() {
        return niter;
    }

    /**
     * @return the tolerance
     */
    public Double getTolerance() {
        return tolerance;
    }

    /**
     * @return the x
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * @param diffFunction the diffFunction to set
     */
    public void setDiffFunction(String diffFunction) {
        this.diffFunction = diffFunction;
    }

    /**
     * @param functionF the functionF to set
     */
    public void setFunctionF(String functionF) {
        this.functionF = functionF;
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
    public void setNiter(int niter) {
        this.niter = niter;
    }

    /**
     * @param tolerance the tolerance to set
     */
    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * @param x the x to set
     */
    public void setX(BigDecimal x) {
        this.x = x;
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionF = new Expression(this.functionF).setPrecision(16);
        BigDecimal fx = expressionF.setVariable("x", this.x).eval();
        Expression expressionDf = new Expression(this.diffFunction).setPrecision(16);
        BigDecimal dfx = expressionDf.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double) (counter));
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(fx.doubleValue());
        firstIteration.add(dfx.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        BigDecimal x1 = new BigDecimal(0);
        while ((fx.doubleValue() != 0.0) && (error > this.tolerance) && (counter < this.niter) && (dfx.doubleValue() != 0.0)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            x1 = new BigDecimal(this.x.doubleValue() - (fx.doubleValue() / dfx.doubleValue()));
            fx = expressionF.setVariable("x", x1).eval();
            dfx = expressionDf.setVariable("x", x1).eval();
            error = Math.abs(x1.doubleValue() - this.x.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.doubleValue());
            nIteration.add(fx.doubleValue());
            nIteration.add(dfx.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = x1;
            counter++;
        }
        if (fx.doubleValue() == 0.0) {
            this.message = this.x.toString() + " is a root";
            return resultTable;
        } else if (error < this.tolerance) {
            this.message = x1.toString() + " is an approximation to the root with absolute error " + error.toString();
            return resultTable;
        } else if (dfx.doubleValue() == 0.0) {
            this.message = x1.toString() + " is a possible multiple root";
            return resultTable;
        } else {
            this.message = "The method failed in " + String.valueOf(this.niter) + "iterations";
            return resultTable;
        }
    }
}