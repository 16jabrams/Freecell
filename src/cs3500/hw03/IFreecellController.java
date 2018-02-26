package cs3500.hw03;

import java.util.List;
import cs3500.hw02.FreecellOperations;

/**
 * This is the interface of the Freecell Controller. It is parameterized over the
 * card type, i.e. when you implement it, you can substitute K with your
 * implementation of a card.
 */
public interface IFreecellController<K> {
  /**
   * This method should run a new game of Freecell using the provided model, number of cascade
   * and open piles and the provided deck. If "shuffle" is set to false, the deck must be used
   * as-is, else the deck should be shuffled. To do so, it should ask the provided model to start
   * a game with the provided parameters, and then “run” the game in the following sequence
   * until the game is over:
   * Transmit game state to the Appendable object exactly as the model provides it.
   * If the game is ongoing, wait for user input from the Readable object.
   * A valid user input for a move is a sequence of three inputs (separated by spaces or newlines):
   * The source pile (e.g., "C1", as a single word).
   * (The pile number begins at 1, so that it is more human-friendly).
   * The card index, again with the index beginning at 1.
   * The destination pile (e.g., "F2", as a single word). (The pile number is again counted from 1).
   * The controller will parse these inputs and pass the information on to the model to make the
   * move.
   * If the game has been won, the method should transmit the final game state, and a message
   * "Game over." on a new line and return.
   *
   * @param deck         deck to be used
   * @param model        freecell model to be used
   * @param numCascades  number of cascade piles
   * @param numOpens     number of open piles
   * @param shuffle      if true, shuffle the deck, else leave as is
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
                int numOpens, boolean shuffle);
}
