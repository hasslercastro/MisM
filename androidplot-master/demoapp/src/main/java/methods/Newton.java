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
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionF = new Expression(this.functionF).setPrecision(10);
        BigDecimal fx = expressionF.setVariable("x", this.x).eval();
        Expression expressionDf = new Expression(this.diffFunction).setPrecision(10);
        BigDecimal dfx = expressionDf.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double)(counter));
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(fx.doubleValue());
        firstIteration.add(dfx.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        BigDecimal x1=new BigDecimal(0);
        while ((fx != BigDecimal.ZERO) && (error > this.tolerance) && (counter < this.niter) && (dfx != BigDecimal.ZERO)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            x1 = new BigDecimal(this.x.doubleValue()-(fx.doubleValue()/dfx.doubleValue()));//this.x.subtract(fx.divide(dfx));
            fx = expressionF.setVariable("x", x1).eval();
            dfx = expressionDf.setVariable("x", x1).eval();
            error = Math.abs((x1.doubleValue() - x.doubleValue())/x1.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.doubleValue());
            nIteration.add(fx.doubleValue());
            nIteration.add(dfx.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = x1;
            counter++;
        }
        if (fx == BigDecimal.ZERO) {
            this.msg = this.x.toString() + " is a root";
        } else if (error < this.tolerance) {
            this.msg = this.x.toString() + " is an approximation with a tolerance of = " + this.tolerance.toString();
        } else if(dfx == BigDecimal.ZERO){
            this.msg = x1.toString() + " is a possible multiple root";
        }else{
            this.msg = "The method failed in " + String.valueOf(this.niter) + "iterations"; 
        }
        return resultTable;
    }
}