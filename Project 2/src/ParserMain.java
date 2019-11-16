public class ParserMain {
    public static void main(String args[]) {
        Parser p = new Parser();
        p.parseInput();
        System.out.println("\n\n");
        new AST(p.getRootOfPST());
    }
}
