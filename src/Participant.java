/**
 * This class contains name, cash, and point storage for each
 * participant in a card game
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

public class Participant {
   private String name; // identifier
   private int cash; // cash on hand for betting
   private int score = 0; // current cumilative points

   // default constructor
   Participant(String name) {
      this.name = name;
   }

   // overloaded constructor
   Participant(String name, int cash) {
      this.name = name;
      this.cash = cash;
   }

   /**
    * Adds points to participants score and returns the result
    *
    * @return int
    */
   public int addScore(int points) {
      return this.score += points;
   }

   /**
    * Adds (+) / Subtracts (-) cash from participants cash on hand and
    * returns the result
    *
    * @return int
    */
   public int modifyCash(int cash) {
      return this.cash += cash;
   }

   /**
    * Returns the participant's name
    *
    * @return String
    */
   public String getName() {
      return this.name;
   }

   /**
    * Returns a string representation of the participants name, cash, and
    * score
    *
    * @return String
    */
   public String toString() {
      return "Name: " + name + " | Cash: $" + cash + " | Score: " + score;
   }
}
