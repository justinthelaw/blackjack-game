
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
   private Stack<Card> DECK = new Stack<Card>();
   private final int CARD_AMOUNT = 4;
   // the following are coupled (1:1) arrays required to build a deck of cards
   private final String[] CARD_TYPES = { "Ace", "2", "3", "4", "5", "6",
         "7", "8", "9", "10", "Jack", "Queen", "King" };
   private final int[] CARD_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

   // default constructor
   DeckOfCards() {
      buildDeck();
      shuffleDeck();
   } // end DeckOfCards

   /**
    * Generates a new deck using available card types and amount of each
    * card
    *
    * @return void
    */
   private void buildDeck() {
      // for each card type, add 4 of that card to the deck
      for (int i = 0; i < this.CARD_TYPES.length; i++) {
         for (int j = 0; j < this.CARD_AMOUNT; j++) {
            Card card = new Card(this.CARD_TYPES[i], this.CARD_VALUES[i]);
            this.DECK.push(card);
         }
      }
   } // end buildDeck

   /**
    * Shuffles the order of cards in an existing deck
    *
    * @return void
    */
   private void shuffleDeck() {
      // randomize the order of the deck's stack of cards
      Collections.shuffle(this.DECK);
   } // end shuffleDeck

   /**
    * Returns the type of the card from the top of the stack and
    * removes that card from the stack
    *
    * @return String
    */
   public Card drawCard() {
      // remove a card from the deck stack and return the value of that card
      return this.DECK.pop();
   } // end drawCard

   /**
    * Returns a string representation of the stack of cards in their
    * current order
    *
    * @return String
    */
   public String toString() {
      return "Current Deck: " + this.DECK.toString();
   } // end toString

   /**
    * Returns the number of cards left in a deck
    *
    * @return int
    */
   public int getCurrentDeckSize() {
      return this.DECK.size();
   } // end getCurrentDeckSize

   /**
    * Returns a string representation of the types of cards in the deck
    *
    * @return String
    */
   public String getCardTypes() {
      return Arrays.toString(this.CARD_TYPES);
   } // end getCardTypes

} // end DeckOfCards class
