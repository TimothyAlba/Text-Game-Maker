package parser;

public class ParseLine {
    public String Action;
    public String Data;

    public ParseLine(String value){
        if (value.indexOf(":") > -1) {
            Action = value.substring(0, value.indexOf(":")).trim();
            Data = value.substring(value.indexOf(":") + 1).trim();
        }
        else {
            Action = value.trim();
        }
    }
}
