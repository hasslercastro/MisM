import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Newton {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionF;

    public static void main(String[] args) {
        Newton newton = new Newton(0.5E-8, new BigDecimal(1.5), 10, "(x^3)+(4*(x^2))-(10)");
    }

    public Newton(Double tolerance, BigDecimal x, int niter, String functionF) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
        this.eval();
    }

    public Newton() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionF = new Expression(this.functionF).setPrecision(10);
        BigDecimal fx = expressionF.setVariable("x", this.x).eval();
        String derivate = new Diff(this.functionF).derivative();
        Expression expressionDf = new Expression(derivate).setPrecision(10);
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
            
            fx = expressionF.setVariable("x", x1).eval();

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
            System.out.print("x is a root");
        } else if (error < this.tolerance) {
            System.out.print(x1);
            System.out.print("x is approximation with a tolerance = ");
            System.out.print(tolerance);
        } else if(dfx == BigDecimal.ZERO){
            System.out.println(x1);
            System.out.print(" Is a possible multiple root");

        }else{
            System.out.println("Failed");
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
}