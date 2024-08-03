package src.obejcts;

import java.util.HashMap;
import java.util.Map;

public class CardValues {
    private static final Map<Rank, Integer> cardValues = new HashMap<>();

    static{
        cardValues.put(Rank.TWO,2);
        cardValues.put(Rank.THREE,3);
        cardValues.put(Rank.FOUR,4);
        cardValues.put(Rank.FIVE,5);
        cardValues.put(Rank.SIX,6);
        cardValues.put(Rank.EIGHT,8);
        cardValues.put(Rank.NINE,9);
        cardValues.put(Rank.TEN,10);
        cardValues.put(Rank.JACK,10);
        cardValues.put(Rank.QUEEN,10);
        cardValues.put(Rank.KING,10);
        cardValues.put(Rank.ACE,11);
    }

    public static int getCardValue(Rank rank) {
        return cardValues.get(rank);
    }
}
