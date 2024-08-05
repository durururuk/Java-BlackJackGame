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
    //플레이
    public void play(Deck deck) throws DeckIsEmptyException, InterruptedException {
        try {
            Thread.sleep(500);
            System.out.println("딜러가 플레이합니다. \n");

            System.out.println("딜러가 뽑은 카드는 다음과 같습니다.");
            Thread.sleep(500);
            Game.showHand(hand);
            Thread.sleep(500);
            int result = Game.resultCalculator(getHand(), 0);
            System.out.println("\n 합계 : " + result);

            while (result < 17) {
                Thread.sleep(1000);
                System.out.println("딜러 : 히트");
                System.out.println("딜러가 한 장 더 드로우합니다.");
                addCard(deck.drawCard());
                Thread.sleep(1000);
                Game.showHand(hand);
                result = Game.resultCalculator(getHand(), 0);
                Thread.sleep(500);
                System.out.println("\n 합계 : " + result);
            }
            Thread.sleep(1000);
            if (result > 21) {
                System.out.println("딜러 버스트!!");
                dResult = 0;
            } else {
                dResult = result;
                if(dResult == 21) {
                    System.out.println("!!딜러 블랙잭!!");
                }
                System.out.println("\n딜러의 최종 점수 : " + dResult);

            }
        } catch(InterruptedException e) {
            System.out.println("오류가 발생했습니다.");
        }

    }

    public void initialize() {
        dResult = 0;
        this.hand = new ArrayList<>();
    }


}
