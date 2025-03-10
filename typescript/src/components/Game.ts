import Deck from "./Deck";
import Player from "./Player";
import Dealer from "./Dealer";
import { GameState } from "./types";

export default class Game {
  private _player: Player;
  private _dealer: Player;
  private _deck: Deck;

  constructor(playerName: string, startingCash: number) {
    this._player = new Player(playerName, startingCash);
    this._dealer = new Dealer();

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

  public toString(): string {
    let gameState: GameState = {
      players: {
        dealer: this.dealer.toString(),
        player: this.player.toString(),
      },
      deck: `Remaining Cards: ${this.deck.size}`,
    };
    return JSON.stringify(gameState, null, 2);
  }
}
