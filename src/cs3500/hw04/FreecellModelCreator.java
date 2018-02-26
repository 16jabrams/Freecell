package cs3500.hw04;

import cs3500.hw02.FreecellModel;

/**
 * This class provides an enum class for both model types and a factory method for returning one.
 */
public class FreecellModelCreator {
  /**
   * This enum class represents the type game model types.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE
  }

  /**
   * returns either a FreecellModel or MultiFreecellmodel, depending on the GameType parameter.
   *
   * @param type GameType designating the model
   * @return the model the GameType specified
   */
  public static FreecellModel create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreecellModel();
      case MULTIMOVE:
        return new MultiFreecellModel();
      default:
        throw new IllegalArgumentException();
    }
  }
}
