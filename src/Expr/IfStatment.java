package Expr;

public final class IfStatment implements Statment{
    private final Expression expression;
    private final Statment ifStatment, elseStatment;

    public IfStatment(Expression expression,Statment ifStatment,Statment elseStatment){
        this.expression = expression;
        this.ifStatment = ifStatment;
        this.elseStatment = elseStatment;
    }

    @Override
    public void execute(){
        final double res = expression.eval().asNum();
        if (res !=0){
            ifStatment.execute();
        } else if (elseStatment != null){
            elseStatment.execute();
        }
    }
    @Override
    public String toString(){
        final StringBuilder res = new StringBuilder();
        res.append("if").append(expression).append(' ').append(ifStatment);
        if (elseStatment !=null ){
            res.append("else ").append(elseStatment);
        }
        return res.toString();
    }
}
