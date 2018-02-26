package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for instances of Cards and for methods of the Card class.
 */
public class CardTest {

  // Instances of Cards
  SuitType s1 = SuitType.HEARTS;
  ValueType v1 = ValueType.ACE;
  Card c1 = new Card(s1, v1);

  SuitType s2 = SuitType.DIAMONDS;
  ValueType v2 = ValueType.ACE;
  Card c2 = new Card(s2, v2);

  SuitType s3 = SuitType.SPADES;
  ValueType v3 = ValueType.JACK;
  Card c3 = new Card(s3, v3);

  SuitType s4 = SuitType.SPADES;
  ValueType v4 = ValueType.JACK;
  Card c4 = new Card(s4, v4);

  /**
   * Tests getSuit method.
   */
  @Test
  public void getSuitTest() {
    assertEquals(s1, c1.getSuit());
    assertEquals(s2, c2.getSuit());
    assertEquals(s3, c3.getSuit());
  }

  /**
   * Tests getValue method.
   */
  @Test
  public void getValueTest() {
    assertEquals(v1, c1.getValue());
    assertEquals(v2, c2.getValue());
    assertEquals(v3, c3.getValue());
  }

  /**
   * Tests toString method.
   */
  @Test
  public void toStringTest() {
    assertEquals("A♥", c1.toString());
    assertEquals("A♦", c2.toString());
    assertEquals("J♠", c3.toString());
  }

  /**
   * Tests isSame method.
   */
  @Test
  public void isSameTest() {
    assertEquals(true, c3.isSame(c4));
    assertEquals(false, c3.isSame(c1));
    assertEquals(true, c3.isSame(c3));
  }
}
