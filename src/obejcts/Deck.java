package src.obejcts;

import src.exceptions.DeckIsEmptyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private int currentDeckIndex = 0;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add (new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        currentDeckIndex = 0; //덱을 섞을 때 인덱스 초기화
    }

    public Card drawCard() throws DeckIsEmptyException {
        if (currentDeckIndex >= cards.size()) {
            throw new DeckIsEmptyException();
        }
        return cards.get(currentDeckIndex++);
    }

}
