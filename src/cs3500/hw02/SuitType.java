package cs3500.hw02;

/**
 * Enumeration representing the suits in a deck of cards.
 */
public enum SuitType {
  HEARTS('♥', 'r'), CLUBS('♣', 'b'), DIAMONDS('♦', 'r'), SPADES('♠', 'b');

  private final char symbol;
  private final char color;

  /**
   * Creates an instance of a SuitType.
   *
   * @param symbol icon of suit.
   * @param color  color of suit.
   */
  SuitType(char symbol, char color) {
    this.symbol = symbol;
    this.color = color;
  }

  /**
   * Gets the color.
   *
   * @return color char.
   */
  public char getColor() {
    return color;
  }

  /**
   * Gets the symbol.
   *
   * @return returns symbol char.
   */
  public char getSymbol() {
    return symbol;
  }

  /**
   * Is the given color different than this color.
   *
   * @param s SuitType to be compared to this.
   * @return true if the colors are the same.
   */
  public boolean diffColor(SuitType s) {
    boolean bool = false;
    switch (s) {
      case HEARTS:
        bool = (this == CLUBS) || (this == SPADES);
        break;
      case CLUBS:
        bool = (this == HEARTS) || (this == DIAMONDS);
        break;
      case DIAMONDS:
        bool = (this == CLUBS) || (this == SPADES);
        break;
      case SPADES:
        bool = (this == HEARTS) || (this == DIAMONDS);
        break;
      default:
        break;
    }
    return bool;
  }

  /**
   * Is the given suit the same as this suit.
   *
   * @param s SuitType to be compared to this.
   * @return true if the suits are the same.
   */
  public boolean sameSuit(SuitType s) {
    boolean bool = false;
    switch (s) {
      case HEARTS:
        bool = this == HEARTS;
        break;
      case CLUBS:
        bool = this == CLUBS;
        break;
      case DIAMONDS:
        bool = this == DIAMONDS;
        break;
      case SPADES:
        bool = this == SPADES;
        break;
      default:
        break;
    }
    return bool;
  }
}


