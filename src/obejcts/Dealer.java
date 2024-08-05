package src.obejcts;

import src.Game;
import src.exceptions.DeckIsEmptyException;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> hand = new ArrayList<>();
    private int dResult;

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getdResult() {
        return dResult;
    }

    public void setdResult(int dResult) {
        this.dResult = dResult;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void play(Deck deck) throws DeckIsEmptyException {
        System.out.println("딜러가 플레이합니다.");

        //카드를 두 장 드로우 합니다.
        addCard(deck.drawCard());
        addCard(deck.drawCard());

        System.out.println("딜러가 뽑은 카드는 다음과 같습니다.");
        int result = Game.resultCalculator(getHand(),0);
        System.out.println("\n 합계 : " + result);

        while (result < 17) {
            System.out.println("딜러 : 히트");
            addCard(deck.drawCard());
            result = Game.resultCalculator(getHand(),0);
            System.out.println("\n 합계 : " + result);
        }

        if (result > 21) {
            System.out.println("딜러 버스트!!");
            result = 0;
        } else {
            System.out.println("딜러 턴 종료.");
        }
        setdResult(result);
    }



}
