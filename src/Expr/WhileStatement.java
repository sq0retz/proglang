package Expr;

public final class WhileStatement implements Statment{

    private final Expression expression;
    private final Statment statment;

    public WhileStatement(Expression expression, Statment statment) {
        this.expression = expression;
        this.statment = statment;
    }

    @Override
    public void execute() {
        while (expression.eval().asNum() != 0){
            statment.execute();
        }
    }
    @Override
    public String toString(){
        return "while"+' '+expression+' '+statment;
    }
}
