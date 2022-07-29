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

   public static String playerName;
   public static Participant player;
   public static Participant dealer;

   /**
    * This main class executes the steps through the Blackjack game
    *
    * @param args
    * @return void
    */
   public static void main(String[] args) {
      // Introduction message
      System.out.println("\n🂿🂡🃴🃞 Java CLI Blackjack 🃞🃴🂡🂿\n");
      newGame();
   } // end main

   public static void newGame() {
      // Instantiate player (w/ name), dealer, and a shuffled deck of cards
      playerName = Game.enterName();
      player = new Participant(playerName, Game.CASH);
      dealer = new Participant("Dealer");
      Game.playGame(player, dealer, playerName);
   }



} // end BlackjackGameSimulator class
