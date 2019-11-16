import java.util.Iterator;
import java.util.List;

public class AST {

    private GrammarNode treeRoot;

    public AST(GrammarNode root){
        this.treeRoot = root;
        convert(root);
        display();
    }

    public void display() {
        System.out.println(treeRoot);
    }

    private void convert(GrammarNode g) {
        if(g == null) {return;}

        if(g.getChildren().size() > 0) {
            for (int i = 0; i < g.getChildren().size(); i++) {
                convert(g.getChildren().get(i));
            }
        }else{
            System.out.println(g.getValueAsString());
            if(!g.getParent().getValue().isTerminal() && g.getValue().isTerminal()){
                List<GrammarNode> nodes = g.getParent().getChildren();
                System.out.println(nodes.size());
                if(g.getParent().getParent() != null){
                    GrammarNode newParent = g.getParent().getParent();
                    newParent.getChildren().remove(g.getParent());
                    newParent.getChildren().add(g);
                    g.parent = newParent;

                } else {
                    g.parent = null;
                    treeRoot = g;
                }
                System.out.println("Parent: " + (g.parent == null ? "Null" : g.getParent().getValueAsString() ));
                nodes.remove(g);
                System.out.println(nodes.size());

                for(GrammarNode gn : nodes){
                    gn.parent = g;
                    System.out.println(gn.getValueAsString()+ "'s new parent is " + gn.getParent().getValueAsString());
                }

                g.getChildren().addAll(nodes);
                for(GrammarNode gns : g.getChildren()){
                    convert(gns);
                }
            }
        }
    }
}
