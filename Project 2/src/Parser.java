import java.util.*;

public class Parser {
    private Stack<GrammarSymbol> parserStack;
    private TokenInput tokenInput;
    private Queue<String> input;
    private ParseTable parseTable;
    private GrammarRulesRepo rulesRepo;
    private GrammarNode root;

    Parser(){
        rulesRepo = GrammarRulesRepo.getInstance();
        parseTable = ParseTable.getInstance();
        tokenInput = new TokenInput();
        parserStack = new Stack<>();
        input = new LinkedList<>();
        startStack();
        startQueue();
    };

    public GrammarNode getRootOfPST() {
        return this.root;
    }

    public void parseInput() {
        GrammarSymbol starter = parserStack.pop();
        int ruleNumber = parseTable.fetchCell(starter.getSymbolAsString() + input.peek());
        root = new GrammarNode(starter, ruleNumber, null);
        parseTree(root, input.peek());
        removeEPS(root);
        System.out.println(root);
    }

    private void removeEPS(GrammarNode gn) {
        if(gn == null){ return; };
        for (Iterator<GrammarNode> it = gn.getChildren().iterator(); it.hasNext(); ) {
            GrammarNode g = it.next();
            if (g.getChildren().size() != 0) {
                removeEPS(g);
            }else if(!g.getValue().isTerminal()) {
                it.remove();
            }
        }
    }

    public String nextInput() {
        if(!input.isEmpty()) {
            String temp = input.peek();
            input.remove();
            return temp;
        }
        return null;
    }

    public void startQueue() {
        TreeSet<Integer> tree = new TreeSet<>();
        Set set = tree;
        for(String s : tokenInput.getInput()){
            input.add(s);
        }
    }



    public void startStack() {
        parserStack.push(new GrammarSymbol("Pgm", false));
    }

    public void parseTree(GrammarNode root, String value) {
        System.out.println("STEP: " + root.getValueAsString() + " / " + input.peek());
        if (!root.getValue().isTerminal()){
            int ruleNumber = parseTable.fetchCell(root.getValueAsString() + input.peek());
            if(ruleNumber == -1){
                ruleNumber = parseTable.fetchCell(root.getValueAsString() + "EPSILON");
            }

            if (GrammarRulesRepo.getInstance().getRuleRHS(ruleNumber) != null) {
                root.addChildrenForRule(ruleNumber);
            }

            for (GrammarNode gn : root.getChildren()) {
                parseTree(gn, value);
                if(gn.getValueAsString().equals(input.peek())){
                    nextInput();
                }
            }
        }
    }
}
