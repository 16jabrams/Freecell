
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.PileType;
import cs3500.hw02.SuitType;
import cs3500.hw02.ValueType;
import cs3500.hw04.MultiFreecellModel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the methods within MultiFreecellModel.
 */
public class MultiFreecellModelTest {
  /**
   * Tests that getDeck returns a valid deck.
   */
  @Test
  public void getDeck() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    assertEquals(d1.size(), 52);
    boolean dup = false;
    int dups = 0;
    for (Card c : d1) {
      for (Card d : d1) {
        if (c.isSame(d)) {
          dups += +1;
        }
      }
      if (dups >= 2) {
        dup = true;
      }
      dups = 0;
    }
    assertEquals(dup, false);
  }

  /**
   * Tests that startGame can shuffle the deck.
   */
  @Test
  public void startGame() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    MultiFreecellModel f2 = new MultiFreecellModel();
    List<Card> d2 = f2.getDeck();
    f1.startGame(d1, 8, 4, true);
    f2.startGame(d2, 8, 4, true);
    assertNotEquals(f1.getGameState(), f2.getGameState());
  }

  /**
   * Tests that starting the game twice in a row does not effect the piles.
   */
  @Test
  public void startGame2() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, false);
    String v1 = f1.getGameState();
    f1.startGame(d1, 8, 4, false);
    String v2 = f1.getGameState();
    assertEquals(v1, v2);
  }

  /**
   * Tests that startGame creates the correct number of piles.
   */
  @Test
  public void startGame3() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, true);
    assertEquals(true, f1.getGameState().contains("O1:\nO2:\nO3:\nO4:"));
    assertEquals(true, f1.getGameState().contains("F1:\nF2:\nF3:\nF4:"));
    assertEquals(true, f1.getGameState().contains("C8:"));
    assertEquals(false, f1.getGameState().contains("C9:"));
    assertEquals(false, f1.getGameState().contains("O5:"));
    assertEquals(false, f1.getGameState().contains("F5:"));

  }

  /**
   * Tests that startGame deals the cards in round robin fashion.
   */
  @Test
  public void startGame4() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, false);
    assertEquals(true, f1.getGameState().contains("C1: A♦"));
    assertEquals(true, f1.getGameState().contains("C2: 2♦"));
    assertEquals(true, f1.getGameState().contains("C3: 3♦"));
    assertEquals(true, f1.getGameState().contains("C4: 4♦"));
    assertEquals(true, f1.getGameState().contains("C5: 5♦"));
    assertEquals(true, f1.getGameState().contains("C6: 6♦"));
    assertEquals(true, f1.getGameState().contains("C7: 7♦"));
    assertEquals(true, f1.getGameState().contains("C8: 8♦"));
  }

  /**
   * Tests that start game throws exception when given a bad deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameBadDeck() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = new ArrayList<>();
    f1.startGame(d1, 8, 4, false);
  }

  /**
   * Tests that start game throws exception when given a bad deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameBadDeck2() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = new ArrayList<>();
    SuitType s1 = SuitType.HEARTS;
    ValueType v1 = ValueType.ACE;
    Card c1 = new Card(s1, v1);

    SuitType s2 = SuitType.DIAMONDS;
    ValueType v2 = ValueType.ACE;
    Card c2 = new Card(s2, v2);

    List<Card> loc1 = new ArrayList<>();

    loc1.add(c1);
    loc1.add(c2);
    f1.startGame(d1, 8, 4, false);
  }

  /**
   * Tests that move method moves cards to the correct places.
   */
  @Test
  public void move() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    // checks for a legal move to an open pile from a cascade pile
    assertEquals(true, f1.getGameState().contains("O2: 10♣"));
    f1.move(PileType.OPEN, 1, 0, PileType.OPEN, 4);
    // checks for a legal move to an open pile from an open pile
    assertEquals(true, f1.getGameState().contains("O2:\n"));
    assertEquals(true, f1.getGameState().contains("O5: 10♣"));
    f1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    f1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    // checks for a legal move to a foundation pile (beginning with the ace)
    assertEquals(true, f1.getGameState().contains("F1: A♣"));
    f1.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    // checks for a legal move to a foundation pile after the ace has been placed
    assertEquals(true, f1.getGameState().contains("F1: A♣, 2♣"));
    f1.move(PileType.CASCADE, 0, 4, PileType.OPEN, 9);
    f1.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 3);
    // checks for a legal move to a cascade pile
    assertEquals(true, f1.getGameState().contains("C4: 4♦, Q♦, 7♥, 2♠, 10♠, 5♣, K♣, Q♥"));
  }

  /**
   * Tests move that is invalid for invalid index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void move2() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.CASCADE, 0, 10, PileType.OPEN, 1);
  }

  /**
   * Tests move that is invalid for cascade rules.
   */
  @Test(expected = IllegalArgumentException.class)
  public void move3() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
  }

  /**
   * Tests move that is invalid for foundation rules.
   */
  @Test(expected = IllegalArgumentException.class)
  public void move4() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 1);
  }

  /**
   * Tests move that is invalid for open rules.
   */
  @Test(expected = IllegalArgumentException.class)
  public void move5() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    f1.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
  }

  /**
   * Tests move that is invalid for invalid pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void move6() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 10, false);
    f1.move(PileType.OPEN, 0, 10, PileType.OPEN, 1);
  }

  /**
   * Tests that false is returned on not finished game.
   */
  @Test
  public void isGameOver() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, false);
    assertEquals(false, f1.isGameOver());
  }

  /**
   * Tests that getGameState returns empty string before game is started.
   */
  @Test
  public void getGameState() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    assertEquals("", f1.getGameState());
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, false);
    assertNotEquals("", f1.getGameState());
  }

  /**
   * Tests that getGameState returns the correct string of the game state.
   */
  @Test
  public void getGameState2() {
    MultiFreecellModel f1 = new MultiFreecellModel();
    List<Card> d1 = f1.getDeck();
    f1.startGame(d1, 8, 4, false);
    assertEquals(f1.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♦, 9♦, 4♥, Q♥, 7♠, 2♣, 10♣\n" +
            "C2: 2♦, 10♦, 5♥, K♥, 8♠, 3♣, J♣\n" +
            "C3: 3♦, J♦, 6♥, A♠, 9♠, 4♣, Q♣\n" +
            "C4: 4♦, Q♦, 7♥, 2♠, 10♠, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♥, 3♠, J♠, 6♣\n" +
            "C6: 6♦, A♥, 9♥, 4♠, Q♠, 7♣\n" +
            "C7: 7♦, 2♥, 10♥, 5♠, K♠, 8♣\n" +
            "C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣");
  }

  /**
   * Tests that a multimove when there are not enough piles is not possible.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multiMove() {
    MultiFreecellModel model = new MultiFreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 5, 17, false);
    model.move(PileType.CASCADE, 0, 10, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 4, 9, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 2, 9, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 2, 8, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 2, 7, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 2, 6, PileType.OPEN, 5);
    model.move(PileType.CASCADE, 2, 5, PileType.OPEN, 6);
    model.move(PileType.CASCADE, 2, 4, PileType.OPEN, 7);
    model.move(PileType.CASCADE, 2, 3, PileType.CASCADE, 4);
    model.move(PileType.CASCADE, 1, 10, PileType.OPEN, 8);
    model.move(PileType.CASCADE, 1, 9, PileType.OPEN, 9);
    model.move(PileType.CASCADE, 1, 8, PileType.OPEN, 10);
    model.move(PileType.CASCADE, 1, 7, PileType.OPEN, 11);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 12);
    model.move(PileType.CASCADE, 1, 5, PileType.OPEN, 13);
    model.move(PileType.CASCADE, 1, 4, PileType.OPEN, 14);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 15);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 16);
    model.move(PileType.CASCADE, 4, 8, PileType.CASCADE, 1);
  }

  /**
   * Tests a multimove that is possible.
   */
  @Test
  public void multiMove2() {
    MultiFreecellModel model = new MultiFreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 5, 30, false);
    model.move(PileType.CASCADE, 0, 10, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 4, 9, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 2, 9, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 2, 8, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 2, 7, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 2, 6, PileType.OPEN, 5);
    model.move(PileType.CASCADE, 2, 5, PileType.OPEN, 6);
    model.move(PileType.CASCADE, 2, 4, PileType.OPEN, 7);
    model.move(PileType.CASCADE, 2, 3, PileType.CASCADE, 4);
    model.move(PileType.CASCADE, 1, 10, PileType.OPEN, 8);
    model.move(PileType.CASCADE, 1, 9, PileType.OPEN, 9);
    model.move(PileType.CASCADE, 1, 8, PileType.OPEN, 10);
    model.move(PileType.CASCADE, 1, 7, PileType.OPEN, 11);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 12);
    model.move(PileType.CASCADE, 1, 5, PileType.OPEN, 13);
    model.move(PileType.CASCADE, 1, 4, PileType.OPEN, 14);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 15);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 16);
    model.move(PileType.CASCADE, 4, 8, PileType.CASCADE, 1);
    assertEquals(true, model.getGameState().contains("C2: 2♦, 7♦, 6♣, 5♥"));
  }

  /**
   * Tests that a multimove when there is an invalid multimove (to Cascade).
   */
  @Test(expected = IllegalArgumentException.class)
  public void multiMove3() {
    MultiFreecellModel model = new MultiFreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 5, 17, false);
    model.move(PileType.CASCADE, 4, 8, PileType.CASCADE, 1);
  }

  /**
   * Tests that a multimove when there is an invalid multimove (to open pile).
   */
  @Test(expected = IllegalArgumentException.class)
  public void multiMove4() {
    MultiFreecellModel model = new MultiFreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 5, 17, false);
    model.move(PileType.CASCADE, 4, 8, PileType.OPEN, 1);
  }

  /**
   * Tests that a multimove when there is an invalid multimove (to foundation pile).
   */
  @Test(expected = IllegalArgumentException.class)
  public void multiMove5() {
    MultiFreecellModel model = new MultiFreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 5, 17, false);
    model.move(PileType.CASCADE, 4, 8, PileType.FOUNDATION, 1);
  }
}