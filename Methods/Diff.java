import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 * Diff
 */
public class Diff {
    private String function;
    private String result;

    public Diff(String function) {
        this.function = function;
        this.result = "";
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    public String derivative() {
        ParserForEuler pr = new ParserForEuler();
        this.function = pr.putEulerForDiff(this.function);
        DJep diff = new DJep();
        diff.addStandardFunctions();
        diff.addStandardConstants();
        diff.addComplex();
        diff.setAllowUndeclared(true);
        diff.setAllowAssignment(true);
        diff.setImplicitMul(true);
        diff.addStandardDiffRules();

        try {
            Node node = diff.parse(this.function);
            Node dif = diff.differentiate(node, "x");
            Node sim = diff.simplify(dif);
            this.result = diff.toString(sim);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.result;

    }

    // public static void main(String[] args) {
    //     Diff diff = new Diff("e^(x)");
    //     System.out.println(diff.derivative());
    // }
}
