package cs3500.hw02;

/**
 * This class represents a model of a playing card.
 */
public class Card {
  private final SuitType suit;
  private final ValueType value;

  /**
   * Create instance of Card.
   *
   * @param suit  suit of card.
   * @param value value of card.
   */
  public Card(SuitType suit, ValueType value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * Gets the SuitType.
   *
   * @return returns suit of card.
   */
  public SuitType getSuit() {
    return suit;
  }

  /**
   * Gets the ValueType.
   *
   * @return returns value of card.
   */
  public ValueType getValue() {
    return value;
  }

  /**
   * Checks if the cards are equal.
   *
   * @param c given card to be compared to this card.
   * @return returns true if card has same suit and value as this card.
   */
  public boolean isSame(Card c) {
    return c.suit == this.suit && c.value == this.value;
  }

  /**
   * Returns Card as string.
   *
   * @return returns of string of the card value and suit symbol for showing game status.
   */
  @Override
  public String toString() {
    return value.getVal() + suit.getSymbol();
  }
}
