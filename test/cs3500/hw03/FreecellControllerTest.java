package cs3500.hw03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.hw02.Card;
import cs3500.hw02.PileType;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.FreecellModel;

public class FreecellControllerTest {
  FreecellModel model1;
  FreecellController controller1;

  /**
   * Initializes the fields for testing.
   *
   * @param input StringReader for Readable object
   */
  public void init(StringReader input) {
    model1 = new FreecellModel();
    controller1 = new FreecellController(input, new StringBuilder());
  }

  /**
   * Tests a controller with a null appendable object cannot be created.
   */
  @Test(expected = IllegalStateException.class)
  public void nullAppendable() {
    FreecellController controllerA = new FreecellController(new StringReader(""), null);
  }

  /**
   * Tests a controller with a null readable object cannot be created.
   */
  @Test(expected = IllegalStateException.class)
  public void nullReadable() {
    FreecellController controllerR = new FreecellController(null, new StringBuilder());
  }

  /**
   * Tests that a null deck cannot be used in the controller.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullDeck() {
    StringReader input = new StringReader("blah");
    this.init(input);
    controller1.playGame(null, model1, 5, 5, false);
  }

  /**
   * Tests that a null model cannot be used in the controller.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    StringReader input = new StringReader("blah");
    this.init(input);
    ArrayList<Card> deck = new ArrayList<>();
    controller1.playGame(deck, null, 5, 5, false);
  }

  /**
   * Tests that the game doesn't start with invalid deck.
   */
  @Test
  public void playGame1() {
    StringReader input = new StringReader("blah");
    this.init(input);
    ArrayList<Card> deck = new ArrayList<>();
    StringBuilder expected = new StringBuilder("Could not start game.");
    controller1.playGame(deck, model1, 5, 5, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests that the game doesn't start with invalid number of piles.
   */
  @Test
  public void playGame2() {
    StringReader input = new StringReader("blah");
    this.init(input);
    ArrayList<Card> deck = new ArrayList<>();
    StringBuilder expected = new StringBuilder("Could not start game.");
    controller1.playGame(deck, model1, 5, 0, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests that the game doesn't start with invalid number of piles.
   */
  @Test
  public void playGame3() {
    StringReader input = new StringReader("blah");
    this.init(input);
    ArrayList<Card> deck = new ArrayList<>();
    StringBuilder expected = new StringBuilder("Could not start game.");
    controller1.playGame(deck, model1, 2, 5, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests for case that a move that may be valid input but isn't allowed by the game rules.
   */
  @Test
  public void playGame4() {
    StringReader input = new StringReader("C10 8 O3");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 5, 5, false);
    assertEquals(true, controller1.app.toString().contains("Invalid move. Try again."));
  }

  /**
   * Tests for case that game is finished and ends.
   */
  @Test
  public void playGame5() {
    String moves = "";
    for (int i = 0; i < 52; i++) {
      moves += (" C" + (i + 1) + " 1 " + "F" + ((i / 13) + 1));
    }
    StringReader input = new StringReader(moves);
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 52, 5, false);
    assertEquals(true, controller1.app.toString().contains("\nGame over."));
  }

  /**
   * Tests the takeInput method when given a premature quit input.
   */
  @Test
  public void takeInput1() {
    StringReader input = new StringReader("q");
    this.init(input);
    List<Card> deck = model1.getDeck();
    StringBuilder expected = new StringBuilder("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "C1: A♦, 6♦, J♦, 3♥, 8♥, K♥, 5♠, 10♠, 2♣, 7♣, Q♣\n" +
            "C2: 2♦, 7♦, Q♦, 4♥, 9♥, A♠, 6♠, J♠, 3♣, 8♣, K♣\n" +
            "C3: 3♦, 8♦, K♦, 5♥, 10♥, 2♠, 7♠, Q♠, 4♣, 9♣\n" +
            "C4: 4♦, 9♦, A♥, 6♥, J♥, 3♠, 8♠, K♠, 5♣, 10♣\n" +
            "C5: 5♦, 10♦, 2♥, 7♥, Q♥, 4♠, 9♠, A♣, 6♣, J♣\n" +
            "Game quit prematurely.");
    controller1.playGame(deck, model1, 5, 5, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests the takeInput method when given a premature quit input.
   */
  @Test
  public void takeInput2() {
    StringReader input = new StringReader("C1 7 O2 Q");
    this.init(input);
    List<Card> deck = model1.getDeck();
    StringBuilder expected = new StringBuilder("F1:\n" +
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
            "C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♣\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♦, 9♦, 4♥, Q♥, 7♠, 2♣\n" +
            "C2: 2♦, 10♦, 5♥, K♥, 8♠, 3♣, J♣\n" +
            "C3: 3♦, J♦, 6♥, A♠, 9♠, 4♣, Q♣\n" +
            "C4: 4♦, Q♦, 7♥, 2♠, 10♠, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♥, 3♠, J♠, 6♣\n" +
            "C6: 6♦, A♥, 9♥, 4♠, Q♠, 7♣\n" +
            "C7: 7♦, 2♥, 10♥, 5♠, K♠, 8♣\n" +
            "C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣\n" +
            "Game quit prematurely.");
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests the sourceInput method when given correct input.
   */
  @Test
  public void sourceInput1() {
    StringReader input = new StringReader("C1");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(PileType.CASCADE, controller1.source);
    assertEquals(controller1.pileNumber, 0);
  }

  /**
   * Tests the sourceInput method when given incorrect input.
   */
  @Test
  public void sourceInput2() {
    StringReader input = new StringReader("bad 23552 wrong");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.source, null);
    assertEquals(controller1.pileNumber, -999);
  }

  /**
   * Tests the sourceInput method when given a sequence of correct and incorrect input.
   */
  @Test
  public void sourceInput3() {
    StringReader input = new StringReader("bad iqwfew eyyee C1");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(PileType.CASCADE, controller1.source);
    assertEquals(controller1.pileNumber, 0);
  }

  /**
   * Tests the indexInput method when given correct input.
   */
  @Test
  public void indexInput1() {
    StringReader input = new StringReader("C1 7");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.cardIndex, 6);
  }

  /**
   * Tests the indexInput method when given incorrect input.
   */
  @Test
  public void indexInput2() {
    StringReader input = new StringReader("C1 a");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.cardIndex, -999);
  }

  /**
   * Tests the indexInput method when given a sequence of correct and incorrect input.
   */
  @Test
  public void indexInput3() {
    StringReader input = new StringReader("C1 a b c 7");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.cardIndex, 6);
  }

  /**
   * Tests the destInput method when given correct input.
   */
  @Test
  public void destInput1() {
    StringReader input = new StringReader("C1 7 O4");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(null, controller1.destination);
    assertEquals(controller1.destPileNumber, -999);
  }

  /**
   * Tests the destInput method when given incorrect input.
   */
  @Test
  public void destInput2() {
    StringReader input = new StringReader("C1 7 bad");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.destination, null);
    assertEquals(controller1.destPileNumber, -999);
  }

  /**
   * Tests the destInput method when given a sequence of correct and incorrect input.
   */
  @Test
  public void destInput3() {
    StringReader input = new StringReader("C1 7 bad blah O4");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.destination, null);
    assertEquals(controller1.destPileNumber, -999);
  }

  /**
   * Tests the resetFields method.
   */
  @Test
  public void resetFields() {
    StringReader input = new StringReader("C1 7");
    this.init(input);
    List<Card> deck = model1.getDeck();
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(controller1.source, PileType.CASCADE);
    assertEquals(controller1.pileNumber, 0);
    assertEquals(controller1.cardIndex, 6);
    assertEquals(controller1.destination, null);
    assertEquals(controller1.destPileNumber, -999);
    controller1.resetFields();
    assertEquals(controller1.source, null);
    assertEquals(controller1.pileNumber, -999);
    assertEquals(controller1.cardIndex, -999);
    assertEquals(controller1.destination, null);
    assertEquals(controller1.destPileNumber, -999);
  }

  /**
   * Tests the output method when no outputs besides the start of the game are given.
   */
  @Test
  public void output1() {
    StringReader input = new StringReader("C1 7");
    this.init(input);
    List<Card> deck = model1.getDeck();
    StringBuilder expected = new StringBuilder("F1:\n" +
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
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }

  /**
   * Tests the output method when something has been sent.
   */
  @Test
  public void output2() {
    StringReader input = new StringReader("C1 7 O2");
    this.init(input);
    List<Card> deck = model1.getDeck();
    StringBuilder expected = new StringBuilder("F1:\n" +
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
            "C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♣\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♦, 9♦, 4♥, Q♥, 7♠, 2♣\n" +
            "C2: 2♦, 10♦, 5♥, K♥, 8♠, 3♣, J♣\n" +
            "C3: 3♦, J♦, 6♥, A♠, 9♠, 4♣, Q♣\n" +
            "C4: 4♦, Q♦, 7♥, 2♠, 10♠, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♥, 3♠, J♠, 6♣\n" +
            "C6: 6♦, A♥, 9♥, 4♠, Q♠, 7♣\n" +
            "C7: 7♦, 2♥, 10♥, 5♠, K♠, 8♣\n" +
            "C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣");
    controller1.playGame(deck, model1, 8, 4, false);
    assertEquals(expected.toString(), controller1.app.toString());
  }
}