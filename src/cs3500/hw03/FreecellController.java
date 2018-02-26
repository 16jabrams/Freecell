package cs3500.hw03;

import java.util.List;
import java.io.IOException;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.Card;
import cs3500.hw02.PileType;

/**
 * This class represents the controller for a freecell game.
 */
public class FreecellController implements IFreecellController<Card> {
  protected final Readable read;
  protected final Appendable app;
  protected boolean quit;
  protected PileType source;
  protected PileType destination;
  protected int cardIndex;
  protected int destPileNumber;
  protected int pileNumber;

  /**
   * Constructor for an instance of the Freecell controller.
   *
   * @param read the reader for the reader's input
   * @param app  the output to update the user
   */
  public FreecellController(Readable read, Appendable app) {
    if (read == null || app == null) {
      throw new IllegalStateException();
    }
    this.read = read;
    this.app = app;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model,
                       int numCascades, int numOpens, boolean shuffle) {
    if (model == null || deck == null) {
      throw new IllegalArgumentException("The model/ deck cannot be null.");
    }
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.output("Could not start game.");
      return;
    }
    this.output(model.getGameState());

    resetFields();
    this.quit = false;
    Scanner scan = new Scanner(this.read);
    System.out.println("Please provide a valid input.");
    while (!model.isGameOver() && scan.hasNext() && !this.quit) {
      takeInput(scan.next());
      if (this.source != null && this.cardIndex != -999 && this.destination != null) {
        try {
          model.move(source, pileNumber, cardIndex, destination, destPileNumber);
          this.output("\n" + model.getGameState());
          resetFields();
        } catch (IllegalArgumentException e) {
          this.output("\nInvalid move. Try again." + e);
          resetFields();
        }
      }
      if (model.isGameOver()) {
        this.output(model.getGameState() + "\nGame over.");
      }
    }
  }

  /**
   * Processes the input from the user and decides which type of input it is.
   *
   * @param input String of the current input from the user
   */
  public void takeInput(String input) {
    if (input.equals("q") || input.equals("Q")) {
      this.quit = true;
      this.output("\nGame quit prematurely.");
      return;
    } else if (this.source == null && input.length() > 1) {
      sourceInput(input);
    } else if (this.source != null && this.cardIndex == -999) {
      indexInput(input);
    } else if (this.source != null && this.cardIndex != -999 && this.destination == null
            && input.length() > 1) {
      destInput(input);
    } else {
      System.out.println("Invalid input");
    }
  }

  /**
   * Processes the source input.
   *
   * @param input String of the current input from the user
   */
  public void sourceInput(String input) {
    char pile = input.charAt(0);
    int pileNum;
    try {
      pileNum = Integer.parseInt(input.substring(1)) - 1;
    } catch (NumberFormatException e) {
      System.out.println("Invalid source pile number, please input a valid source.");
      return;
    }
    switch (pile) {
      case ('C'):
        this.source = PileType.CASCADE;
        this.pileNumber = pileNum;
        break;
      case ('O'):
        this.source = PileType.OPEN;
        this.pileNumber = pileNum;
        break;
      case ('F'):
        this.source = PileType.FOUNDATION;
        this.pileNumber = pileNum;
        break;
      default:
        System.out.println("Invalid source pile, please input a valid source.");
        break;
    }
  }

  /**
   * Processes the index input.
   *
   * @param input String of the current input from the user
   */
  public void indexInput(String input) {
    int index;
    try {
      index = Integer.parseInt(input) - 1;
    } catch (NumberFormatException e) {
      System.out.println("Invalid card index, please input valid card index.");
      return;
    }
    this.cardIndex = index;
  }

  /**
   * Processes the destination input.
   *
   * @param input String of the current input from the user
   */
  public void destInput(String input) {
    char pile = input.charAt(0);
    int pileNum;
    try {
      pileNum = Integer.parseInt(input.substring(1)) - 1;
    } catch (NumberFormatException e) {
      System.out.println("Invalid destination pile number, please input a valid destination.");
      return;
    }
    switch (pile) {
      case ('C'):
        this.destination = PileType.CASCADE;
        this.destPileNumber = pileNum;
        break;
      case ('O'):
        this.destination = PileType.OPEN;
        this.destPileNumber = pileNum;
        break;
      case ('F'):
        this.destination = PileType.FOUNDATION;
        this.destPileNumber = pileNum;
        break;
      default:
        System.out.println("Invalid destination pile, please input a valid destination.");
        break;
    }
  }

  /**
   * Resets all of the fields.
   */
  public void resetFields() {
    this.source = null;
    this.destination = null;
    this.cardIndex = -999;
    this.destPileNumber = -999;
    this.pileNumber = -999;
  }

  /**
   * Sends output to the appendable object.
   *
   * @param str the string being appended
   */
  public void output(String str) {
    try {
      this.app.append(str);
    } catch (IOException e) {
      // catches exception within method
    }
  }
}
