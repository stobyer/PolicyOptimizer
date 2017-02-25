import java.util.ArrayList;

/**
 * Created by stas.o on 22/02/2017.
 */
public class Policy {
    ArrayList<Rule> rules = new ArrayList<>();

    public void addRule(Rule r) {
        rules.add(r);
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public boolean optimize(){
        return true;
    }
}
