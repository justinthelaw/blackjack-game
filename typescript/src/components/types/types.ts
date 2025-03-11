import Deck from "../Deck";
import Player from "../Player";

export enum Suit {
  "clubs" = "♣️",
  "diamonds" = "♦️",
  "hearts" = "♥️",
  "spades" = "♠️",
}

export enum Face {
  "ace" = "ace",
  "jack" = "jack",
  "queen" = "queen",
  "king" = "king",
}

export type Rank = 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13;

export const faceValues: { [key in Face | "joker"]: Rank | Rank[] } = {
  [Face.ace]: [1, 11],
  [Face.jack]: 11,
  [Face.queen]: 12,
  [Face.king]: 13,
  joker: 0,
};

export type Suits = Suit | "joker";
export type Ranks = Rank | Rank[] | 0;
export type Faces = Face | "number" | "joker";

export interface DeckState {
  [key: string]: string;
}

export interface GameState {
  players: { dealer: string; player: string };
  remainingDeckSize: number;
  bet: number;
}
