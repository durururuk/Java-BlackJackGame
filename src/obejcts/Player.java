package src.obejcts;

import src.Game;
import src.exceptions.DeckIsEmptyException;
import src.exceptions.OverBalanceBettingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int balance = 5000;
    private List<Card> hand;
    int aceCount = 0;
    boolean flag = true;
    Scanner sc = new Scanner(System.in);
    boolean isPlayerBurst = false;
    int pResult = 0;

    public Player() {
        this.balance = balance;
        this.hand = new ArrayList<>();
    }

    //Getter, Setter
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Card> getHand() {
        return hand;
    }

    //플레이어의 플레이
    public void play(Deck deck) throws DeckIsEmptyException{
        System.out.println("플레이어가 플레이합니다.");

        //카드를 두 장 드로우 합니다.
        addCard(deck.drawCard());
        addCard(deck.drawCard());
//        addCard(new Card(Rank.FIVE,Suit.HEARTS));
//        addCard(new Card(Rank.TWO,Suit.HEARTS));

        System.out.println("플레이어가 뽑은 카드는 다음과 같습니다.");
        Game.showHand(hand);
        showHandResult();

        while (!isPlayerBurst) {
            System.out.println("어떻게 하시겠습니까?");
            System.out.println("1.히트 2.스탠드");

            int input = sc.nextInt();
            switch (input) {
                case 1:
                    hit(deck);
                    break;
                case 2: {
                    System.out.println("플레이어 : 스탠드");
                    pResult = Game.resultCalculator(hand, 0);
                    System.out.println("플레이어의 최종 점수 : " + pResult);
                    isPlayerBurst = true;
                    break;
                }
                default: {
                    System.out.println("입력이 올바르지 않습니다..");
                    break;
                }
            }

        }





    }

    //베팅 메서드
//    public void bet(int chip) throws OverBalanceBettingException {
//        if (balance < chip) {
//            throw new OverBalanceBettingException();
//        } else {
//            balance -= chip;
//        }
//    }

    //카드 뽑기
    public void addCard(Card card) {
        hand.add(card);
    }

    //플레이어 손패의 합계
    public void showHandResult() {
        boolean hasAce = hand.stream().anyMatch(card -> Rank.ACE.equals(card.getRank()));
        if (hasAce) {
            System.out.printf("\n합계 : %d 또는 %d \n",Game.resultCalculator(hand,1),Game.resultCalculator(hand,0));
        } else {
            System.out.printf("\n합계 : %d \n",Game.resultCalculator(hand,0));
        }
    }

    //히트
    public void hit(Deck deck) throws DeckIsEmptyException {
        System.out.println("플레이어 : 히트");
        addCard(deck.drawCard());
        Game.showHand(hand);
        showHandResult();
        if(Game.resultCalculator(hand,0 ) > 21) {
            System.out.println("플레이어 버스트!");
            isPlayerBurst = true;
        }
    }



}
