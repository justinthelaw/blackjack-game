import Player from "./Player";

export default class Dealer extends Player {
  constructor(name: string = "dealer") {
    super(name, 0);
  }
}
