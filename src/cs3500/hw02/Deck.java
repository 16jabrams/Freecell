package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a deck of a playing cards.
 */
public class Deck {
  private final List<Card> cards;

  /**
   * Creates instance of a Deck.
   *
   * @param cards list of cards in the deck.
   */
  public Deck(List<Card> cards) {
    this.cards = cards;
  }

  /**
   * Creates instance of a Deck without given List of Cards.
   */
  public Deck() {
    List<Card> emptyList = new ArrayList<>();
    this.cards = emptyList;
  }

  /**
   * returns the last card/ the card on top of the deck.
   *
   * @return returns the last card.
   */
  public Card getLast() {
    if (this.getCards().isEmpty()) {
      throw new IllegalArgumentException();
    } else {
      return this.cards.get(this.cards.size() - 1);
    }
  }

  /**
   * Adds card on top of deck.
   *
   * @param c Card to be added.
   * @return returns deck with card added.
   */
  public Deck addOn(Card c) {
    cards.add(c);
    return this;
  }

  /**
   * Gets the list of cards from the Deck.
   *
   * @return returns the list of cards.
   */
  public List<Card> getCards() {
    return this.cards;
  }
}
