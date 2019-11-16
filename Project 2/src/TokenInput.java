import java.util.*;
import java.util.regex.Pattern;

public class TokenInput {


    private Map<Integer, String> knownTokens;
    private Scanner input;
    private List<String> inputStream;

    public TokenInput() {
        input = new Scanner(System.in);
        inputStream = new ArrayList<>();
        knownTokens = setupTokens();
    }

    public List<String> getInput() {
        int c = 0;
        String str = input.nextLine();

        String[] tokens = str.split("\\)\\(");

        for(String s : tokens){
            String regexPattern = ":..?..l";
            String[] t = s.split(" ");
            checkForPunct(Integer.parseInt(t[1]));
        }
        return inputStream;
    }

    private void checkForPunct(int n){
            if(knownTokens.containsKey(n)){
                inputStream.add(knownTokens.get(n));
            }else{
                inputStream.add("id");
            }
    }

    private Map<Integer, String> setupTokens() {
        Map<Integer, String> list = new HashMap<>();
        list.put(2, "id");
        list.put(3, "kint");
        list.put(4, "kfloat");
        list.put(5, "string");
        list.put(6, "comma");
        list.put(7, "semi");
        list.put(10, "kwdprog");
        list.put(11, "kwdmain");
        list.put(12, "fcn");
        list.put(15, "kfloat");
        list.put(16, "kint");
        list.put(58, "kwdvars");
        list.put(17, "kstring");
        list.put(33, "brace1");
        list.put(34, "brace2");
        list.put(37, "parens1");
        list.put(38, "parens2");
        list.put(0, "");


//        2 id id
//        3 int myint
//        4 float myfloat
//        5 string "
//        6 comma ,
//                7 semi ;
//        10 prog prog
//        11 main main
//        12 fcn fcn
//        13 class class
//        15 float float
//        16 int int
//        18 if if
//        19 elseif elseif
//        20 else else
//        21 while while
//        22 input input
//        23 print print
//        24 new new
//        25 return return
//                26 var var
//        31 angle1 <
//        32 angle2 >
//                33 brace1 {
//            34 brace2 }
//        35 bracket1 [
//                36 bracket2 ]
//        37 parens1 (
//                38 parens2 )
//        41 aster *
//                42 caret ^
//                43 colon :
//        44 dot .
//        45 equal =
//                46 minus -
//                47 plus +
//                48 slash /
//                49 slash &
//                51 oparrow ->
//                52 opeq ==
//                53 opne !=
//                54 ople <=
//                55 opge >=
//                56 opshl <<
//                57 opshr >>
//                58 kwdvars kvars
//        99 error myerror
//        0 eof $
        return list;
    }
}
