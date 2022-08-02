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
      // Introduction message
      System.out.println("\n🂿🂡🃴🃞 Java CLI Blackjack 🃞🃴🂡🂿\n");
      // starts a new game
      newGame(null);
      Game.input.close();
      System.out.println("Thank you for playing!\n");
   } // end main

   /**
    * This class begins a new game as soon as the program begins. existingPlayer
    * can be populated (not null) with a player who decides to start a new game
    * after losing all of their money in thr last gamr.
    *
    * @param existingPlayer
    * @return void
    */
   public static void newGame(String existingPlayer) {
      // store the Participants and player's input name
      String playerName = existingPlayer;
      Participant player;
      Participant dealer;

      // Instantiate player (w/ name), dealer, and a shuffled deck of cards
      if (playerName == null) {
         // if this isn't a restarted/continued game, name is asked of the user
         playerName = Game.enterName();
      }
      // generate a new player and dealer
      player = new Participant(playerName, Game.STARTING_CASH);
      dealer = new Participant("Dealer");

      // keep playing game until player wants to stop
      do {
         if (player.getCash() > 0) {
            // if cash remains, keep going
            player.reset();
            dealer.reset();
            Game.playGame(player, dealer, playerName);
         } else {
            // if out of cash, start entirely new game
            System.out.println("Starting a new game with reset cash-on-hand!");
            newGame(playerName);
         }
      } while (Game.playAgain());
   }

} // end BlackjackGameSimulator class
