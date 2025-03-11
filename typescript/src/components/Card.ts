import { Rank, Suit, Face, Suits, Ranks, Faces, faceValues } from "./types/types";

export default class Card {
  private _suit: Suits;
  private _rank: Ranks;
  private _face: Faces;

  constructor(face: Faces);
  constructor(suit: Suits, face?: Faces, rank?: Ranks);

  constructor(faceOrSuit: Faces | Suits, face?: Faces, rank?: Ranks) {
    if (faceOrSuit === "joker") {
      this._suit = this._face = "joker";
      this._rank = 0;
    } else if (typeof faceOrSuit === "string" && face) {
      this._suit = faceOrSuit as Suits;
      if (rank) {
        this._rank = rank;
      } else if (face && face !== "number") {
        this._rank = faceValues[face];
      } else {
        throw new Error(
          "Invalid parameters: Rank or Face must be defined in a Card"
        );
      }
      this._face = face || "number";
    } else {
      throw new Error(
        "Invalid parameters: Face must be defined as `joker`, else Suit and Rank or Face must be defined in a Card"
      );
    }
  }
  public get suit(): Suit | "joker" {
    return this._suit;
  }

  public getRank(currentScore?: number): Rank | Rank[] {
    if (this.face === "ace") {
      if (currentScore && currentScore <= 10) {
        return 11 as Rank;
      } else {
        return 1 as Rank;
      }
    }
    return this._rank;
  }

  public get face(): Face | "number" | "joker" {
    return this._face;
  }

  public toString(): string {
    return `Card[suit=${this.suit},rank=${this._rank},face=${
      this.face ?? this._rank
    }]`;
  }
}
