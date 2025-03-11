import Game from "./components/Game";

const args = process.argv.slice(2);

let playerName: string;
let startingCash: number;
let bet: number;

if (
  args.length !== 3 ||
  args[0].trim().length === 0 ||
  args[1].trim().length === 0 ||
  args[2].trim().length === 0 ||
  parseInt(args[1], 10) === 0 ||
  (parseFloat(args[2]) < 0 && parseFloat(args[2]) > 1)
) {
  throw new Error(
    "A player name (string), starting cash amount (integer, greater than 0), and bet ratio amount (float, between 0-1) must be supplied."
  );
} else {
  playerName = args[0];
  startingCash = parseInt(args[1], 10);
  bet = parseFloat(args[2]);
}

const game = new Game(playerName, startingCash, bet);

console.log(game.toString());
