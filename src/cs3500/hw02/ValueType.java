package cs3500.hw02;

/**
 * Enumeration representing the possible values of playing cards.
 */
public enum ValueType {
  ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
  SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

  private final String val;

  /**
   * Creates instance of ValueType.
   *
   * @param val value of card.
   */
  ValueType(String val) {
    this.val = val;
  }

  /**
   * Gets value.
   *
   * @return returns value string.
   */
  public String getVal() {
    return val;
  }

  /**
   * Checks if the given value is less than this value.
   *
   * @param v ValueType to be compared to this.
   * @return returns true if given value is less.
   */
  public boolean valueComp(ValueType v) {
    boolean result = false;
    switch (v) {
      case ACE:
        result = this == TWO;
        break;
      case TWO:
        result = this == THREE;
        break;
      case THREE:
        result = this == FOUR;
        break;
      case FOUR:
        result = this == FIVE;
        break;
      case FIVE:
        result = this == SIX;
        break;
      case SIX:
        result = this == SEVEN;
        break;
      case SEVEN:
        result = this == EIGHT;
        break;
      case EIGHT:
        result = this == NINE;
        break;
      case NINE:
        result = this == TEN;
        break;
      case TEN:
        result = this == JACK;
        break;
      case JACK:
        result = this == QUEEN;
        break;
      case QUEEN:
        result = this == KING;
        break;
      case KING:
        result = false;
        break;
      default:
        break;
    }
    return result;
  }
}
