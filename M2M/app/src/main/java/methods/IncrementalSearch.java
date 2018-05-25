package methods;

/**
 * IncrementalSearch
 */
import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class IncrementalSearch {
    private String function;
    private BigDecimal x0;
    private double delta;
    private double[] result;
    private int niter;
    private String message;
    ArrayList<ArrayList<Double>> resultA = new ArrayList<>();

    public IncrementalSearch() {
        this.function = "";
        this.x0 = new BigDecimal(0.0);
        this.delta = 0.0;
        this.result = new double[] { 0.0 };
        this.niter = 0;
    }

    public IncrementalSearch(String function, BigDecimal x0, double delta, int niter) {
        this.function = function;
        this.x0 = x0;
        this.delta = delta;
        this.result = new double[] { 0.0 };
        ;
        this.niter = niter;
    }

    /**
     * @return the delta
     */
    public double getDelta() {
        return delta;
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
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
     * @return the result
     */
    public double[] getResult() {
        return result;
    }

    /**
     * @return the resultA
     */
    public ArrayList<ArrayList<Double>> getResultA() {
        return resultA;
    }

    /**
     * @return the x0
     */
    public BigDecimal getX0() {
        return x0;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(double delta) {
        this.delta = delta;
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
    public void setNiter(int niter) {
        this.niter = niter;
    }

    /**
     * @param result the result to set
     */
    public void setResult(double[] result) {
        this.result = result;
    }

    /**
     * @param resultA the resultA to set
     */
    public void setResultA(ArrayList<ArrayList<Double>> resultA) {
        this.resultA = resultA;
    }

    /**
     * @param x0 the x0 to set
     */
    public void setX0(BigDecimal x0) {
        this.x0 = x0;
    }
    
    public ArrayList<ArrayList<Double>> incrementalSearch() {
        MathContext mc = new MathContext(5);
        Expression expression = new Expression(this.function).setPrecision(16);
        expression.setVariable("x", this.x0);
        BigDecimal fx0 = expression.eval();
        BigDecimal x1;
        BigDecimal fx1;
        BigDecimal delta = new BigDecimal(this.delta);
        if (fx0.doubleValue() == 0.0) {
            ArrayList<Double> currentResult = new ArrayList<>();
            currentResult.add(this.x0.round(mc).doubleValue());
            this.resultA.add(currentResult);
            this.message = this.resultA.toString() + " is a root";
            return this.resultA;
        } else {
            x1 = this.x0.add(delta);
            int i = 1;
            expression.setVariable("x", x1);
            fx1 = expression.eval();
            while ((fx0.doubleValue() * fx1.doubleValue() > 0) && (i < this.niter)) {
                ArrayList<Double> currentResult = new ArrayList();
                this.x0 = x1;
                fx0 = fx1;
                x1 = this.x0.add(delta);
                expression.setVariable("x", x1);
                fx1 = expression.eval();
                currentResult.add((double) i);
                currentResult.add(this.x0.round(mc).doubleValue());
                currentResult.add(x1.round(mc).doubleValue());
                currentResult.add(fx0.round(mc).doubleValue());
                currentResult.add(fx1.round(mc).doubleValue());
                this.resultA.add(currentResult);
                i++;
            }
            if (fx1.doubleValue() == 0.0) {
                this.result = new double[] { x1.doubleValue() };
                this.message = x1.toString() + " is a root";
                return this.resultA;
            } else if (fx0.doubleValue() * fx1.doubleValue() < 0) {
                this.result = new double[] { this.x0.doubleValue(), x1.doubleValue() };
                this.message = "There's a root in the interval [" + this.x0.toString() + " ," + x1.toString() + "]";
                return this.resultA;
            } else {
                this.message = "The method failed in " + String.valueOf(niter) + " iterations";
                this.result = new double[] { -11111, -11111 };
                return this.resultA;
            }
        }
    }
}
