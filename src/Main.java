
import Expr.Statment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Main {
    public static void main (String[] args) throws IOException{
        final String input = new String(Files.readAllBytes(Paths.get("test.txt")),"UTF-8");
        final List<Token> tokenList = new Lexer(input).tokeniz();
        for (Token token : tokenList){
            System.out.println(token);
        }
        System.out.println("///Lex end///");
        final List<Statment> statmentList = new Parser(tokenList).parse();
        for (Statment statment:statmentList){
            System.out.println(statment);
        }
        System.out.println("///Parse end \n res:");
        for (Statment statment:statmentList){
            System.out.print(' ');
            statment.execute();
        }
    }
}
