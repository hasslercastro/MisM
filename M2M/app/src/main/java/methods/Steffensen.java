package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Steffensen {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionG;
    private String msg;

    public Steffensen(Double tolerance, BigDecimal x, int niter, String functionG) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionG = functionG;
    }

    public Steffensen() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionG = "";
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionG = new Expression(this.functionG).setPrecision(16);
        BigDecimal gx = expressionG.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double)(counter));
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(gx.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        while ((gx != BigDecimal.ZERO) && (error > this.tolerance) && (counter < this.niter)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            gx = expressionG.setVariable("x", this.x).eval();
            BigDecimal xgx = new BigDecimal(this.x.doubleValue() + gx.doubleValue());
            BigDecimal gxgx = expressionG.setVariable("x", xgx).eval();
            BigDecimal x1 = new BigDecimal(this.x.doubleValue() - ((Math.pow(gx.doubleValue(), 2))/(gxgx.doubleValue() - gx.doubleValue())));         
            error = Math.abs((x1.doubleValue() - x.doubleValue())/x1.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.doubleValue());
            nIteration.add(gx.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = x1;
            counter++;
        }
        if (gx == BigDecimal.ZERO) {
            this.msg = this.x.toString() + " is a root";
        } 
        else if (error < this.tolerance) {
            this.msg = this.x.toString() + " is an approximation with a tolerance of = " + this.tolerance.toString();
        }
        else{
            this.msg = "The method failed in " + String.valueOf(this.niter) + "iterations"; 
        }
        return resultTable;
    }
}