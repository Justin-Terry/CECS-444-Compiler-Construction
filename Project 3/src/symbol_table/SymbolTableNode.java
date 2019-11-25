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

     public void updateSymbol(String varName, String var) {
        Symbol mapSym = table.get(varName);
        mapSym.setValue(var);
        System.out.println("Symbol Table Updated...");
        this.display();
     }

    public void display() {
        System.out.println("================= SYMBOL TABLE =================");
        for(String e : table.keySet()){
            System.out.println(String.format( "%5s %-8s", "Name: " + e, table.get(e)));
        }
        System.out.println("================================================");
    }

}
