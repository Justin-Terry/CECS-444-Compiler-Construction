import symbol_table.ScopeTree;
import tree.Node;
import tree.Tree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Tree t;
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        t = new Tree();
        while(input.hasNextLine()){
            String in = input.nextLine();
            if(in.equals("p")){
                t.printTree();
            }else if(in.equals("f")){
                System.out.println(t.findNode(t.root, "1"));
            }else if(in.equals("s")){
                createSymbolTable(t);
            }else if(in.equals("")){

            } else{
                getNodes(in, t);
            }
        }
    }

    private static void getNodes(String s, Tree t) {
//        System.out.println(s);
        String type = getType(s);
        String value = getValue(s);
        String parent = getParent(s);
        String id = getId(s);
        String line = getLine(s);
        char side = getSide(s);
//        System.out.println(type + " " + value + " " + parent + " " + id + " " + side);
        Node n = new Node(id, parent, value, line, type, side);
        t.   insertNode(n);
    }
    private static String getType(String s){
        Pattern typePattern = Pattern.compile("Type:.*\\| S");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(6,str.length()-4);
            return str;
        }
        return null;
    }
    private static String getValue(String s){
        Pattern typePattern = Pattern.compile("Value:.*\\| T");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(7,str.length()-4);
            return str;
        }
        return null;
    }
            private static String getParent(String s){
        Pattern typePattern = Pattern.compile("Parent:.*\\)");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(8,str.length()-1);
            return str;
        }
        return null;
    }
    private static String getId(String s){
        Pattern typePattern = Pattern.compile("Node:.*\\| V");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(6,str.length()-4);
            return str;
        }
        return null;
    }
    private static char getSide(String s) {
        Pattern typePattern = Pattern.compile("Side:.*\\| L");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(6,str.length()-4);
            return str.charAt(0);
        }
        return 'n';
    }
    private static String getLine(String s){
        Pattern typePattern = Pattern.compile("Line:.*\\| P");
        Matcher matcher = typePattern.matcher(s);
        while(matcher.find()) {
            String str = matcher.group(0);
            str = str.substring(6,str.length()-4);
            return str;
        }
        return null;
    }
    private static void createSymbolTable(Tree t){
        ScopeTree sctTree = new ScopeTree(t);
        sctTree.display();
    }
}
