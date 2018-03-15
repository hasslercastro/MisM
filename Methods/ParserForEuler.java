/**
 * ParserForEuler
 */
public class ParserForEuler {
    public String putEulerForEval(String line){
        if(line.contains("exp")){
            return line.replace("exp", "e^");
        }else{
            return line;
        }
    }

    public String putEulerForDiff(String line) {
        if(line.contains("e^")){
            return line.replace("e^", "exp");
        }else{
            return line;
        }
    }

    public String putLnforEval(String line){
        if(line.contains("ln")){
            return line.replace("ln", "log");
        }else{
            return line;
        }
    }

    // public static void main(String[] args) {
    //     ParserForEuler parserForEuler = new ParserForEuler();
    //     System.out.println(parserForEuler.putEulerForEval("x+exp(x)+exp(23+34+x^2+exp(2))"));
    //     System.out.println(parserForEuler.putEulerForDiff("x+e^(x)+e^(23+34+x^2+e^(2))"));
    //     System.out.println(parserForEuler.putLnforEval("ln(2x+32+ln(x^2))"));
    // }
}