/**
 * This class contains all the basic construct of a card, which is its type and
 * value
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

public class Card {
   private int value;
   private String type;

   // default constructor
   Card(String type, int value) {
      this.type = type;
      this.value = value;
   } // end Card

   /**
    * A method for changing the value of a card, namely the Ace at the end of the
    * first draw for each player
    *
    * @param newValue
    */
   public void setValue(int newValue) {
      this.value = newValue;
   } // end setValue

   /**
    * Returns a string representation of the card's type and value
    *
    * @return String
    */
   public String toString() {
      return "Type: " + this.type + " | Value: " + this.value;
   } // end toString
} // end Card class
