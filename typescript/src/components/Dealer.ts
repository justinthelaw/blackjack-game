import Player from "./Player";

export default class Dealer extends Player {
  constructor(name: string = "dealer") {
    super(name, 0);
  }

  public override toString() {
    return `Dealer[name=${this.name},score=${this.score},wins=${this.wins}]`;
  }
}
