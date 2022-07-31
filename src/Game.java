
/**
 * This class contains all of the Blackjack game logic and possible user decisions
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

import java.util.Scanner;

public class Game {

   // player's starting cash on hand
   // accessible by the main function
   public static final int STARTING_CASH = 100;

   // tracks whether it's the participant's 1st turn
   public static Boolean playerFirstTurn = false;
   public static Boolean dealerFirstTurn = false;

   // scanner for grabbing user input
   private static Scanner input = new Scanner(System.in);

   // variable to store the participants and deck instances
   private static Participant player;
   private static Participant dealer;
   private static DeckOfCards deck;

   // variable to store player's entered bet
   private static int bet;

   /**
    * Kicks off the blackjack game with an instantiated player, the player's name,
    * and a dealer
    *
    * @param playerInstance
    * @param dealerInstance
    * @param playerName
    * @return void
    */
   public static void playGame(Participant playerInstance, Participant dealerInstance, String playerName) {
      player = playerInstance;
      dealer = dealerInstance;
      // generate a new, shuffled deck of 52 cards
      deck = new DeckOfCards();

      // starting message based on whether it is player's first draws
      String message = !playerFirstTurn ? "Welcome, " + playerName : "Let's continue";

      // Tells player how muhc money they start with
      System.out.println(message + "! Your current cash on hand is $" + player.getCash() + "\n");

      // Asks player to place a bet for the first round of gameplay
      bet = Game.enterBet(player.getCash());
      System.out.println(playerName + " has placed a $" + bet + " bet!\n");

      // Automatically draws the player's first 2 cards and beings game
      Game.askToDrawCard();
      playerFirstTurn = true;

      while ((player.getScore() < 21) && !dealerFirstTurn) {
         // continue by asking player if they would like to draw another card
         Game.askToDrawCard();
      }

      // compare score only if both have drawn and stayed
      if ((player.getScore() < 21) && (dealer.getScore() < 21)) {
         System.out.println(compareScore());
      }
   } // end playGame

   /**
    * Asks the player for their name
    *
    * @return String
    */
   public static String enterName() {
      System.out.print("What is your name? ");
      String playerName = input.nextLine();
      return playerName;
   } // end enterName

   /**
    * Asks the player to place a bet within their current cash on hand range, but
    * more than $0
    *
    * @param cashOnHand
    * @return int
    */
   public static int enterBet(int cashOnHand) {
      System.out.print("How much would you like to bet? ");
      int bet = input.nextInt();
      // keep asking player for a bet until a correct input is provided
      while (bet > cashOnHand || bet <= 0) {
         System.out.print("That's invalid! How much would you like to bet? ");
         bet = input.nextInt();
      }
      if (playerFirstTurn) {
         input.nextLine();
      }
      return bet;
   } // end enterBet

   /**
    * Asks the participant if they want to draw another card. A dealer (computer)
    * keeps drawing until the score is over 17.
    *
    * @return void
    */
   public static void askToDrawCard() {
      if (playerFirstTurn) {
         System.out.print("Would you like to draw another card, (Yes or No)? ");
         String answer = input.nextLine();
         if (answer.toLowerCase().equals("yes")) {
            drawCardAndScore(player);
         } else {
            System.out.println("You have decided to stay! It is now the Dealer's turn.");
            dealerFirstTurn = true;
            while (dealer.getScore() < 17) {
               // dealer continues to draw until score is over 17
               drawCardAndScore(dealer);
            }
         }
      } else {
         // if its the player's first turn, immediately draw 2 cards
         input.nextLine();
         System.out.println("Drawing your first 2 cards!");
         drawCardAndScore(player);
         drawCardAndScore(player);
      }
   } // end askToDrawCard

   /**
    * Draws a card for the participant and begins logic-tree for scoring and
    * determining
    * if there is a winner or loser
    *
    * @param participant
    * @return void
    */
   public static void drawCardAndScore(Participant participant) {
      String name = participant.getName();
      // draw a card and print result
      System.out.println(name + " draws a card...");
      Card drawnCard = deck.drawCard();
      System.out.println("It's a " + drawnCard.getType() + ", which is worth " + drawnCard.getValue() + " points!");

      // check to see if its their first ace, and ask for the value
      if (drawnCard.getType() == "Ace" && Game.checkIfFirstAce(participant)) {
         if (name != "Dealer") {
            System.out.print("This is your first Ace! What is the value of an Ace"
                  + " for the rest of this game? ");
            participant.setAceValue(input.nextInt());
            input.nextLine();
         } else {
            // simple determination for Dealer Ace value
            int value = participant.getScore() < 11 ? 11 : 1;
            participant.setAceValue(value);
         }
      }
      // modify cards in participant hand
      participant.modifyCards(drawnCard);
      // check the score of the cards in hand
      System.out.println(Game.checkIfScoreIsOver(participant));
   } // end drawCardAndScore

   /**
    * Loops through the participant's hand to see if it's their first Ace
    *
    * @param participant
    * @return Boolean
    */
   public static Boolean checkIfFirstAce(Participant participant) {
      // check each card in hand for a pre-existing Ace
      for (Card card : participant.cards) {
         if (card.getType() == "Ace") {
            return false;
         }
      }
      return true;
   } // end checkIfFirstAce

   /**
    * Checks the participant score to see if it went over
    *
    * @param participant
    * @return String
    */
   public static String checkIfScoreIsOver(Participant participant) {
      int score = participant.calculateScore();
      String name = participant.getName();
      String message = "\n" + name + " has a score of " + score;
      // check to see if the score went over
      if (score < 21) {
         return message + "!\n";
      } else if (score > 21) {
         String losing = message + ", which is over 21!\n";
         // check who lost, adjust message, modify player cash by bet
         if (name == "Dealer") {
            player.modifyCash(bet);
            return losing + player.getName() + " won! 🥳📈\n";
         } else {
            player.modifyCash(-bet);
            return losing + name + " Loses! 😢📉\n";
         }
      } else {
         // check who won, adjust message, modify player cash by bet
         String winning = message + ", which is equal to 21!\n";
         if (name == "Dealer") {
            player.modifyCash(-bet);
            return winning + player.getName() + " lost! 😢📉\n";
         } else {
            player.modifyCash(bet);
            return winning + name + " Wins! 🥳📈\n";
         }
      }
   } // end checkIfScoreIsOver

   /**
    * Returns a game message based on a final score comparison between the player
    * and dealer
    *
    * @param player
    * @param dealer
    * @return String
    */
   public static String compareScore() {
      // score comparison and resulting message
      if (player.getScore() < dealer.getScore()) {
         player.modifyCash(-bet);
         return player.getName() + " loses!";
      } else if (player.getScore() > dealer.getScore()) {
         player.modifyCash(bet);
         return player.getName() + " wins!";
      }
      // possible tie condition
      return player.getName() + "  and the Dealer tied! No cash is awarded.";
   } // end compareScore

   public static Boolean playAgain() {
      System.out.print("Would you like to continue playing (Yes or No)? ");
      String choice = input.nextLine().toLowerCase();
      // add space befor enext message
      System.out.println();
      if (choice.equals("yes")) {
         return true;
      }
      return false;
   } // end playAgain

} // end Game class
