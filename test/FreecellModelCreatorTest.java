
import org.junit.Test;

import cs3500.hw02.FreecellModel;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.MultiFreecellModel;


import static org.junit.Assert.assertEquals;

/**
 * Tests instances and methods of FreecellModelCreator class.
 */
public class FreecellModelCreatorTest {

  /**
   * Tests create in case of singlemove.
   */
  @Test
  public void create() {
    FreecellModel m1 = FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertEquals(false, m1 instanceof MultiFreecellModel);

  }

  /**
   * Tests create in case of multimove.
   */
  @Test
  public void create2() {
    FreecellModel m1 = FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    assertEquals(true, m1 instanceof MultiFreecellModel);
  }

}