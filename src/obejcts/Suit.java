package src.obejcts;

public enum Suit {
    HEARTS("h"), DIAMONDS("d"), CLUBS("c"), SPADES("s");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
