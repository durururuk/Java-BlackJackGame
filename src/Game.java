package src;

import src.exceptions.DeckIsEmptyException;
import src.exceptions.OverBalanceBettingException;
import src.obejcts.Card;
import src.obejcts.Dealer;
import src.obejcts.Deck;
import src.obejcts.Player;

import java.util.List;

public class Game {
    public static void main(String[] args) throws DeckIsEmptyException {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        int pot = 0;

//        dealer.play(deck);
        player.play(deck);
    }



    //들고있는 손패의 합계를 계산하는 메서드
    public static int resultCalculator(List<Card> hand, int ori) {
        int aceCount = 0;
        int result = 0;
        int result2 = 0;
        for (Card card : hand) {
            if (card.getRank().toString().equals("ACE")) {
                aceCount++;
            }
            result += card.getValue();
        }
            //Ace를 전부 1로 계산했을 때의 result
            result2 = result - 10*aceCount;
        while (result > 21 && aceCount > 0) {
            result -= 10;
            aceCount--;
        }
        if(ori == 0) {
            return result;
        } else {
            return result2;
        }

    }

    //들고있는 손패를 알려주는 메서드
    public static void showHand(List<Card> hand) {
        System.out.print("[ ");
        for (Card card : hand) {
            System.out.printf("(%s) ", card.getCardSymbol());
        }
        System.out.print("]");
    }




}
