/**
 * This class contains all the standards and methods for building and
 * manipulating a deck of cards without Jokers.
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

import java.util.Stack;
import java.util.Collections;
import java.util.Arrays;

public class DeckOfCards {
   private Stack<String> DECK = new Stack<String>();
   private final int CARD_AMOUNT = 4;
   private final String[] CARD_TYPES = { "Ace", "2", "3", "4", "5", "6",
         "7", "8", "9", "10", "Jack", "Queen", "King" };

   // default constructor
   DeckOfCards() {
      buildDeck();
      shuffleDeck();
   }

   /**
    * Generates a new deck using available card types and amount of each
    * card
    *
    * @return void
    */
   private void buildDeck() {
      for (String card : this.CARD_TYPES) {
         for (int i = 0; i < this.CARD_AMOUNT; i++) {
            this.DECK.push(card);
         }
      }
   }

   /**
    * Shuffles the order of cards in an existing deck
    *
    * @return void
    */
   private void shuffleDeck() {
      Collections.shuffle(this.DECK);
   }

   /**
    * Returns the type of the card from the top of the stack and
    * removes that card from the stack
    *
    * @return String
    */
   public String drawCard() {
      return this.DECK.pop();
   }

   /**
    * Returns a string representation of the stack of cards in their
    * current order
    *
    * @return String
    */
   public String toString() {
      return "Current Deck: " + this.DECK.toString();
   }

   /**
    * Returns the number of cards left in a deck
    *
    * @return int
    */
   public int getCurrentDeckSize() {
      return this.DECK.size();
   }

   /**
    * Returns a string representation of the types of cards in the deck
    *
    * @return String
    */
   public String getCardTypes() {
      return Arrays.toString(this.CARD_TYPES);
   }

}
