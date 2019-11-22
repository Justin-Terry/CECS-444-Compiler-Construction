package symbol_table;

import tree.Node;

public class Symbol {
    private String name;
    private Node.NodeType type;
    private String lineNumber;
    private String value;
    private Node sctNode;

    public Symbol(String name, Node.NodeType type, String lineNumber, String value, Node sctNode) {
        this.name = name;
        this.type = type;
        this.lineNumber = lineNumber;
        this.value = value;
        this.sctNode = sctNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node.NodeType getType() {
        return type;
    }

    public void setType(Node.NodeType type) {
        this.type = type;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getSctNode() {
        return sctNode;
    }

    public void setSctNode(Node sctNode) {
        this.sctNode = sctNode;
    }

    @Override
    public String toString() {
        return "type: " + this.type.toString() + " line: " + this.lineNumber + " value: " + this.value;
    }
}
