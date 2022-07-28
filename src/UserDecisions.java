
/**
 * This class contains all of the decisions that can be made by the user during
 * a game of Blackjack
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

import java.util.Scanner;

public class UserDecisions {

   private static Scanner input = new Scanner(System.in);

   /**
    * Asks the player for their name
    *
    * @return String
    */
   public static String whatIsYourName() {
      System.out.print("What is your name? ");
      String playerName = input.nextLine();
      return playerName;
   } // end whatIsYourName

   /**
    * Asks the player to place a bet within their current cash on hand range, but
    * more than $0
    *
    * @param cashOnHand
    * @return int
    */
   public static int howMuchAreYouBetting(int cashOnHand) {
      System.out.print("How much would you like to bet? ");
      int bet = input.nextInt();
      // keep asking player for a bet until a correct input is provided
      while (bet > cashOnHand || bet <= 0) {
         System.out.print("That's invalid! How much would you like to bet? ");
         bet = input.nextInt();
      }
      return bet;
   } // end howMuchAreYouBetting

} // end UserDecisions class
