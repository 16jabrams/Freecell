package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for instances of SuitTypes and for methods of the SuitType class.
 */
public class SuitTypeTest {
  // instances of suits.
  SuitType s1 = SuitType.HEARTS;
  SuitType s2 = SuitType.DIAMONDS;
  SuitType s3 = SuitType.SPADES;
  SuitType s4 = SuitType.CLUBS;

  /**
   * Tests for getColor method.
   */
  @Test
  public void getColorTest() {
    assertEquals('r', s1.getColor());
    assertEquals('r', s2.getColor());
    assertEquals('b', s3.getColor());
    assertEquals('b', s4.getColor());
  }

  /**
   * Tests for getSymbol method.
   */
  @Test
  public void getSymbolTest() {
    assertEquals('♥', s1.getSymbol());
    assertEquals('♦', s2.getSymbol());
    assertEquals('♠', s3.getSymbol());
    assertEquals('♣', s4.getSymbol());
  }

  /**
   * Tests for diffColor method.
   */
  @Test
  public void diffColorTest() {
    assertEquals(false, s1.diffColor(s2));
    assertEquals(true, s1.diffColor(s3));
    assertEquals(true, s1.diffColor(s4));
  }

  /**
   * Tests for sameSuit method.
   */
  @Test
  public void sameSuitTest() {
    assertEquals(true, s1.sameSuit(s1));
    assertEquals(false, s1.sameSuit(s2));
    assertEquals(false, s1.sameSuit(s3));
    assertEquals(false, s1.sameSuit(s4));
  }
}