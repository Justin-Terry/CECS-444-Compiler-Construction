import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TokenInput {


    private List<String> knownTokens;
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
            String regexPattern = "\".*\"";
            String[] t = s.split("\"");
            checkForPunct(t[1]);
        }
        System.out.println("Input Stream: " + inputStream);
        return inputStream;
    }

    private void checkForPunct(String s){
        switch(s){
            case "(":
                inputStream.add("parens1");
                break;
            case ")":
                inputStream.add("parens2");
                break;
            case ";":
                inputStream.add("semi");
                break;
            case "":
                break;
            default:
                if(knownTokens.contains(s)){
                    inputStream.add(s);
                    break;
                }else{
                    inputStream.add("id");
                    break;
                }
        }

    }

    private List<String> setupTokens() {
        List<String> list = new ArrayList<>();
        list.add("kwdvars");
        list.add("kint");
        list.add("kstring");
        return list;
    }
}
