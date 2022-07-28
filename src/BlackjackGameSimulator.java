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
    // player's starting cash on hand
    public static final int CASH = 100;

    /**
     * This main class executes the steps through the Blackjack game
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // Introduction message
        System.out.println("\n🂿🂡🃴🃞 Command-Line Blackjack 🃞🃴🂡🂿\n");

        // Instantiate player (w/ name), dealer, and a shuffled deck of cards
        String playerName = UserDecisions.whatIsYourName();
        Participant player = new Participant(playerName, CASH);
        Participant dealer = new Participant("Dealer");
        DeckOfCards deck = new DeckOfCards();

        // Tells player how muhc money they start with
        System.out.println("Welcome, " + player.getName() + "! Your current cash on hand is $" + CASH + "\n");

        // Asks player to place a bet for the first round of gameplay
        int currentBet = UserDecisions.howMuchAreYouBetting(player.getCash());
        System.out.println(playerName + " has placed a $" + currentBet + " bet!\n");
    } // end main

} // end BlackjackGameSimulator class
