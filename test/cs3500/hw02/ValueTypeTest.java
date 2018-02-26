package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for instances of ValueTypes and for methods of the ValueType class.
 */
public class ValueTypeTest {
  // instances of values
  ValueType va = ValueType.ACE;
  ValueType vj = ValueType.JACK;
  ValueType v10 = ValueType.TEN;
  ValueType v5 = ValueType.FIVE;

  /**
   * Tests for getVal method.
   */
  @Test
  public void getValTest() {
    assertEquals("A", va.getVal());
    assertEquals("J", vj.getVal());
    assertEquals("10", v10.getVal());
    assertEquals("5", v5.getVal());
  }

  /**
   * Tests for valueComp method.
   */
  @Test
  public void valueCompTest() {
    assertEquals(true, vj.valueComp(v10));
    assertEquals(false, vj.valueComp(vj));
    assertEquals(false, v10.valueComp(vj));
    assertEquals(false, v5.valueComp(va));
  }
}