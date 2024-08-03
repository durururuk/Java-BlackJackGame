package src.obejcts;

import src.exceptions.DeckIsEmptyException;

import java.util.List;

public class Dealer {
    private List<Card> hand;

    public void addCard(Card card) {
        hand.add(card);
    }


}
