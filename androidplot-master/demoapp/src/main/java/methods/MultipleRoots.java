package methods;

import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
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
    private String msg;

    public MultipleRoots(Double tolerance, BigDecimal x, int niter, String functionF, String diffFunction, String diff2Function) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
        this.diffFunction = diffFunction;
        this.diff2Function = diff2Function;
        this.eval();
    }

    public MultipleRoots(){
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
        this.diffFunction = "";
        this.diff2Function = "";
    }

    public ArrayList<ArrayList<Double>> eval() {
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
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(fx.doubleValue());
        firstIteration.add(dfx.doubleValue());
        firstIteration.add(d2fx.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        BigDecimal x1=new BigDecimal(0);
        while ((fx != BigDecimal.ZERO) && (error > this.tolerance) && (counter < this.niter) && (dfx != BigDecimal.ZERO)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            x1 = new BigDecimal(this.x.doubleValue()-((fx.doubleValue()*dfx.doubleValue())/(Math.pow(dfx.doubleValue(),2) -(fx.doubleValue()*d2fx.doubleValue()))));//this.x.subtract(fx.divide(dfx));
            fx = expressionF.setVariable("x", x1).eval();

            dfx = expressionDf.setVariable("x", x1).eval();
            
            //fx = expressionF.setVariable("x", x1).eval();

            d2fx = expressionD2f.setVariable("x", x1).eval();

            error = Math.abs((x1.doubleValue() - x.doubleValue())/x1.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.doubleValue());
            nIteration.add(fx.doubleValue());
            nIteration.add(dfx.doubleValue());
            nIteration.add(d2fx.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = x1;
            counter++;
        }
        if (fx == BigDecimal.ZERO) {
            System.out.print("x is a root");
        } else if (error < this.tolerance) {
            System.out.print(x1);
            System.out.print("x is approximation with a tolerance = ");
            this.msg = "There is an approximation with a tolerance " + this.tolerance + " at " + x1.toString();
            System.out.println(tolerance);
        } else if(dfx == BigDecimal.ZERO){
            System.out.println(x1);
            System.out.print(" Is a possible multiple root");
            this.msg = "There is a possible root at " + x1.toString();

        }else{
            System.out.println("Failed");
            this.msg = "Failed";
        }

        for (int i = 0; i < resultTable.size(); i++) {
            for (int j = 0; j < resultTable.get(i).size(); j++) {
                System.out.print(resultTable.get(i).get(j));
                System.out.print("   ");
            }
            System.out.println();
        }
        return resultTable;
    }

    public String getMsg(){
        return this.msg;
    }
}