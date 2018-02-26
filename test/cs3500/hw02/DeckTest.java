package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for instances of Decks and for methods of the Deck class.
 */
public class DeckTest {
  // instances of decks, empty constructor and given list of cards.
  SuitType s1 = SuitType.HEARTS;
  ValueType v1 = ValueType.ACE;
  Card c1 = new Card(s1, v1);

  SuitType s2 = SuitType.DIAMONDS;
  ValueType v2 = ValueType.ACE;
  Card c2 = new Card(s2, v2);

  List<Card> loc1 = new ArrayList<>();
  List<Card> loc2 = new ArrayList<>();
  List<Card> loc3 = new ArrayList<>();

  /**
   * Tests for getLast, can't have any empty Deck.
   */
  @Test
  public void getLastTest() {
    loc1.add(c1);
    loc1.add(c2);
    Deck d1 = new Deck(loc1);
    assertEquals(c2, d1.getLast());
    assertNotEquals(c1, d1.getLast());
  }

  /**
   * Tests that getLast throws an exception when used on an empty Deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getLastTest2() {
    Deck d2 = new Deck();
    d2.getLast();
  }

  /**
   * Tests for addOn method.
   */
  @Test
  public void addOnTest() {
    loc1.add(c1);
    loc2.add(c1);
    loc2.add(c2);
    loc3.add(c1);
    Deck d1 = new Deck(loc1);
    Deck d2 = new Deck(loc2);
    Deck d4 = new Deck(loc3);
    Deck d3 = new Deck();
    assertEquals(d2.getCards(), d1.addOn(c2).getCards());
    assertEquals(d4.getCards(), d3.addOn(c1).getCards());
  }

  /**
   * Tests for getCards. checks that it returns an empty list from an empty deck.
   */
  @Test
  public void getCardsTest() {
    loc1.add(c1);
    loc2.add(c1);
    loc2.add(c2);
    Deck d1 = new Deck(loc1);
    Deck d2 = new Deck(loc2);
    Deck d3 = new Deck();
    assertEquals(loc1, d1.getCards());
    assertEquals(loc2, d2.getCards());
    assertEquals(new ArrayList<Card>(), d3.getCards());
  }
}