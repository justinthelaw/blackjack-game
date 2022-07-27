# Blackjack

To run this program, either double-click on the jar file as an executable or execute `java -jar ${InsertYourDirectoryHere}/blackjack.jar` in your terminal. You will need a computer system with Java 7 or greater SE edition run-time and JDK. You may optionally use a Java IDE for example NetBeans, Eclipse, VSCode (w/ Java extensions) etc.

## Program Specification _(Re-Written)_

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

### Diagram

Below is a sequence diagram based on the re-write version of the program specification. A UML diagram was not built due to the low number of classes needed and the simplicity in class relationships relative to the complexity of the program execution's sequence of events.

The sequence diagram was built using [mermaid.js](https://mermaid-js.github.io/mermaid/#/), a markdown language for building Unified Modelling Language (UML) Diagrams, Entity Relationship Diagrams (ERDs), Sequence Diagrams and more.

The mermaid diagram below only builds in the VSCode previewer but not when it is live on Github. Please see [Blackjack-Diagram](Blackjack-Diagram.PNG) for the rendering of the diagram.

```
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

### Discussion

What is the current design approach?

Please refer to the javadoc comments within each file for details on the functionality of each class and its methods. Alternatively, the javadoc comments can be exported into a javadoc for organizing and viewing in a web browser.

There are several major chunks of the game that are needed to create a functional:

1. _Deck of Cards_: This is an object that stores the deck (52 cards), the standard/set values of the cards, and the adjustable value of the Ace. It will also store the methods for manipulating the deck, namely the use of the Random
2. _Random Deck Generator_: This is the functional component that is capable of generating a standard deck of cards in a random order for the start of the game. The output would be an an object described in the _Deck of Cards_.
3. _Participant_: This is the superclass for the Player (user) and the Dealer (computer), used to store their decided value of the Ace, their current cards-in-hand value, and their current money (if applicable).
4. _Black Jack Game_: This is the functional component of the game that contains the main() block, where a majority of the instantiations and method-calls are being performed by the program. Here, the game runs from beginning, to middle, to end, and then either jumps back to beginning or ends the program based on the user's choice.
5. _User Decisions_: This functional component houses all of the possible choices the user can be prompted to make. This provides a way to centralize decision-oriented components that could be re-usable, and to abstract more lines of code away from the main _Black Jack Game_.

What alternative design approaches were considered and why were they rejected?

- There is a possibility that the _Black Jack Game_ implements and defines all the _User Decisions_ inside of itself; however, abstracting away complexity into other chunks of the overall program makes it easier to read and comprehend each individual component of the program.
- The _Deck of Cards_ could be combined with the _Random Deck Generator_, however, I believe that it would be easier to manage smaller components with less variables and methods. _Random Deck Generator_ is a complex enough function to warrant its own file and location in the program.
- _Participant_ can also be split into a explicit, hard-coded objects, Dealer and Player. This approach was not ideal and would make the game not extensible. There is a possibility that there are multiple players playing against the dealer concurrently, like in a Casino setting, so it would make more sense to instantiate those players and dealers as instances of _Participant_.

## Implementation Output

Below is an output in the VSCode Integrated Terminal.

<img src="./BlackJack-Completed.png" alt="./BlackJack-Completed.png">

## Implementation Code

The program was written in VSCode on WSL 2, Ubuntu 20.04.4 LTS. Please refer to [./src](./src) for code.
