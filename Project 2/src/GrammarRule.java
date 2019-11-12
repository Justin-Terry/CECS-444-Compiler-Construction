import java.util.ArrayList;
import java.util.List;

public class GrammarRule {
    private GrammarSymbol lhs;
    private List<GrammarSymbol> rhs;
    private boolean isTerminal;
    private int ruleNumber;
    private List<String> childrenValues;

    public GrammarRule(int ruleNumber, List<GrammarSymbol> rhs){
        this.rhs = rhs;
        this.ruleNumber = ruleNumber;
    }

}
