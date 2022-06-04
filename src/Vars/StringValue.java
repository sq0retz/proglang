package Vars;

public final class StringValue implements Value{
    private final String value;

    public StringValue(String value){
        this.value = value;
    }
    @Override
    public double asNum(){
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e){
            return 0;
        }
    }
    @Override
    public String asStr(){
        return value;
    }
    @Override
    public String toString(){
        return asStr();
    }
}
