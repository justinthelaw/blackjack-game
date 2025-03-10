export default class Player {
  private _name: string;
  private _cash: number;
  private _score: number = 0;
  private _wins: number = 0;

  constructor(name: string, cash: number = 100) {
    this._name = name;
    this._cash = cash;
  }

  public get name(): string {
    return this._name;
  }

  public set name(name: string) {
    this._name = name;
  }

  public get cash(): number {
    return this._cash;
  }

  public set cash(cash: number) {
    this._cash = cash;
  }

  public get score(): number {
    return this._score;
  }

  public set score(score: number) {
    this._score = score;
  }

  public get wins(): number {
    return this._wins;
  }

  public set wins(wins: number) {
    this._wins = wins;
  }

  public toString(): string {
    return `Player[name=${this.name},cash=${this.cash},score=${this.score},wins=${this.wins}]`;
  }
}
