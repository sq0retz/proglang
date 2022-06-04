package Expr;
import Vars.Value;
import Vars.NumberValue;
import Vars.StringValue;
public final class ConditionalExpression implements Expression{
    private Expression expr1, expr2;
    private final char operation;
    public ConditionalExpression(char operation, Expression expr1,Expression expr2){
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 =expr2;
    }
    @Override
    public Value eval(){
        final Value val1 = expr1.eval();
        final Value val2 = expr2.eval();
        if (val1 instanceof StringValue){
            final String str1 = val1.asStr();
            final String str2 = val2.asStr();
            switch (operation){
                case'<': return new NumberValue(str1.compareTo(str2)<0);
                case '>':return new NumberValue(str1.compareTo(str2)>0);
                case '=': default:return new NumberValue(str1.equals(str2));
            }
        }
        final double num1 = val1.asNum();
        final double num2 = val2.asNum();
        switch (operation){
            case '<':return new NumberValue(num1<num2);
            case '>':return  new NumberValue(num1>num2);
            case '=': default:return new NumberValue(num1 == num2);
        }
    }
    @Override
    public String toString(){
        return String.format("%s %c %s",expr1,operation,expr2);
    }
}
