package src;

import src.exceptions.DeckIsEmptyException;
import src.obejcts.Dealer;
import src.obejcts.Deck;
import src.obejcts.Player;

public class Game {
    public static void main(String[] args) throws DeckIsEmptyException {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        dealer.play(deck);
    }



}
