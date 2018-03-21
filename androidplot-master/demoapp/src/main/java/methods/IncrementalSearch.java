package methods; /**
 * IncrementalSearch
 */
import methods.com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.ArrayList;


public class IncrementalSearch {
   private String function;
   private BigDecimal x0;
   private double delta;
   private double[] result;
   private int niter;
   private String message;
   ArrayList<ArrayList<Double>> resultA =  new ArrayList<>();


   public IncrementalSearch(){
       this.function = "";
       this.x0 = new BigDecimal(0.0);
       this.delta = 0.0;
       this.result = new double[]{0.0};
       this.niter = 0;
   }

   public IncrementalSearch(String function, BigDecimal x0, double delta, int niter){
    this.function = function;
    this.x0 = x0;
    this.delta = delta;
    this.result = new double[]{0.0};;
    this.niter = niter;
   }

   public String getMessage(){
       return this.message;
   }

  
    public ArrayList<ArrayList<Double>> incrementalSearch(){

        Expression expression = new Expression(this.function).setPrecision(10);
        expression.setVariable("x", this.x0);
        BigDecimal fx0 = expression.eval();
        BigDecimal x1;
        BigDecimal fx1;
        BigDecimal delta = new BigDecimal(this.delta);
        if (fx0.equals(0)) {

            ArrayList<Double> currentResult =  new ArrayList<>();
            currentResult.add(this.x0.doubleValue());
            this.resultA.add(currentResult);
                return this.resultA;
        } else {
            x1 = this.x0.add(delta);
            int i = 1;
            expression.setVariable("x", x1);
            fx1 = expression.eval();
            while ((fx0.doubleValue()*fx1.doubleValue() > 0) && (i < this.niter)) {
                ArrayList<Double> currentResult = new ArrayList();
                this.x0 = x1;
                fx0 = fx1;
                x1 = this.x0.add(delta);
                expression.setVariable("x", x1);
                fx1 = expression.eval();
                currentResult.add((double) i );
                currentResult.add(this.x0.doubleValue());
                currentResult.add(x1.doubleValue());
                currentResult.add(fx0.doubleValue());
                currentResult.add(fx1.doubleValue());
                this.resultA.add(currentResult);
                i++;                
            }
            if (fx1.doubleValue() == 0.0) {
                this.result = new double[]{x1.doubleValue()};
                this.message ="There is a root at " + x1.toString();
                return this.resultA;
            }
            else if (fx0.doubleValue()*fx1.doubleValue() < 0) {
                this.result = new double[]{this.x0.doubleValue(), x1.doubleValue()};
                this.message = "There is a root between " + this.x0.toString() + " and " +x1.toString();
                return this.resultA;
            } 
            else {
                System.out.println("Failed in " + i + "iterations");
                this.message = "Failed in " + i + " iterations";
                this.result = new double[]{-11111, -11111};
                return this.resultA;
            }   
        }
    }
}
