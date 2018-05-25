package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * MultipleRoots
 */
public class MultipleRoots {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionF;
    private String diffFunction;
    private String diff2Function;
    private String message;

    public MultipleRoots(Double tolerance, BigDecimal x, int niter, String functionF, String diffFunction, String diff2Function) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
        this.diffFunction = diffFunction;
        this.diff2Function = diff2Function;
    }

    public MultipleRoots(){
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
        this.diffFunction = "";
        this.diff2Function = "";
    }

    /**
     * @return the diff2Function
     */
    public String getDiff2Function() {
        return diff2Function;
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
     * @param diff2Function the diff2Function to set
     */
    public void setDiff2Function(String diff2Function) {
        this.diff2Function = diff2Function;
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
        MathContext mc = new MathContext(5);
        Expression expressionF = new Expression(this.functionF).setPrecision(16);
        BigDecimal fx = expressionF.setVariable("x", this.x).eval();
        Expression expressionDf = new Expression(this.diffFunction).setPrecision(16);
        BigDecimal dfx = expressionDf.setVariable("x", this.x).eval();
        Expression expressionD2f = new Expression(this.diff2Function).setPrecision(16);
        BigDecimal d2fx = expressionD2f.setVariable("x", this.x).eval();

        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double)(counter));
        firstIteration.add(this.x.round(mc).doubleValue());
        firstIteration.add(fx.round(mc).doubleValue());
        firstIteration.add(dfx.round(mc).doubleValue());
        firstIteration.add(d2fx.round(mc).doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        BigDecimal x1=new BigDecimal(0);
        while ((fx.doubleValue() != 0.0) && (error > this.tolerance) && (counter < this.niter) && (dfx.doubleValue() != 0.0)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            x1 = new BigDecimal(this.x.doubleValue()-((fx.doubleValue()*dfx.doubleValue())/(Math.pow(dfx.doubleValue(),2) -(fx.doubleValue()*d2fx.doubleValue()))));
            fx = expressionF.setVariable("x", x1).eval();
            dfx = expressionDf.setVariable("x", x1).eval();
            d2fx = expressionD2f.setVariable("x", x1).eval();
            error = Math.abs(x1.doubleValue() - this.x.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.round(mc).doubleValue());
            nIteration.add(fx.round(mc).doubleValue());
            nIteration.add(dfx.round(mc).doubleValue());
            nIteration.add(d2fx.round(mc).doubleValue());
            nIteration.add(new BigDecimal(error).round(mc).doubleValue());
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
        } else if(dfx.doubleValue() == 0.0){
            this.message = x1.toString() + " is a possible multiple root";
            return resultTable;
        }else{
            this.message = "The method failed in " + String.valueOf(this.niter) + " iterations";
            return resultTable;
        }
    }
}