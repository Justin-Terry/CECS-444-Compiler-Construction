public class GrammarSymbol {
    private String value;
    private boolean isTerminal;

    GrammarSymbol(String value, boolean isTerminal){
        this.value = value;
        this.isTerminal = isTerminal;
    }

    public String getSymbolAsString(){
        return this.value;
    };

    public boolean isTerminal() {
        return this.isTerminal;
    }
}
