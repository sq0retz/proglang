package Expr;
import Vars.Value;
import Vars.NumberValue;
import Vars.StringValue;
public final class BinaryExpression implements Expression{
    private final Expression expr1,expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1,Expression expr2){
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    @Override
    public Value eval() {
        final Value value1 = expr1.eval();
        final Value value2 = expr2.eval();
        if (value1 instanceof StringValue) {
            final String str1 = value1.asStr();
            switch (operation) {
                case '*': {
                    final int iter = (int) value2.asNum();
                    final StringBuilder buffer = new StringBuilder();
                    for (int i = 0; i < iter; i++) {
                        buffer.append(str1);
                    }
                    return new StringValue(buffer.toString());
                }
                case '+':
                default:
                    return new StringValue(str1 + value2.asStr());
            }
        }
        final double num1 = value1.asNum();
        final double num2 = value2.asNum();
        switch (operation){
            case '-': return new NumberValue(num1-num2);
            case '*':return new NumberValue(num1*num2);
            case '/':return new NumberValue(num1/num2);
            case '+': default: return new NumberValue(num1+num2);
        }
    }
    @Override
    public String toString(){
        return String.format("%s %c $s",expr1,operation,expr2);
    }
}
