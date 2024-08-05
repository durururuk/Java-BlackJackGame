package src;

import src.exceptions.DeckIsEmptyException;
import src.exceptions.OverBalanceBettingException;
import src.obejcts.Card;
import src.obejcts.Dealer;
import src.obejcts.Deck;
import src.obejcts.Player;

import java.util.List;
import java.util.Scanner;

public class Game {
    static int pot = 0;
    static Player player = new Player();
    static Dealer dealer = new Dealer();
    static Deck deck = new Deck();
    static Scanner sc = new Scanner(System.in);
    static boolean retry = true;

    public static void main(String[] args) throws DeckIsEmptyException, InterruptedException {

        Thread.sleep(500);
        System.out.println("[블랙잭 게임을 시작합니다..]");

        while (retry) {
            try {
                //플레이어 베팅
                System.out.printf("\n플레이어의 현재 칩은 %d개 입니다.\n", player.getBalance());
                System.out.println("얼마를 베팅하시겠습니까?");
                int input = sc.nextInt();
                try {
                    bet(input);
                } catch (OverBalanceBettingException e) {
                    System.out.println("가지고 있는 칩보다 많이 베팅 할 수는 없습니다");
                }

                Thread.sleep(500);
                //플레이어, 딜러 카드 드로우
                System.out.println("플레이어, 딜러 카드를 2장씩 드로우합니다. \n");
                player.addCard(deck.drawCard());
                player.addCard(deck.drawCard());

                dealer.addCard(deck.drawCard());
                dealer.addCard(deck.drawCard());

                System.out.println("딜러의 카드를 한 장 오픈합니다.");
                Thread.sleep(500);
                System.out.printf("[(%s)] \n\n", dealer.getHand().getFirst().getCardSymbol());

                Thread.sleep(500);
                player.play(deck);
                System.out.println();
                dealer.play(deck);
                System.out.println();
                Thread.sleep(1000);
                whoWon();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("오류가 발생했습니다.");
            } finally {
                System.out.println("[한 번 더 플레이 하시겠습니까?]");
                System.out.println("[1.예. 2.아니오.]");
                int input = sc.nextInt();
                if (input == 1) {
                    Thread.sleep(500);
                    System.out.println("한 번 더 플레이합니다.");
                    player.initialize();
                    deck.shuffle();
                    dealer.initialize();


                } else {
                    Thread.sleep(500);
                    System.out.println("게임을 종료합니다.");
                    retry = false;

                }

            }
        }


    }


    //들고있는 손패의 합계를 계산하는 메서드
    public static int resultCalculator(List<Card> hand, int ori) {
        int aceCount = 0;
        int result = 0;
        int result2;
        for (Card card : hand) {
            if (card.getRank().toString().equals("ACE")) {
                aceCount++;
            }
            result += card.getValue();
        }
        //Ace를 전부 1로 계산했을 때의 result
        result2 = result - 10 * aceCount;
        while (result > 21 && aceCount > 0) {
            result -= 10;
            aceCount--;
        }
        if (ori == 0) {
            return result;
        } else {
            return result2;
        }

    }

    //들고있는 손패를 알려주는 메서드
    public static void showHand(List<Card> hand) throws InterruptedException {
        System.out.print("[ ");
        for (Card card : hand) {
            Thread.sleep(300);
            System.out.printf("(%s) ", card.getCardSymbol());
        }
        System.out.print("]");
    }

    //베팅 메서드
    public static void bet(int chip) throws OverBalanceBettingException, InterruptedException {
        if (player.getBalance() < chip) {
            throw new OverBalanceBettingException();
        } else {
            pot = chip;
            player.setBalance(player.getBalance() - chip);
            Thread.sleep(500);
            System.out.printf("칩 %d개 베팅 하셨습니다.\n", chip);
            System.out.printf("남은 칩은 %d개 입니다. \n\n", player.getBalance());
        }
    }

    //게임의 승패를 알려주는 메서드
    public static void whoWon() {
        int result = 0;
        //플레이어가 이기는 조건
        if (player.getpResult() > dealer.getdResult()) {
            result = 1;
        } else if (player.getpResult() == dealer.getdResult()) {
            result = 2;
        } else if (player.getpResult() < dealer.getdResult()) {
            result = 3;
        }

        switch (result) {
            case 1 -> {
                System.out.println("플레이어가 이겼습니다. 베팅액만큼 칩을 더 받습니다");
                System.out.println("베팅한 칩 : " + pot);
                player.setBalance(player.getBalance() + pot * 2);
                System.out.println("남은 칩 갯수 : " + player.getBalance());
            }

            case 2 -> {
                System.out.println("비겼습니다. 베팅액만큼 칩을 돌려받습니다");
                System.out.println("베팅한 칩 : " + pot);
                player.setBalance(player.getBalance() + pot);
                System.out.println("남은 칩 갯수 : " + player.getBalance());
            }

            case 3 -> {
                System.out.println("플레이어가 졌습니다.");
                System.out.println("베팅한 칩 : " + pot);
                System.out.println("남은 칩 갯수 : " + player.getBalance());
            }

            default -> System.out.println("오류가 발생했습니다.");
        }

    }

}
