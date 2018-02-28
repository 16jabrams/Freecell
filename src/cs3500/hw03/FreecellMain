package cs3500.hw03;

import java.io.InputStreamReader;
import cs3500.hw04.MultiFreecellModel;

/**
 * Main class used for testing and running the game in the console.
 */
public class FreecellMain {
  /**
   * Main method used for running and testing.
   * @param args command line input
   */
  public static void main(String[] args) {
    FreecellController cntrl = new FreecellController(new InputStreamReader(System.in), System.out);
    MultiFreecellModel model = new MultiFreecellModel();
    cntrl.playGame(model.getDeck(), model, 8, 4, false);
  }
}
