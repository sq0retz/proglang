package Vars;

public final class NumberValue implements Value{
    private final double value;
    public NumberValue(boolean value){
        this.value = value ? 1:0;
    }
    public NumberValue(double value){
        this.value = value;
    }
    @Override
    public double asNum() {
        return value;
    }
    @Override
    public String asStr(){
        return Double.toString(value);
    }
    @Override
    public String toString(){
        return asStr();
    }
}
