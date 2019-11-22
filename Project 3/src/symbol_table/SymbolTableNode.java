package symbol_table;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableNode {
    private SymbolTableNode parent;
    private Map<String, Symbol> table;


    public SymbolTableNode(){
        table = new HashMap<>();
    }

    public SymbolTableNode addSymbol(Symbol s) {
        table.put(s.getName(), s);
        return this;
    }

    public void display() {
        for(String e : table.keySet()){
            System.out.println(String.format( "%5s of %-8s", "Name: " + e, table.get(e)));
        }
    }

}
