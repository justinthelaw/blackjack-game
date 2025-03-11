from typing import Any, Counter
import random


class Card:
    rank: str | None
    suit: str

    def __init__(self, suit: str, rank: str | None = None):
        self.suit = suit
        self.rank = rank

    def __repr__(self):
        if self.suit == "JOKER":
            return f"*{self.suit}"
        else:
            return f"{self.rank}{self.suit}"


suits = ["c", "d", "h", "s"]  # clubs, diamonds, hearts, spades
ranks = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]


class Stack:
    stack: list[Any] = []

    def __init__(self, stack: list[Any]):
        self.stack = stack

    def add(self, item: Any):
        self.stack.insert(0, item)

    def remove(self):
        return self.stack.pop()

    def peek(self, index: int):
        return self.stack[index]

    def __len__(self):
        return len(self.stack)

    def __repr__(self):
        return str(self.stack)


class Deck:
    cards: Stack

    def __init__(self, include_joker: bool = False):
        cards_list: list[Card] = []

        for suit in suits:
            for rank in ranks:
                cards_list.append(Card(suit, rank))

        if include_joker:
            cards_list.append(Card("JOKER"))
            cards_list.append(Card("JOKER"))

        self.cards = Stack(cards_list)

    def count(self):
        return len(self.cards)

    def list(self):
        return self.cards

    def shuffle(self):
        current_index = 0

        while current_index < self.count():
            random_index = random.randint(0, len(self.cards) - 1)
            self.cards.stack[current_index], self.cards.stack[random_index] = (
                self.cards.stack[random_index],
                self.cards.stack[current_index],
            )
            current_index += 1

    def __repr__(self):
        return f"Deck[count={self.count()}]"

    def draw(self):
        return self.cards.remove()


def determine_card_rank(card: Card):
    rank = card.rank
    if type(rank) is int:
        return rank
    elif rank is None:
        return 15
    else:
        return ranks.index(rank) + 2


def determine_rank_hand(poker_hand_card_ranks: list[int]):
    return


def main():
    deck = Deck(include_joker=False)
    deck.shuffle()

    poker_hand = []
    for _ in range(5):
        drawn_card = deck.draw()
        poker_hand.append(drawn_card)

    print(poker_hand)

    poker_hand_card_ranks = sorted(map(determine_card_rank, poker_hand), reverse=True)

    print(poker_hand_card_ranks)

    hand_ranking = determine_rank_hand(poker_hand_card_ranks)

    print(hand_ranking)


if __name__ == "__main__":
    main()
