package lexer;

public class LexerToken {
    private int id, line, position;
    private String str;
    private Integer intValue;
    private Float floatValue;

    public LexerToken(){};

    public LexerToken(int id, String str, int line, int position) {
        this.id = id;
        this.str = str;
        this.line = line;
        this.position = position;
    }

    public LexerToken(int id, String str, int line, int position, int intValue) {
        this.id = id;
        this.str = str;
        this.line = line;
        this.position = position;
        this.intValue = intValue;
    }

    public LexerToken(int id, String str, int line, int position, float floatValue) {
        this.id = id;
        this.str = str;
        this.line = line;
        this.position = position;
        this.floatValue = floatValue;
    }

    @Override
    public String toString() {
        //(Tok: 10 lin= 1,1 str= "prog")
        StringBuilder mStringBuilder = new StringBuilder();
        mStringBuilder.append("(Tok: " + this.id + " ");
        mStringBuilder.append("lin= " + this.line + "," + this.position + " ");
        mStringBuilder.append("str= \"" + this.str + "\"");

        if(this.intValue != null) {
            mStringBuilder.append(" int= " + this.intValue);
        }
        if(this.floatValue != null) {
            mStringBuilder.append(" flo= " + this.floatValue);
        }

        mStringBuilder.append(")");
        return mStringBuilder.toString();
    };

}
