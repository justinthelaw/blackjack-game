# Blackjack

## Implementations

- [Java Implementation](./java/README.md)
- [TypeScript Implementation](./typescript/README.md)

## Program Specification

Below is a re-write of the original program specification. The re-write splits up and re-organizes the original paragraph into a game rhythm. The game rules are used to define the beginning, middle, and end states of the game and program, and within each state there are steps the program must take to go from state to state.

This project involves writing a program to simulate a blackjack card game. A simple console-based user interface was created to implement this game. An object-oriented solution was used for implementing this game.

### _Beginning_

1. A simple blackjack card game consists of a player and a dealer.
   - There is only one deck of cards.
2. A player is provided with a sum of money with which to play.
3. A player can place a bet between $0 and the amount of money the player has.
4. The dealer deals cards to itself and a player.
   - A player is dealt cards, called a hand.
   - Each card in the hand has a point value.
5. The objective of the game is to get as close to 21 points as possible without exceeding 21 points.
   - A player that goes over is out of the game.
   - The dealer must play by slightly different rules than a player, and the dealer does not place bets.

### _Middle_

7. A player is dealt two cards face up.
   - It is up to Player / Dealer to decide whether the value of an Ace is 1 or 11, based on the hands they have.
   - If the point total is exactly 21 the player wins immediately.
8. If the total is not 21, the dealer is dealt two cards, one face up and one face down.
9. A player then determines whether to ask the dealer for another card (called a “hit”) or to “stay” with his/her current hand.
   - A player may ask for several “hits.”
10. When a player decides to “stay” the dealer begins to play.
11. If the dealer has 21 it immediately wins the game. Otherwise, the dealer must take “hits” until the total points in its hand is 17 or over, at which point the dealer must “stay.”
12. If the dealer goes over 21 while taking “hits” the game is over and the player wins.
13. If the dealer’s points total exactly 21, the dealer wins immediately.

### _End_

14. When the dealer and player have finished playing their hands, the one with the highest point total is the winner.
15. Play is repeated until the player decides to quit or runs out of money to bet.

## Program Design

As written in the re-write version of the Program Specification, much of the thought went behind organizing the game into a set of beginning, during, and ending states, and stepping through the decisions the player and dealer must make to advance and/or end the game.

### Assumptions

- The Joker cards (2) are removed from the deck resulting in a standard 52-card playing deck
- The "user interface" is mainly a set of Scanners seeking user input for each step of the game - no graphical user interface needs to be implemented in console
- The dealer is not a playable participant, and the user may only take the Player role
- There is only 1 player (user) and 1 dealer in the game.
- User input error handling will only loop (ask the same question) until the user enters an acceptable response
- The suit of the card does not affect gameplay and thus does not need to be represented in the code or program
- The sum of money provided to the player is hard coded and can only be changed in the code

### Diagram

Below is a sequence diagram based on the re-write version of the program specification. A UML diagram was not built due to the low number of classes needed and the simplicity in class relationships relative to the complexity of the program execution's sequence of events.

The sequence diagram was built using [mermaid.js](https://mermaid-js.github.io/mermaid/#/), a markdown language for building Unified Modelling Language (UML) Diagrams, Entity Relationship Diagrams (ERDs), Sequence Diagrams and more.

The mermaid diagram below only builds in the VSCode previewer but not when it is live on Github. Please see [Blackjack-Diagram](Blackjack-Diagram.PNG) for the rendering of the diagram.

```mermaid
sequenceDiagram
  autonumber

  participant Game
  participant Player

    break when the Player ends the game
      loop until the Player enters integer
        Game ->> Player: Prompts for cash-on-hand
        Player ->> Game: Provides cash-on-hand value
      end

        Game --> Game: Generates random deck of cards
        Game ->> Player: Instantiates w/ cash-on-hand

        break when the Player's cash-on-hand is =0 or Player ends the game
          loop until Player enters integer <= cash-on-hand, >0
            Game ->> Player: Prompts for bet
            Player ->> Game: Provides bet value
          end

          Game ->> Player: Deals 2 random cards
          Player ->> Game: Decides if Ace is worth 1 or 11

          alt Player hand is >21
            Game ->> Player: Decrements cash-on-hand by bet
            Note over Game,Player: Go to Step 5, check cash-on-hand, ask if Player wants to end game
          else Player hand <21
            Game ->> Player: Hit or Stay?

            break when Player Stays or hand is >21
              Game ->> Player: Deals 1 random card
            end

            break when hand is >=17
              Game --> Game: Deals 1 random card
            end

            alt Game (Dealer) hand >21
              Game ->> Player: Player wins! Increment cash-on-hand by bet
              Note over Game,Player: Go to Step 5, check cash-on-hand, ask if Player wants to end game
            else Game (Dealer) hand <21
              alt Game (Dealer) hand > Player hand
                Game ->> Player: Player loses!
                Note over Game,Player: Go to Step 5, check cash-on-hand, ask if Player wants to end game
              else Game (Dealer) hand < Player hand
                Game ->> Player: Player wins! Increment cash-on-hand by bet
                Note over Game,Player: Go to Step 5, check cash-on-hand, ask if Player wants to end game
              end
            end
          end
        end
    end
```
