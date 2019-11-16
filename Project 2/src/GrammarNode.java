import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class GrammarNode {
    private UUID nodeId;
    private GrammarSymbol value;
    private int ruleNumber;
    private List<GrammarNode> children;
    GrammarNode parent;
    private int distanceToRoot;

    GrammarNode(GrammarSymbol value, int ruleNumber, GrammarNode parent) {
        this.nodeId = UUID.randomUUID();
        this.children = new ArrayList<>();
        this.value = value;
        this.ruleNumber = ruleNumber;
        this.parent = parent;
    }

    private void setDistanceToRoot(){
        int count = 0;
        GrammarNode g = this;
        while(g.getParent() != null){
            g = g.getParent();
            count ++;
        }
        distanceToRoot = count;
    }

    public String getValueAsString() {
        return value.getSymbolAsString();
    }

    public GrammarSymbol getValue() {
        return this.value;
    }

    public GrammarNode getParent() {
        return this.parent;
    }

    public List<GrammarNode> getChildren() {
        return this.children;
    }

    public void deleteChild(GrammarNode gn) {
        this.children.remove(gn);
    }

    public void addChildrenForRule(int rule) {
        List<GrammarSymbol> childSymbols = GrammarRulesRepo.getInstance().getRuleRHS(rule);
        for(GrammarSymbol symbol : childSymbols){
            children.add(new GrammarNode(symbol, rule, this));
        }
        for(GrammarNode s : this.children){
            System.out.println(s.getValueAsString());
        }
    }

    public String toString() {
        setDistanceToRoot();
        StringBuilder sb = new StringBuilder();
        sb.append("( Node: " + nodeId + " | Value: " + this.value.getSymbolAsString() +  " | Children: ");
        for(GrammarNode gn : children) {
            sb.append("\n");
            for(int i = 0; i < distanceToRoot; i++){
                sb.append("    ");
            }
            sb.append(gn.toString());
        }
        sb.append(")");
        return sb.toString();
    }

    public void addKids(List<GrammarNode> nodes){
        for (Iterator<GrammarNode> it = nodes.iterator(); it.hasNext(); ){
            children.add(it.next());
        }
    }

    public void hoist() {

    }
}
