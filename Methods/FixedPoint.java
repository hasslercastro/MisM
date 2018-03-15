import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;

public class FixedPoint {

    private Double tolerance;
    private BigDecimal x;
    private int niter;
    private String functionF;
    private String functionG;

    public FixedPoint(Double tolerance, BigDecimal x, int niter, String functionF, String functionG) {
        this.tolerance = tolerance;
        this.x = x;
        this.niter = niter;
        this.functionF = functionF;
        this.functionG = functionG;
        this.eval();
    }

    public FixedPoint() {
        this.tolerance = 0.0;
        this.x = BigDecimal.ZERO;
        this.niter = 0;
        this.functionF = "";
        this.functionG = "";
    }

    public ArrayList<ArrayList<Double>> eval() {
        Expression expressionF = new Expression(this.functionF).setPrecision(10);
        BigDecimal resultF = expressionF.setVariable("x", this.x).eval();
        ArrayList<ArrayList<Double>> resultTable = new ArrayList<>();
        ArrayList<Double> firstIteration = new ArrayList<>();
        int counter = 0;
        Double error = this.tolerance + 1;
        firstIteration.add((double)(counter));
        firstIteration.add(this.x.doubleValue());
        firstIteration.add(resultF.doubleValue());
        firstIteration.add(0.0);
        resultTable.add(firstIteration);
        while ((resultF != BigDecimal.ZERO) && (error > this.tolerance) && (counter < this.niter)) {
            ArrayList<Double> nIteration = new ArrayList<>();
            Expression expressionG = new Expression(this.functionG).setPrecision(10);
            BigDecimal resultG = expressionG.setVariable("x", this.x).eval();
            BigDecimal xn = resultG;
            resultF = expressionF.setVariable("x", xn).eval();
            Double xD = this.x.doubleValue();
            Double xnD = xn.doubleValue();
            error = Math.abs((xnD - xD) / xnD);
            nIteration.add(counter + 1.0);
            nIteration.add(xn.doubleValue());
            nIteration.add(resultF.doubleValue());
            nIteration.add(error);
            resultTable.add(nIteration);
            this.x = xn;
            counter++;
        }
        if (resultF == BigDecimal.ZERO) {
            System.out.print("xa is a root");
        } else if (error < this.tolerance) {
            System.out.print(this.x);
            System.out.print("xa is approximation with a tolerance = ");
            System.out.print(tolerance);
        } else {
            System.out.print("Failed in: iterations");

        }
        return resultTable;
    }
}