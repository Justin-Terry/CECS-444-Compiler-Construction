public class GrammarSymbol {
    private String value;
    private boolean isTerminal;

    GrammarSymbol(String value, boolean isTerminal){
        this.value = value;
        Character f = value.charAt(0);
        if(f.isLowerCase(value.charAt(0))){
            this.isTerminal = true;
        } else{
            this.isTerminal = false;
        }
    }

    public String getSymbolAsString(){
        return this.value;
    };

    public boolean isTerminal() {
        return this.isTerminal;
    }
}
