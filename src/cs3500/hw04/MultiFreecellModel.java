package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.Deck;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * This class represents a model of a MultiFreecell game.
 */
public class MultiFreecellModel extends FreecellModel {

  /**
   * Creates instance of MultiFreecellModel with no parameters. Default constructor.
   */
  public MultiFreecellModel() {
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
  }

  /**
   * Creates instance of MultiFreecellModel using given parameters.
   *
   * @param numCascadePiles int representing the number of cascade piles in the game.
   * @param numOpenPiles int representing the number of open piles in the game.
   * @param shuffle boolean, true if the game deck will be shuffled first.
   */
  public MultiFreecellModel(int numCascadePiles, int numOpenPiles, boolean shuffle) {
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
    startGame(this.getDeck(), numCascadePiles, numOpenPiles, shuffle);
  }

  /**
   * If the move is a single card it is passed to the parent move method, else sent to multi move.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    Deck from;
    if (source == PileType.CASCADE) {
      from = cascadePiles.get(pileNumber);
      if (cardIndex != (from.getCards().size() - 1)) {
        multiMove(from, cardIndex, destination, destPileNumber);
      }
      else {
        super.move(source, pileNumber, cardIndex, destination, destPileNumber);
      }
    }
    else {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  /**
   * Deals with movement of multiple cards.
   *
   * @param from           deck from which you are moving cards
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   */
  private void multiMove(Deck from, int cardIndex, PileType destination, int destPileNumber) {
    switch (destination) {
      case OPEN:
        throw new IllegalArgumentException();
      case CASCADE:
        List<Card> toCards = cascadePiles.get(destPileNumber).getCards();
        List<Card> fromCards = from.getCards();
        List<Card> build = fromCards.subList(cardIndex, fromCards.size());
        boolean valid = validBuild(build);
        Card first = build.get(0);
        Card last = toCards.get(toCards.size() - 1);
        boolean valid2 = last.getValue().valueComp(first.getValue()) &&
                last.getSuit().diffColor(first.getSuit());
        boolean valid3 = interSlots(build);
        if (valid && valid2 && valid3) {
          toCards.addAll(build);
          fromCards.removeAll(build);
        }
        else {
          throw new IllegalArgumentException();
        }
        break;
      case FOUNDATION:
        throw new IllegalArgumentException();
      default:
        break;
    }
  }

  /**
   * Returns boolean for if the build is a valid build.
   *
   * @param cards List for the potential build.
   * @return true if the build is valid, else false
   */
  private boolean validBuild(List<Card> cards) {
    boolean valid = true;
    Card c1;
    Card c2;
    for (int i = 0; i < cards.size(); i ++) {
      if (i < cards.size() - 1) {
        c1 = cards.get(i);
        c2 = cards.get(i + 1);
        if (!c1.getValue().valueComp(c2.getValue()) || !c1.getSuit().diffColor(c2.getSuit())) {
          valid = false;
        }
      }
    }
    return valid;
  }

  /**
   * Returns boolean for if the build can be moved based on empty open and cascade piles.
   *
   * @param build List for the cards potentially being moved
   * @return true if the build is permitted to be moved based on the number of empty piles
   */
  private boolean interSlots(List<Card> build) {
    boolean valid;
    int n = 0;
    int k = 0;
    int size = build.size();
    for (Deck d: openPiles) {
      if (d.getCards().isEmpty()) {
        n = n + 1;
      }
    }
    for (Deck deck: cascadePiles) {
      if (deck.getCards().isEmpty()) {
        k = k + 1;
      }
    }
    double dub = Math.pow(2, k);
    int twok = (int) dub;
    n = n + 1;
    int max = n * twok;
    valid = (size < max) || (size == max);
    return valid;
  }
}
