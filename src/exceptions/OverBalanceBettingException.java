package src.exceptions;

public class OverBalanceBettingException extends Exception {
    public OverBalanceBettingException() {
        System.out.println("칩이 부족합니다!");
    }
    public OverBalanceBettingException(String message) {
        super(message);
    }
}
