This project was completed my Fall 2017 semester in Object-Oriented Design in IntelliJ IDEA. The program is based on a model (mapping the objects) and controller (manipulating and interacting this data) design.

How to play the game:
- The game will display a textual representation of the game in the console after each move
- Input into the console the move you want to make by writing the current pile of the card (the name is the same as how it is displayed), its index (from left to right and it must be the card at the end of the pile unless you have cards in order at the end of the pile), and the destination pile
- For example: C8 6 O3 moves the outermost card in the 8th cascade pile to the 3rd open pile
- If a move is invalid you will be alerted and prompted to input again the entire move, or the missing part of the input if part is correct

How to run the game in IntelliJ IDEA:
- Download the code as a zip file
- Create a new project from existing sources and choose this zip file as the source
- 

 As provided by our course website, the description of the rules of freecell this game follows is:

"Freecell is a Solitaire-type game, which uses the regular deck of 52 suit-value cards. There are four suits: clubs (♣), diamonds (♦), hearts (♥), and spades (♠). Hearts and diamonds are colored red; clubs and spades are colored black. There are thirteen values: ace (written A), two through ten (written 2 through 10), jack (J), queen (Q) and king (K). In Freecell, aces are considered “low”, or less than a two. (In other games, they’re considered “high”, or greater than a king.)

The game play is divided among three types of card piles. First, there are four foundation piles, one for each suit (shown in the top right in figure above), and indexed 1 through 4. These four piles are initially empty, and the goal of Freecell is to collect all cards of all suits in their respective foundation piles. A card can be added to a foundation pile if and only if its suit matches that of the pile, and its value is one more than that of the card currently on top of the pile (i.e. the next card in foundation pile 2 in the figure above can only be 3♣). If a foundation pile is currently empty, any ace can be added to it: there is no required ordering of suits in the foundation piles.

The second type of pile is the cascade pile (the eight piles in the bottom of figure above), also indexed starting from 1. Usually a game of Freecell has eight cascade piles, but our game will allow as few as four. Because the initial deal of the game is shuffled (see below), a cascade pile may initially contain cards in any order. However, a card from some pile can be moved to the end of a cascade pile if and only if its color is different from that of the currently last card, and its value is exactly one less than that of the currently last card (e.g. in the figure above, the next card in cascade pile 1 can be 4♦ or 4♥ while the next card in cascade pile 3 can be 10♠ or 10♣). This sequence of decreasing-value, alternating-color cards is called a build. (Different variants of Freecell, or other solitaire games, differ primarily in what builds are permitted.)

Finally, the third type of pile is the open pile (top left in figure above). Usually a game of Freecell has four open piles, but our game will allow as few as one. An open pile may contain at most one card. An open pile is usually used as a temporary buffer during the game to hold cards.

Play starts by dealing the full deck of 52 cards among the cascade piles, in a round-robin fashion (e.g. in a game with eight cascade piles, pile 1 will have card indices 0, 8, 16,..., pile 2 will have card indices 1, 9, 17, ... and so on (assuming card indices in the deck begin at 0). The player then moves cards between piles, obeying the rules above. The objective of the game is to collect all cards in the four foundation piles, at which stage the game is over."
