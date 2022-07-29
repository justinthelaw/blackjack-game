
/**
 * This class contains all of the decisions that can be made by the user during
 * a game of Blackjack
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

import java.util.Scanner;

public class Game {
   // scanner for grabbing user input
   private static Scanner input = new Scanner(System.in);

   // player's starting cash on hand
   public static final int CASH = 100;

   // tracks the turn number for each participant
   private static int playerTurnNumber = 0;
   private static int dealerTurnNumber = 0;

   public static void playGame(Participant player, Participant dealer, String playerName) {
      DeckOfCards deck = new DeckOfCards();

      // Tells player how muhc money they start with
      System.out.println("Welcome, " + player.getName()
            + "! Your current cash on hand is $" + CASH + "\n");

      // Asks player to place a bet for the first round of gameplay
      int currentBet = Game.enterBet(player.getCash());
      System.out.println(playerName + " has placed a $" + currentBet + " bet!\n");

      // Automatically draws the player's first 2 cards and beings game
      Game.askToDrawCard(player, deck, playerTurnNumber, dealerTurnNumber);
      playerTurnNumber += 2;

      while (player.getScore() < 21) {
         // continue by asking player if they would like to draw another card
         Game.askToDrawCard(player, deck, playerTurnNumber, dealerTurnNumber);
      }

      // TODO
      // recursively continue game if
      // player has enough cash to continue and wants to continue
      playGame(player, dealer, playerName);

      // TODO
      // else {newGame()}
   }

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
      return bet;
   } // end enterBet

   public static void askToDrawCard(Participant participant, DeckOfCards deck, int playerTurnNumber,
         int dealerTurnNumber) {
      if (playerTurnNumber >= 2) {
         System.out.print("Would you like to draw another card, (Yes or No)? ");
         String answer = input.nextLine();
         if (answer.toLowerCase().equals("yes")) {
            drawCardAndScore(participant, deck);
         } else {
            // TODO
         }
      } else {
         input.nextLine();
         System.out.println("Drawing your first 2 cards!");
         drawCardAndScore(participant, deck);
         drawCardAndScore(participant, deck);
      }
   }

   public static void drawCardAndScore(Participant participant, DeckOfCards deck) {
      String name = participant.getName();

      System.out.println(name + " draws a card...");
      Card drawnCard = deck.drawCard();
      System.out.println("It's a " + drawnCard.getType() + ", which is worth " + drawnCard.getValue() + " points!");

      // check to see if its their first ace, and ask for the value
      if (drawnCard.getType() == "Ace" && Game.checkIfFirstAce(participant)) {
         System.out.println("This is your first Ace! What is the value of an Ace"
               + " for the rest of this game? ");
         participant.setAceValue(input.nextInt());
      }

      participant.modifyCards(drawnCard);
      System.out.println(Game.checkIfScoreIsOver(participant));
   }

   public static Boolean checkIfFirstAce(Participant participant) {
      // check each card in hand for a pre-existing Ace
      for (Card card : participant.cards) {
         if (card.getType() == "Ace") {
            return false;
         }
      }
      return true;
   }

   /**
    * Checks the participant score to see if it went over
    *
    * @param participant
    * @return String
    */
   public static String checkIfScoreIsOver(Participant participant) {
      int score = participant.calculateScore();
      String name = participant.getName();
      String message = name + " has a score of " + score;
      // check to see if the score went over
      if (score < 21) {
         return message + "!\n";
      } else if (score > 21) {
         return message + ", which is over 21!\n" + name + " Loses!\n";
      } else {
         return message + ", which is equal to 21!\n" + name + " Wins!\n";
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
   public static String compareScore(Participant player, Participant dealer) {
      // score omparison and resulting message
      if (player.getScore() < dealer.getScore()) {
         return player.getName() + " loses!";
      } else if (player.getScore() > dealer.getScore()) {
         return player.getName() + " wins!";
      } else {
         // this shouldn't be possible, but just in case there is an edge case
         return player.getName() + "  and the Dealer tied!";
      }
   } // end compareScore

} // end Game class
