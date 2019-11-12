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

    public void parseInput() {
        GrammarSymbol starter = parserStack.pop();

        int ruleNumber = parseTable.fetchCell(starter.getSymbolAsString() + input.peek());
        System.out.println("Rule Number: " + ruleNumber);
        root = new GrammarNode(starter, ruleNumber);
        parseTree(root, input.peek());
        System.out.println(root);
    }

    public String nextInput() {
        System.out.println(input.toString());
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
        parserStack.push(new GrammarSymbol("Vargroup", false));
    }

    public void parseTree(GrammarNode root, String value) {
        if (!root.getValue().isTerminal()){
            System.out.println("SEARCHING FOR: " + root.getValueAsString() + input.peek());
            int ruleNumber = parseTable.fetchCell(root.getValueAsString() + input.peek());
            if (GrammarRulesRepo.getInstance().getRuleRHS(ruleNumber) != null) {
                root.addChildrenForRule(ruleNumber);
            }
            for (GrammarNode gn : root.getChildren()) {
                parseTree(gn, value);
                System.out.println("Node: " + root.getValueAsString() + " VALUE: " + input.peek());
                if(gn.getValueAsString().equals(input.peek())){
                    nextInput();
                }
            }
        }
    }
}
