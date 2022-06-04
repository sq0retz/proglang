package Expr;
import Vars.Value;
import Vars.Variabl;
public class AssignStatment implements Statment{
    private final String value;
    private final Expression expr;

    public AssignStatment(String value,Expression expr){
        this.value = value;
        this.expr = expr;
    }
    @Override
    public void execute(){
        final Value result = expr.eval();
        Variabl.set(value,result);
    }
    @Override
    public String toString(){
        return String.format("%s = %s", value,expr);
    }
}
