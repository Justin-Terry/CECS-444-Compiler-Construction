import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrammarRulesRepo {
    private static GrammarRulesRepo repo;
    private static Map<Integer, List<GrammarSymbol>> rules;

    private GrammarRulesRepo(){
        rules = new HashMap<>();
        addRules();
    }

    public List<GrammarSymbol> getRuleRHS(int i) {
        return rules.get(i);
    }

    public static GrammarRulesRepo getInstance() {
        if(repo == null) {
            repo = new GrammarRulesRepo();
        }
        return repo;
    }

    private void addRules() {
        rules.put(1, constructGS("kint"));
        rules.put(2, constructGS("kfloat"));
        rules.put(3, constructGS("kstring"));
        rules.put(4, constructGS("kwdvars PPvarlist"));
        //rules.put(5, constructGS("EPSILON"));
        rules.put(6, constructGS("parens1 Varlist parens2"));
        rules.put(7, constructGS("Varitem semi Varlist"));
        //rules.put(8, constructGS("EPSILON"));
        rules.put(9, constructGS("Vardecl"));
        rules.put(10, constructGS("Simplekind Varspec"));
        rules.put(11, constructGS("Basekind"));
        rules.put(12, constructGS("Varid"));
        rules.put(13, constructGS("id"));
        rules.put(14, constructGS("kwdprog Vargroup Fcndefs Main"));
        rules.put(15, constructGS("Fcndef Fcndefs"));
        //rules.put(16, constructGS("EPSILON"));
        rules.put(17, constructGS("Fcnheader BBlock"));
        rules.put(18, constructGS("kwdfcn Fcnid PParmlist Retkind"));
        rules.put(19, constructGS("id"));
        rules.put(20, constructGS("parens1 Q"));
        rules.put(21, constructGS("parens2"));
        rules.put(22, constructGS("Varspecs parens2"));
        rules.put(23, constructGS("Varspec More_varspecs"));
        rules.put(24, constructGS("comma Varspecs"));
        //rules.put(25, constructGS("EPSILON"));
        rules.put(26, constructGS("Basekind"));
        rules.put(27, constructGS("kwdmain BBlock"));
        rules.put(28, constructGS("brace1 Vargroup Stmts brace2"));
        rules.put(29, constructGS("Stmt semi Stmts"));
        //rules.put(30, constructGS("EPSILON"));
        rules.put(31, constructGS(" "));
        rules.put(32, constructGS("kvar Varspec"));
        //rules.put(999, constructGS("EPSILON"));

    }

    private ArrayList<GrammarSymbol> constructGS(String rhs){
        ArrayList<GrammarSymbol> rhsSymbols = new ArrayList<>();
        String[] values = rhs.replace(" ", ",").split(",");
        for(String value : values){
            if(Character.isLowerCase(value.charAt(0))){
                rhsSymbols.add(new GrammarSymbol(value, true));
            }else {
                rhsSymbols.add(new GrammarSymbol(value, false));
            }
        }
        return rhsSymbols;
    }
}
