import java.util.ArrayList;
import java.util.List;

public final class Lexer {
    private final String input;
    private final List<Token> tokens;
    private int pos;
    private final int length;

    private static final String Op_Cahrs = "+-*/()=<>";
    private static final TokenType[] Op_Tokens = {
            TokenType.Plus, TokenType.Minus,
            TokenType.Multi, TokenType.Div,
            TokenType.P_open, TokenType.P_close,
            TokenType.Equal, TokenType.L_par,
            TokenType.R_par
    };
    public Lexer (String input){
        this.input = input;
        length = input.length();
        tokens = new ArrayList<>();
    }
    public List<Token> tokeniz(){
        while (pos<length){
            final char current = peek(0);
            if (Character.isDigit(current)) tokenizNum();
            else if (Character.isLetter(current)) tokenizWord();
            else if (current =='"') tokenizText();
            else if (Op_Cahrs.indexOf(current)!= -1){
                tokenizOp();
            } else {next();}
        }
        return tokens;
    }
    private void tokenizOp(){
        final int position = Op_Cahrs.indexOf(peek(0));
        addToken(Op_Tokens[position]);
        next();
    }
    private void tokenizWord(){
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true){
            if (!Character.isLetterOrDigit(current) &&(current !='_')&&(current !='$')){
                break;
            }
            buffer.append(current);
            current = next();
        }
        final String word = buffer.toString();
        switch (word){
            case "PRINT": addToken(TokenType.Print); break;
            case "IF": addToken(TokenType.IF); break;
            case "ELSE":addToken(TokenType.Else); break;
            case "WHILE":addToken(TokenType.While);break;
            default: addToken(TokenType.Var, word); break;
        }
    }
    private void tokenizText(){
        next();
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true){
            if (current == '\\'){
                current = next();
                switch (current){
                    case '"':current = next();buffer.append('"'); continue;
                }
                buffer.append('\\');continue;
            }
            if (current == '"') break;
            buffer.append(current);
            current = next();
        }
        next();
        addToken(TokenType.Text, buffer.toString());
    }
    private void tokenizNum(){
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true){
            if (current == '.'){
                if (buffer.indexOf(".")!=-1) throw new RuntimeException("Неверный ввод десятичного числа");
            } else if(!Character.isDigit(current)){
                break;
            }
            buffer.append(current);
            current = next();
        }
        next();
        addToken(TokenType.Num, buffer.toString());
    }
    private char next(){
        pos++;
        return peek(0);
    }
    private char peek(int relPos){
        final int position = pos + relPos;
        if (position>=length) return '\0';
        return input.charAt(position);

    }
    private void addToken(TokenType type){
        addToken(type, "");
    }
    private void addToken(TokenType type, String text){
        tokens.add(new Token(type, text));
    }
}
