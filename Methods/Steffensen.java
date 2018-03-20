import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Steffensen {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionF;
    private String msg;

    public Steffensen(Double tolerance, BigDecimal x, int niter, String functionF) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
    }

    public Steffensen() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionF = new Expression(this.functionF).setPrecision(10);
        BigDecimal fx = expressionF.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double)(counter));
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(fx.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        BigDecimal x1=new BigDecimal(0);
        while ((fx != BigDecimal.ZERO) && (error > this.tolerance) && (counter < this.niter)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            BigDecimal xfx = new BigDecimal(this.x.doubleValue() + fx.doubleValue());
            BigDecimal fxfx = expressionF.setVariable("x", xfx).eval();
            x1 = new BigDecimal((Math.pow(fx.doubleValue(), 2))/(fxfx.doubleValue() - fx.doubleValue()));
            fx = expressionF.setVariable("x", x1).eval();
            error = Math.abs((x1.doubleValue() - x.doubleValue())/x1.doubleValue());
            nIteration.add(counter + 1.0);
            nIteration.add(x1.doubleValue());
            nIteration.add(fx.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = x1;
            counter++;
        }
        if (fx == BigDecimal.ZERO) {
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