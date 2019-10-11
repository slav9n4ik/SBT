package oop;

public class SimpleCalculator implements Calculator {
    @Override
    public String sum(String arg1, String arg2) {
        int result = Integer.parseInt(arg1) + Integer.parseInt(arg2);
        return String.valueOf(result);
    }
}
