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


suits = ["c", "d", "h", "s"]  # clubs, diamonds, hearts, spades
ranks = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]


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


def determine_card_suit(card: Card):
    return card.suit


tuple_to_hand: dict[tuple[int, ...], str] = {
    (5,): "five of a kind",
    (4, 2): "straight flush",
    (4, 1): "four of a kind",
    (3, 2): "full house",
    (3, 1, 3): "flush",
    (3, 1, 2): "straight",
    (3, 1, 1): "three of a kind",
    (2, 2, 1): "two pair",
    (2, 1, 1, 1): "one pair",
    (1, 1, 1, 1, 1): "high card",
}


def determine_special_types(sorted_ranks, sorted_suits):
    if len(sorted_ranks) == 5:
        if max(sorted_ranks) - min(sorted_ranks) == 4:
            if len(sorted_suits) == 1:
                return (4, 2)
            return (3, 1, 2)
        if len(sorted_suits) == 1:
            return (3, 1, 3)
    else:
        return None


def determine_hand(poker_hand: list[Card]):
    sorted_ranks = tuple(
        sorted(Counter(map(determine_card_rank, poker_hand)), reverse=True)
    )
    sorted_suits = tuple(Counter(map(determine_card_suit, poker_hand)))
    rank_counts = Counter(map(determine_card_rank, poker_hand))

    if sorted_ranks == (14, 5, 4, 3, 2):
        sorted_ranks = (5, 4, 3, 2, 1)

    if 15 in sorted_ranks:
        return "JOKER!"

    type = determine_special_types(sorted_ranks, sorted_suits)

    if type is None:
        sorted_rank_counts = tuple(sorted(rank_counts.values(), reverse=True))
    else:
        sorted_rank_counts = type

    hand_type = tuple_to_hand[sorted_rank_counts]
    if hand_type == "high card":
        relative_rank = sorted_ranks[0]
    else:
        relative_rank = sorted(rank_counts)[0]

    return (hand_type, relative_rank)


def print_debug(hand):
    sorted_poker_hand = sorted(hand, key=determine_card_rank, reverse=True)
    card_counts = dict(Counter(hand))
    rank_counts = dict(Counter(map(determine_card_rank, hand)))
    sorted_rank_counts = tuple(sorted(rank_counts.values(), reverse=True))
    suit_counts = dict(Counter(map(determine_card_suit, hand)))
    print("\tSorted Poker Hand:", sorted_poker_hand)
    print("\tCard counts:", card_counts)
    print("\tRank Counts:", rank_counts)
    print("\tSorted Rank Counts:", sorted_rank_counts)
    print("\tSuit Counts:", suit_counts, "\n")


def test(hand: list[Card], expected_category: str):
    resulting_category = determine_hand(hand)

    if expected_category == "FAIL_ME":
        print("CONTROL HAND:")
        print("\tResulting Category:", resulting_category)
        print_debug(hand)
    else:
        try:
            assert expected_category == resulting_category
        except AssertionError:
            print("TEST FAILED:")
            print("\tExpected vs Result:", expected_category, "vs", resulting_category)
            print_debug(hand)


def main():
    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("h", "6"),
        Card("s", "6"),
        Card("JOKER"),
    ]
    test(test_hand, ("five of a kind", 6))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("h", "6"),
        Card("JOKER"),
        Card("JOKER"),
    ]
    test(test_hand, ("five of a kind", 6))

    test_hand = [
        Card("h", "A"),
        Card("h", "5"),
        Card("h", "4"),
        Card("h", "3"),
        Card("h", "2"),
    ]
    test(test_hand, ("straight flush", 14))

    test_hand = [
        Card("h", "6"),
        Card("h", "5"),
        Card("h", "4"),
        Card("h", "3"),
        Card("h", "2"),
    ]
    test(test_hand, ("straight flush", 6))

    test_hand = [
        Card("JOKER"),
        Card("JOKER"),
        Card("h", "9"),
        Card("h", "8"),
        Card("h", "7"),
    ]
    test(test_hand, ("straight flush", 9))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("h", "6"),
        Card("s", "6"),
        Card("s", "A"),
    ]
    test(test_hand, ("four of a kind", 6))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("h", "6"),
        Card("JOKER"),
        Card("s", "A"),
    ]
    test(test_hand, ("four of a kind", 6))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("JOKER"),
        Card("JOKER"),
        Card("s", "A"),
    ]
    test(test_hand, ("four of a kind", 6))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("h", "6"),
        Card("d", "8"),
        Card("s", "8"),
    ]
    test(test_hand, ("full house", 6))

    test_hand = [
        Card("c", "6"),
        Card("d", "6"),
        Card("JOKER"),
        Card("d", "8"),
        Card("s", "8"),
    ]
    test(test_hand, ("full house", 8))

    test_hand = [
        Card("h", "J"),
        Card("h", "A"),
        Card("h", "9"),
        Card("h", "8"),
        Card("h", "7"),
    ]
    test(test_hand, ("flush", 14))

    test_hand = [
        Card("JOKER"),
        Card("h", "A"),
        Card("h", "9"),
        Card("JOKER"),
        Card("h", "2"),
    ]
    test(test_hand, ("flush", 14))

    test_hand = [
        Card("JOKER"),
        Card("h", "A"),
        Card("h", "2"),
        Card("h", "8"),
        Card("h", "4"),
    ]
    test(test_hand, ("flush", 14))

    test_hand = [
        Card("h", "J"),
        Card("h", "10"),
        Card("c", "9"),
        Card("d", "8"),
        Card("d", "7"),
    ]
    test(test_hand, ("straight", 11))

    test_hand = [
        Card("JOKER"),
        Card("h", "10"),
        Card("c", "9"),
        Card("s", "8"),
        Card("d", "7"),
    ]
    test(test_hand, ("straight", 11))

    test_hand = [
        Card("JOKER"),
        Card("JOKER"),
        Card("s", "K"),
        Card("d", "J"),
        Card("h", "10"),
    ]
    test(test_hand, ("straight", 14))

    test_hand = [
        Card("h", "J"),
        Card("h", "9"),
        Card("c", "9"),
        Card("d", "9"),
        Card("d", "7"),
    ]
    test(test_hand, ("three of a kind", 9))

    test_hand = [
        Card("h", "J"),
        Card("h", "9"),
        Card("c", "9"),
        Card("JOKER"),
        Card("d", "7"),
    ]
    test(test_hand, ("three of a kind", 9))

    test_hand = [
        Card("JOKER"),
        Card("JOKER"),
        Card("s", "K"),
        Card("d", "J"),
        Card("h", "2"),
    ]
    test(test_hand, ("three of a kind", 13))

    test_hand = [
        Card("h", "J"),
        Card("h", "9"),
        Card("c", "9"),
        Card("d", "J"),
        Card("d", "7"),
    ]
    test(test_hand, ("two pair", 11))

    test_hand = [
        Card("h", "J"),
        Card("h", "9"),
        Card("c", "9"),
        Card("d", "K"),
        Card("d", "7"),
    ]
    test(test_hand, ("one pair", 9))

    test_hand = [
        Card("JOKER"),
        Card("d", "A"),
        Card("s", "K"),
        Card("d", "4"),
        Card("h", "2"),
    ]
    test(test_hand, ("one pair", 14))

    test_hand = [
        Card("h", "A"),
        Card("h", "J"),
        Card("c", "9"),
        Card("d", "K"),
        Card("d", "7"),
    ]
    test(test_hand, ("high card", 14))

    test_hand = [
        Card("h", "2"),
        Card("s", "9"),
        Card("c", "6"),
        Card("d", "4"),
        Card("d", "7"),
    ]
    test(test_hand, ("high card", 9))

    deck = Deck(include_joker=True)
    deck.shuffle()

    poker_hand = []
    for _ in range(5):
        drawn_card = deck.draw()
        poker_hand.append(drawn_card)

    test(poker_hand, "FAIL_ME")


if __name__ == "__main__":
    main()
