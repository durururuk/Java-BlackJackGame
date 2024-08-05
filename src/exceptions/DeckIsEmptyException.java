package src.exceptions;

public class DeckIsEmptyException extends Exception {
    public DeckIsEmptyException() {
        System.out.println("덱이 비어있습니다!");
    }

    public DeckIsEmptyException(String message) {
        super(message);
    }
}
