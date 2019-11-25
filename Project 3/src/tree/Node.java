package tree;

import symbol_table.SymbolTableNode;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public enum NodeType {KINT, KPLUS, ID, EQUAL, PROG, MAIN, BRACE1, BRACE2, SEMI, PARENS1, PARENS2, KPRINT };
    private NodeType type;
    private String value;
    private Node parent;
    private String parentId;
    private String id;
    private String line;
    private List<Node> leftChildren, rightChildren;
    private char side;
    private SymbolTableNode sctNode;

    public Node(String id, String parentId, String value, String line, String type, char side){
        this.id = id;
        this.parentId = parentId;
        this.value = value;
        this.type = findType(type);
        this.line = line;
        leftChildren = new ArrayList();
        rightChildren = new ArrayList();
        this.side = side;
    }

    public String getLine() {
        return line;
    }

    public NodeType getType() {
        return type;
    }

    private NodeType findType(String s) {
        if(s.equals("kint")){ return NodeType.KINT; }
        if(s.equals("kplus")){ return NodeType.KPLUS; }
        if(s.equals("id")){ return NodeType.ID; }
        if(s.equals("equal")){ return NodeType.EQUAL; }
        if(s.equals("kwdprog")){ return NodeType.PROG; }
        if(s.equals("kwdmain")){ return NodeType.MAIN; }
        if(s.equals("brace1")){ return NodeType.BRACE1; }
        if(s.equals("brace2")){ return NodeType.BRACE2; }
        if(s.equals("parens1")){ return NodeType.PARENS1; }
        if(s.equals("parens2")){ return NodeType.PARENS2; }
        if(s.equals("semi")){ return NodeType.SEMI; }
        if(s.equals("kprint")){ return NodeType.KPRINT; }
        return null;
    }

    public SymbolTableNode getSctNode() {
        return sctNode;
    }

     public void setSctNode(SymbolTableNode sctNode) {
        this.sctNode = sctNode;
    }

     public void setType(String s) {
        this.type = NodeType.valueOf(s);
    }

     public String getValue() {
        return value;
    }

     public void setValue(String value) {
        this.value = value;
    }

     public Node getParent() {
        return parent;
    }

     public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

     public  void setId(String id) {
        this.id = id;
    }

    public List<Node> getLeftChildren() {
        return this.leftChildren;
    }

    public List<Node> getRightChildren() {
        return this.rightChildren;
    }

     public void setType(NodeType type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

      @Override
    public String toString(){
        return "( " + "id: " + this.id + " | value: " + this.value + " | type: " + this.type.toString() + " | parent: " + this.parentId +
                " | side: " + this.side + " | line: " + this.line + ")";
    }

    public char getSide() {
        return side;
    }
}
