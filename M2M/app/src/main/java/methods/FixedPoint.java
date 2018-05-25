package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class FixedPoint {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionG;
    private String message;

    public FixedPoint(Double tolerance, String x, int niter, String functionG) {
        this.tolerance = tolerance;
        this.x = new BigDecimal(x);
        this.niter = niter;
        this.functionG = functionG;
    }

    public FixedPoint() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionG = "";
    }

    /**
     * @return the functionG
     */
    public String getFunctionG() {
        return functionG;
    }

    /**
     * @return the niter
     */
    public int getNiter() {
        return niter;
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
     * @return the x
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * @param functionG the functionG to set
     */
    public void setFunctionG(String functionG) {
        this.functionG = functionG;
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
        Expression expressionG = new Expression(this.functionG).setPrecision(16);
        BigDecimal resultG = expressionG.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        MathContext mc = new MathContext(5);
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double) (counter));
        firstIteration.add(this.x.round(mc).doubleValue());
        firstIteration.add(resultG.round(mc).doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        while ((resultG.doubleValue() != 0.0) && (error > this.tolerance) && (counter < this.niter)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            resultG = expressionG.setVariable("x", this.x).eval();
            BigDecimal xn = resultG;
            error = Math.abs(xn.doubleValue() - this.x.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(xn.round(mc).doubleValue());
            nIteration.add(resultG.round(mc).doubleValue());
            nIteration.add(new BigDecimal(error).round(mc).doubleValue());
            resultTable.add(nIteration);
            this.x = xn;
            counter++;
        }
        if(resultG.doubleValue() == 0.0){
            this.message = this.x.toString() + " is a root";
            return resultTable;
        }
        else if (error < this.tolerance) {
            this.message = this.x.toString() + " is an approximation to the root with absolute error " + error.toString();
            return resultTable;            
        } else {
            this.message = "The method failed in " + String.valueOf(niter) + " iterations";
            return resultTable;
        }
    }
}