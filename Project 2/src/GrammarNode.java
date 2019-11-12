import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GrammarNode {
    private UUID nodeId;
    private GrammarSymbol value;
    private int ruleNumber;
    private List<GrammarNode> children;

    GrammarNode(GrammarSymbol value, int ruleNumber) {
        this.nodeId = UUID.randomUUID();
        this.children = new ArrayList<>();
        this.value = value;
        this.ruleNumber = ruleNumber;
    }

    public String getValueAsString() {
        return value.getSymbolAsString();
    }

    public GrammarSymbol getValue() {
        return this.value;
    }

    public List<GrammarNode> getChildren() {
        return this.children;
    }

    public void addChildrenForRule(int rule) {
        List<GrammarSymbol> childSymbols = GrammarRulesRepo.getInstance().getRuleRHS(rule);
        System.out.println("RULE NUMBER: " + rule + "has children:");
        for(GrammarSymbol symbol : childSymbols){
            System.out.println(symbol.getSymbolAsString());
            children.add(new GrammarNode(symbol, rule));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( Node: " + nodeId + " | Value: " + this.value.getSymbolAsString() +  " | Children: ");
        for(GrammarNode gn : children) {
            sb.append(gn.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
