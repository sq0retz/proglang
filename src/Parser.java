import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import Expr.*;
import Expr.AssignStatment;
import Expr.Statment;
public final class Parser {
    private static final Token EOF = new Token(TokenType.EOF, "");
    private final List<Token> tokenList;
    private final int size;
    private int pos;

    public Parser(List<Token> tokenList){
        this.tokenList = tokenList;
        size = tokenList.size();
    }
    private Statment statment(){
        if (match(TokenType.Print)){
            return new PrintStatment(expression());
        } if (match(TokenType.IF)){
            return ifElse();
        } if (match(TokenType.While)){
            return whileStatement();
        }
        return assignmentStatement();
    }
    private Statment assignmentStatement() {
        final Token current = get(0);
        if (match(TokenType.Var) && get(0).getType() == TokenType.Equal) {
            final String variable = current.getText();
            consume(TokenType.Equal);
            return new AssignStatment(variable, expression());
        }
        throw new RuntimeException("Неизвестное выражение");
    }

    private Statment ifElse(){
        final Expression condition = expression();
        final Statment ifstate = statment();
        final Statment elsestate;
        if (match(TokenType.Else)){
            elsestate = statment();
        } else elsestate = null;
        return new IfStatment(condition,ifstate,elsestate);
    }
    private Statment whileStatement(){
        final Expression condition = expression();
        final Statment statment = statment();
        return new WhileStatement(condition,statment);
    }
    private Expression expression(){
        return conditional();
    }
    private Expression conditional(){
        Expression res = addictive();
        while (true){
            if (match(TokenType.Equal)){
                res = new ConditionalExpression('=',res,addictive());
                continue;
            } if (match(TokenType.L_par)){
                res = new ConditionalExpression('<',res,addictive());
                continue;
            } if (match(TokenType.R_par)){
                res = new ConditionalExpression('>',res,addictive());
                continue;
            }
            break;
        }
        return res;
    }
    private Expression primary(){
        final Token current = get(0);
        if (match(TokenType.Num)){
            return new ValueExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.Text)){
            return new ValueExpression(current.getText());
        }  if (match(TokenType.Var)) {
            return new VariableExpression(current.getText());
        }
        if (match(TokenType.P_open)){
            Expression res = expression();
            match(TokenType.P_close);
            return res;
        }
        throw new RuntimeException("Неизвестное выражение");
    }
    private Expression unary(){
        if (match(TokenType.Minus)){
            return new UnarExpression('-',primary());
        } if (match(TokenType.Plus)){
            return primary();
        }
        return primary();
    }
    private Expression multipli(){
        Expression res = unary();
        while (true){
            if (match(TokenType.Multi)){
                res = new BinaryExpression('*',res,unary());
                continue;
            }
            if (match(TokenType.Div)){
                res = new BinaryExpression('/',res,unary());
                continue;
            }
            break;
        }
        return res;
    }
    private Expression addictive(){
        Expression res = multipli();
        while (true){
            if(match(TokenType.Plus)){
                res = new BinaryExpression('+',res,multipli());
                continue;
            } if (match(TokenType.Minus)){
                res = new BinaryExpression('-',res,multipli());
                continue;
            }
            break;
        }
        return res;
    }

    public List<Statment> parse(){
        final List<Statment> result = new ArrayList<>();
        while (!match(TokenType.EOF)){
          result.add(statment());
        }
        return result;
    }



    private boolean match(TokenType type){
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }
   private Token get (int relPos){
        final int position = pos + relPos;
        if (position >=size) return EOF;
        return tokenList.get(position);
   }
   private Token consume (TokenType type){
        final Token current = get(0);
        if (type != current.getType()) throw new RuntimeException("Token"+ current +"с таким типом"+type+"не найден");
        pos++;
        return current;
   }

}
