package Expr;

public final class ForStatment implements Statment{
    private final Expression expression;
    public ForStatment(Expression expression){
        this.expression = expression;
    }
    @Override
    public void execute(){
        System.out.println();
    }
    @Override public  String toString(){
        return "FOR";
    }
}
