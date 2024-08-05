package src.obejcts;

import src.Game;
import src.exceptions.DeckIsEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int balance = 5000;
    private List<Card> hand;
    Scanner sc = new Scanner(System.in);
    boolean isPlayerBurst = false;
    int pResult = 0;

    public Player() {
        this.balance = balance;
        this.hand = new ArrayList<>();
    }

    //Getter, Setter
    public void setpResult(int result) {
        this.pResult = result;
    }

    public int getpResult() {
        return pResult;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void initialize() {
        pResult = 0;
        this.hand = new ArrayList<>();
        isPlayerBurst = false;
    }

    //플레이어의 플레이
    public void play(Deck deck) throws DeckIsEmptyException, InterruptedException {
        System.out.println("플레이어가 플레이합니다.");
        Thread.sleep(250);
        System.out.println("플레이어가 뽑은 카드는 다음과 같습니다.");
        Game.showHand(hand);
        Thread.sleep(500);
        showHandResult();

        if(Game.resultCalculator(hand,0) == 21) {
            System.out.println("!!플레이어 블랙잭!!");
            pResult = Game.resultCalculator(hand, 0);
            isPlayerBurst = true;
        }

        while (!isPlayerBurst) {
            Thread.sleep(500);
            System.out.println("\n어떻게 하시겠습니까?");
            System.out.println("1.히트 2.스탠드");

            int input = sc.nextInt();
            switch (input) {
                case 1:
                    hit(deck);
                    if(Game.resultCalculator(hand,0) == 21) {
                        System.out.println("!!플레이어 블랙잭!!");
                        pResult = Game.resultCalculator(hand, 0);
                        isPlayerBurst = true;
                    }
                    break;

                case 2: {
                    System.out.println("플레이어 : 스탠드");
                    pResult = Game.resultCalculator(hand, 0);
                    isPlayerBurst = true;
                    break;
                }

                default: {
                    System.out.println("입력이 올바르지 않습니다..");
                    break;
                }
            }

        }
        Thread.sleep(500);
        System.out.println("플레이어의 최종 점수 : " + pResult);
    }

    //카드 뽑기
    public void addCard(Card card) {
        hand.add(card);
    }

    //플레이어 손패의 합계
    public void showHandResult() {
        boolean hasAce = hand.stream().anyMatch(card -> Rank.ACE.equals(card.getRank()));
        if (hasAce && Game.resultCalculator(hand,1)!=Game.resultCalculator(hand,1)) {
            System.out.printf("\n합계 : %d 또는 %d \n",Game.resultCalculator(hand,1),Game.resultCalculator(hand,1));
        } else {
            System.out.printf("\n합계 : %d \n",Game.resultCalculator(hand,0));
        }
    }

    //히트
    public void hit(Deck deck) throws DeckIsEmptyException,InterruptedException {
        System.out.println("플레이어 : 히트");
        System.out.println("플레이어가 한 장 더 드로우합니다.");
        addCard(deck.drawCard());
        Thread.sleep(1000);
        Game.showHand(hand);
        Thread.sleep(500);
        showHandResult();
        if(Game.resultCalculator(hand,0 ) > 21) {
            System.out.println("플레이어 버스트!");
            pResult = 0;
            isPlayerBurst = true;
        }
    }



}
