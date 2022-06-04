package Vars;

import java.util.HashMap;
import java.util.Map;

public class Variabl {
    private static final NumberValue nul = new NumberValue(0);
    private static final Map<String, Value> vars;

    static {
        vars = new HashMap<>();

    }
    public static boolean isExists(String key) {
        return vars.containsKey(key);
    }

    public static Value get(String key) {
        if (!isExists(key)) return nul;
        return vars.get(key);
    }

    public static void set(String key, Value value) {
        vars.put(key, value);
    }
}
