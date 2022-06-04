package Expr;
import Vars.NumberValue;
import Vars.Value;
import Vars.StringValue;
public final class ValueExpression implements Expression{
    private final Value val;
    public ValueExpression(double val){
        this.val = new NumberValue(val);

    }
    public ValueExpression(String val){
        this.val = new StringValue(val);
    }
    @Override
    public Value eval(){
        return val;
    }
    @Override
    public String toString(){
        return val.toString();
    }
}
