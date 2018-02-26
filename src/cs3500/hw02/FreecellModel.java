package cs3500.hw02;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a model of a Freecell game.
 */
public class FreecellModel implements FreecellOperations<Card> {
  protected ArrayList<Deck> cascadePiles;
  protected ArrayList<Deck> openPiles;
  protected ArrayList<Deck> foundationPiles;


  /**
   * Creates instance of FreecellModel with no parameters. Default constructor.
   */
  public FreecellModel() {
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
  }

  /**
   * Creates instance of FreecellModel using given parameters.
   *
   * @param numCascadePiles int representing the number of cascade piles in the game.
   * @param numOpenPiles int representing the number of open piles in the game.
   * @param shuffle boolean, true if the game deck will be shuffled first.
   */
  public FreecellModel(int numCascadePiles, int numOpenPiles, boolean shuffle) {
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
    startGame(this.getDeck(), numCascadePiles, numOpenPiles, shuffle);
  }

  @Override
  public List<Card> getDeck() {
    List<Card> valid = new ArrayList<>();

    List<ValueType> values = new ArrayList<>();
    values.add(ValueType.ACE);
    values.add(ValueType.TWO);
    values.add(ValueType.THREE);
    values.add(ValueType.FOUR);
    values.add(ValueType.FIVE);
    values.add(ValueType.SIX);
    values.add(ValueType.SEVEN);
    values.add(ValueType.EIGHT);
    values.add(ValueType.NINE);
    values.add(ValueType.TEN);
    values.add(ValueType.JACK);
    values.add(ValueType.QUEEN);
    values.add(ValueType.KING);

    List<SuitType> suits = new ArrayList<>();
    suits.add(SuitType.DIAMONDS);
    suits.add(SuitType.HEARTS);
    suits.add(SuitType.SPADES);
    suits.add(SuitType.CLUBS);

    for (SuitType suit : suits) {
      for (ValueType value : values) {
        valid.add(new Card(suit, value));
      }
    }
    return valid;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
    boolean dup = false;
    int dups = 0;
    for (Card c : deck) {
      for (Card d : deck) {
        if (c.isSame(d)) {
          dups = dups + 1;
        }
      }
      if (dups >= 2) {
        dup = true;
      }
      dups = 0;
    }
    if ((deck.size() != 52) || dup) {
      throw new IllegalArgumentException();
    }

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException();
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }

    for (int i = 0; i < numCascadePiles; i++) {
      cascadePiles.add(new Deck());
    }

    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new Deck());
    }

    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new Deck());
    }

    for (int i = 0; i < numCascadePiles; i++) {
      int spot = i;
      while (spot < deck.size()) {
        cascadePiles.get(i).addOn(deck.get(spot));
        spot = spot + numCascadePiles;
      }
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    Deck from = new Deck();
    switch (source) {
      case OPEN:
        if (pileNumber < 0 || pileNumber > openPiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        from = openPiles.get(pileNumber);
        if (cardIndex != (from.getCards().size() - 1)) {
          throw new IllegalArgumentException();
        }
        break;
      case CASCADE:
        if (pileNumber < 0 || pileNumber > cascadePiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        from = cascadePiles.get(pileNumber);
        if (cardIndex != (from.getCards().size() - 1)) {
          throw new IllegalArgumentException();
        }
        break;
      case FOUNDATION:
        if (pileNumber < 0 || pileNumber > foundationPiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        from = foundationPiles.get(pileNumber);
        if (cardIndex != (from.getCards().size() - 1)) {
          throw new IllegalArgumentException();
        }
        break;
      default:
        break;
    }
    Card cFrom = from.getCards().get(cardIndex);
    Deck dest;
    switch (destination) {
      case OPEN:
        if (destPileNumber < 0 || destPileNumber > openPiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        dest = openPiles.get(destPileNumber);
        if (dest.getCards().isEmpty()) {
          dest.getCards().add(cFrom);
          from.getCards().remove(cFrom);
        } else {
          throw new IllegalArgumentException();
        }
        break;
      case CASCADE:
        if (destPileNumber < 0 || destPileNumber > cascadePiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        dest = cascadePiles.get(destPileNumber);

        if (dest.getCards().size() > 0) {
          Card cToC = dest.getLast();
          if ((cToC.getValue().valueComp(cFrom.getValue()))
                  && (cToC.getSuit().diffColor(cFrom.getSuit()))) {
            dest.getCards().add(cFrom);
            from.getCards().remove(cFrom);
          }
          else {
            throw new IllegalArgumentException("cascade destination");
          }
        }
        else if (dest.getCards().isEmpty()) {
          dest.getCards().add(cFrom);
          from.getCards().remove(cFrom);
        }
        else {
          throw new IllegalArgumentException("cascade destination");
        }
        break;
      case FOUNDATION:
        if (destPileNumber < 0 || destPileNumber > foundationPiles.size() - 1) {
          throw new IllegalArgumentException();
        }
        dest = foundationPiles.get(destPileNumber);
        if (dest.getCards().size() > 0) {
          Card cToF = dest.getLast();
          if ((cFrom.getValue().valueComp(cToF.getValue()))
                  && (cToF.getSuit().sameSuit(cFrom.getSuit()))) {
            dest.getCards().add(cFrom);
            from.getCards().remove(cFrom);
          }
          else {
            throw new IllegalArgumentException("foundation destination 1");
          }
        }
        else if (dest.getCards().isEmpty() && cFrom.getValue() == ValueType.ACE) {
          dest.getCards().add(cFrom);
          from.getCards().remove(cFrom);
        }
        else {
          throw new IllegalArgumentException("foundation destination 2");
        }
        break;
      default:
        break;
    }
  }

  @Override
  public boolean isGameOver() {
    int cards = 0;
    for (Deck d : foundationPiles) {
      cards = cards + d.getCards().size();
    }
    return cards == 52;
  }

  @Override
  public String getGameState() {
    String state = "";
    if (foundationPiles.isEmpty() && openPiles.isEmpty() && cascadePiles.isEmpty()) {
      return state;
    }
    for (Deck d : foundationPiles) {
      String temp = "";
      temp = temp + "F" + (foundationPiles.indexOf(d) + 1) + ":";
      for (int i = 0; i < d.getCards().size(); i++) {
        if (i == 0) {
          temp = temp + " " + d.getCards().get(0).toString();
        } else {
          temp = temp + ", " + d.getCards().get(i).toString();
        }
      }
      temp = temp + "\n";
      state = state + temp;
    }

    for (Deck d : openPiles) {
      String temp = "";
      temp = temp + "O" + (openPiles.indexOf(d) + 1) + ":";
      if (d.getCards().isEmpty()) {
        temp = temp + "\n";
        state = state + temp;
      } else {
        temp = temp + " " + d.getCards().get(0).toString() + "\n";
        state = state + temp;
      }
    }

    for (int i = 0; i < cascadePiles.size(); i++) {
      Deck d = cascadePiles.get(i);
      String temp = "";
      temp = temp + "C" + (i + 1) + ":";
      for (int j = 0; j < d.getCards().size(); j++) {
        if (j == 0) {
          temp = temp + " " + d.getCards().get(0).toString();
        } else {
          temp = temp + ", " + d.getCards().get(j).toString();
        }
      }
      if (i == (cascadePiles.size() - 1)) {
        state = state + temp;
      } else {
        temp = temp + "\n";
        state = state + temp;
      }
    }
    return state;
  }
}
