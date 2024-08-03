package src.obejcts;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getCardSymbol() {
        return suit.getSymbol() + rank.getSymbol();
    }

    public int getValue() {
        return CardValues.getCardValue(rank);
    }
}
