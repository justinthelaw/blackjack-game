import Card from "./Card";
import { DeckState, Face, Rank, Suit } from "./types";
import Stack from "./data/Stack";

export default class Deck {
  private _deck: Stack<Card>;

  constructor() {
    let newDeck: Card[] = [];

    let suit: keyof typeof Suit;
    let face: keyof typeof Face;

    for (face in Face) {
      for (suit in Suit) {
        const newCard = new Card(suit as Suit, face as Face);
        newDeck.push(newCard);
      }
    }

    let remainingNumberedCards = [2, 3, 4, 5, 6, 7, 8, 9, 10];

    while (remainingNumberedCards.length != 0) {
      let rank: Rank = remainingNumberedCards.pop() as Rank;
      for (suit in Suit) {
        const newCard = new Card(suit as Suit, "number", rank);
        newDeck.push(newCard);
      }
    }

    newDeck.push(new Card("joker"));
    newDeck.push(new Card("joker"));

    this._deck = new Stack<Card>(newDeck);
  }

  public get deck(): Stack<Card> {
    return this._deck;
  }

  public shuffle(): void {
    let currentIndex = 0;
    let deck = this.deck.stack as Card[];

    while (currentIndex < deck.length) {
      const randomIndex = Math.floor(Math.random() * deck.length);

      const currentIndexCard: Card = deck[currentIndex];
      const randomIndexCard: Card = deck[randomIndex];

      deck[randomIndex] = currentIndexCard;
      deck[currentIndex] = randomIndexCard;

      currentIndex++;
    }

    this._deck = new Stack<Card>(deck);
  }

  public get size(): number {
    return this._deck.size;
  }

  public draw(amount: number = 1): Card[] {
    let drawnCards: Card[] = [];
    for (let i = 0; i < amount; i++) {
      let drawnCard = this.deck.remove();
      if (!drawnCard) {
        break;
      }
      drawnCards.push(drawnCard);
    }
    return drawnCards;
  }

  public toString(): string {
    let obj: DeckState = {};
    let i = 1;

    for (let card of this._deck.stack) {
      obj[`Position ${i}`] = card.toString();
      i += 1;
    }

    return JSON.stringify(obj, null, 2);
  }
}
