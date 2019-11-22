package symbol_table;

import tree.Node;
import tree.Tree;

public class ScopeTree {
    private SymbolTableNode root;

    public ScopeTree(Tree t) {
        parseScopeTree(t.root);
    }

    private void parseScopeTree(Node n) {
        if(n == null) {
            return;
        }
        if(this.root == null){
            root = new SymbolTableNode();
        }
        for(Node lk : n.getLeftChildren()){
            parseScopeTree(lk);
        }
        for(Node rk : n.getRightChildren()){
            parseScopeTree(rk);
        }
        if(n.getType() == Node.NodeType.ID){
            SymbolTableNode link = root.addSymbol(new Symbol(n.getValue(), n.getType(), n.getLine(), n.getLeftChildren().get(0).getValue(), n));
            n.setSctNode(link);
        }
    }

    public void display() {
        root.display();
    }


}
