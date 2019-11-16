import java.util.HashMap;
import java.util.Map;

public class ParseTable {

    private static Map<String, Integer> table;
    private static ParseTable instance;

    private ParseTable(){
        table = new HashMap<>();
        setupTable();
    }

    public static ParseTable getInstance(){
        if(instance == null){
            instance = new ParseTable();
        }
        return instance;
    }
    public static void addCell(String pair, int ruleNumber){
        table.put(pair, ruleNumber);
    }

    public static int fetchCell(String pair) {
        if(table.containsKey(pair)) {
            return table.get(pair);
        }
        return -1;
    }

    public static void display(){
        System.out.println(table.toString());
    }

    private void setupTable() {
        table.put("Basekind" + "kint", 1);
        table.put("Basekind" + "kfloat", 2);
        table.put("Basekind" + "kstring", 3);
        table.put("Vargroup" + "kwdvars", 4);
        table.put("Vargroup" + "EPSILON", 5);
        table.put("PPvarlist" + "parens1", 6);
        table.put("Varlist"+"kint", 7);
        table.put("Varlist"+"kfloat", 7);
        table.put("Varlist"+"kstring", 7);
        table.put("Varlist"+"parens1", 7);
        table.put("Varitem"+"kint", 9);
        table.put("Varitem"+"kfloat", 9);
        table.put("Varitem"+"kstring", 9);
        table.put("Varitem"+"id", 9);
        table.put("Vardecl" + "kint", 10);
        table.put("Vardecl" + "kfloat", 10);
        table.put("Vardecl" + "kstring", 10);
        table.put("Vardecl" + "id", 10);
        table.put("Simplekind" + "kint", 11);
        table.put("Simplekind" + "kfloat", 11);
        table.put("Simplekind" + "kstring", 11);
        table.put("Varid" + "id", 13);
        table.put("Varspec" + "id", 12);
        table.put("Pgm" + "kwdprog", 14);
        table.put("Fcndefs" + "kwdfcn", 15);
        table.put("Fcndefs" + "EPSILON", 16);
        table.put("Fcndef" + "kwdfcn", 17);
        table.put("Fcnheader" + "kwdfcn", 18);
        table.put("Fcnid" + "id", 19);
        table.put("PParmlist" + "parens1", 20);
        table.put("Q" + "parens2", 21);
        table.put("Q" + "id", 22);
        table.put("Q" + "aster", 22);
        table.put("Varspecs" + "id", 23);
        table.put("More_varspecs"+ "comma", 24);
        table.put("Retkind" + "kint", 26);
        table.put("Retkind" + "kfloat", 26);
        table.put("Retkind" + "kstring", 26);
        table.put("Main" + "kwdmain", 27);
        table.put("BBlock" + "brace1", 28);
        table.put("Stmts" + "EPSILON", 999);




    }

}
