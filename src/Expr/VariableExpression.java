package Expr;

import Vars.Value;
import Vars.Variabl;

public class VariableExpression implements Expression{
    private final String name;

    public VariableExpression(String name){
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!Variabl.isExists(name)) throw new RuntimeException("не найдена переменная");
        return Variabl.get(name);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
