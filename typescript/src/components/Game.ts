import Deck from "./Deck";
import Player from "./Player";
import Dealer from "./Dealer";
import { GameState } from "./types/types";

export default class Game {
  private _player: Player;
  private _dealer: Player;
  private _deck: Deck;
  private _bet: number;

  constructor(playerName: string, startingCash: number, bet: number) {
    this._player = new Player(playerName, startingCash);
    this._dealer = new Dealer();
    this._bet = bet;

    const newDeck = new Deck();
    newDeck.shuffle();
    this._deck = newDeck;
  }

  public get deck(): Deck {
    return this._deck;
  }

  public get player(): Player {
    return this._player;
  }

  public get dealer(): Dealer {
    return this._dealer;
  }

  public step(): void {
    // do something
  }

  public toString(): string {
    let gameState: GameState = {
      players: {
        dealer: this.dealer.toString(),
        player: this.player.toString(),
      },
      remainingDeckSize: this.deck.size,
      bet: this._bet,
    };
    return JSON.stringify(gameState, null, 2);
  }
}
