package src.obejcts;

import src.exceptions.OverBalanceBettingException;

import java.util.List;

public class Player {
    private int balance = 5000;
    private List<Card> hand;

    public Player() {
        this.balance = balance;
        this.hand = hand;
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

    //베팅 메서드
    public void bet (int chip) throws OverBalanceBettingException {
        if (balance < chip) {
            throw new OverBalanceBettingException();
        } else {
            balance -= chip;
        }
    }

    //카드 뽑기
    public void addCard(Card card) {
        hand.add(card);
    }
}
