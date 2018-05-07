
package methods;

import java.nio.charset.StandardCharsets;

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

}