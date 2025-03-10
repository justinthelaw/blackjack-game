/**
 * This class contains name, cash, and point storage for each
 * participant in a card game
 *
 * @author Justin Law
 * @version 1.0
 * @class: EN.605.201.82.SU22
 */

public class Participant {
   public Card[] cards = {}; // viewable by all players

   private String name; // identifier
   private int cash; // cash on hand for betting
   private int score = 0; // current cumilative points
   private int aceValue = 1; // chosen value of ace

   // default constructor
   Participant(String name) {
      this.name = name;
   } // end Participant

   // overloaded constructor
   Participant(String name, int cash) {
      this.name = name;
      this.cash = cash;
   } // end Participant

   /**
    * Calculate cards-in-hand score and returns the result
    *
    * @return int
    */
   public int calculateScore() {
      int newScore = 0;
      // increment score by each card in their hand
      for (Card card : this.cards) {
         if (card.getType() == "Ace") {
            // Ace goes by the their chosen Ace value
            newScore += this.aceValue;
         } else {
            newScore += card.getValue();
         }
      }
      this.score = newScore;
      return this.score;
   } // end calculateScore

   /**
    * Reset the turns, score and cards for new round
    *
    * @return void
    */
   public void reset() {
      this.score = 0;
      this.cards = new Card[] {};
      Game.playerFirstTurn = false;
      Game.dealerFirstTurn = false;
   } // end reset score

   /**
    * Adds (+) / Subtracts (-) cash from participants cash on hand and
    * returns the result
    *
    * @return int
    */
   public int modifyCash(int cash) {
      return this.cash += cash;
   } // end modifyCash

   /**
    * Set's the participant's Ace value
    *
    * @return void
    */
   public void setAceValue(int value) {
      this.aceValue = value;
   } // end aceValue

   /**
    * Adds a card to the participant's hand, and then
    * print the cards in their hand
    *
    * @return String
    */
   public String modifyCards(Card drawnCard) {
      int size = cards.length;
      int newSize = size + 1;
      Card[] newHand = new Card[newSize];
      for (int i = 0; i < this.cards.length; i++) {
         newHand[i] = this.cards[i];
      }
      newHand[size] = drawnCard;
      this.cards = newHand;
      return this.cards.toString();
   } // end modifyCards

   /**
    * Returns the participant's name
    *
    * @return String
    */
   public String getName() {
      return this.name;
   } // end getName

   /**
    * Returns the participant's cash on hand
    *
    * @return int
    */
   public int getCash() {
      return this.cash;
   } // end getCash

   /**
    * Returns the participant's score on hand
    *
    * @return int
    */
   public int getScore() {
      return this.score;
   } // end getScore

   /**
    * Returns a string representation of the participants name, cash, and
    * score
    *
    * @return String
    */
   public String toString() {
      return "Name: " + name + " | Cash: $" + cash + " | Score: " + score +
            " | Ace Value: " + aceValue + " | Cards: " + cards.toString();
   } // end toString
} // end Participant class
