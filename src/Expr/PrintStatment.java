package Expr;

import javax.swing.*;

public final class PrintStatment implements Statment{
    private final Expression expression;
    public PrintStatment(Expression expression){
        this.expression = expression;
    }
    @Override
    public void execute(){
        System.out.print(expression.eval());
    }
    @Override
    public String toString(){
        return "PRINT" + ' ' + expression;
    }
}
