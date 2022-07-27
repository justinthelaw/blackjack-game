/**
 * This is the main class that executes the Blackjack game. It
 * instantiates the Player, Dealer, and Deck before stepping through
 * each decision that needs to be made to advance the game
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

public class BlackjackGameSimulator {

    /**
     * This main class executes the steps through the Blackjack game
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        Participant dealer = new Participant("dealer");
        Participant player = new Participant("player", 50);

        System.out.println(deck.toString());
        System.out.println(dealer.toString());
        System.out.println(player.toString());
    } // end main

} // end BlackjackGameSimulator class
