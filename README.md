### Uno Game README

This README provides an overview of the Uno game, its components, and
how to play it.

## Introduction 
Uno is a popular card game known for its fun and
fast-paced gameplay. The goal of the game is to be the first to play all
of your cards or accumulate the lowest score when the game ends.

## Game Components 
The Uno game has been revamped using the
Model-View-Controller (MVC) design pattern in Java, and Uno Flip is integrated. Milestone 4 has now added 
redo/undo functionality, replay functionality, and save/load. Here are the key
components:

1. `UnoGameModel` class: This serves as the model for the Uno game,
managing the game state, players, deck, and card actions. It also
includes methods to check for a winner, handle turns, and more.

2. `UnoGameView` class: This class represents the view of the Uno
game and provides the graphical user interface. It includes buttons for
player actions, displays the current state of the game, and handles user
interactions.

3. `UnoGameController` class: Acting as the controller, this class
receives user inputs from the view and updates the model accordingly. It
also communicates with the view to reflect changes in the game state.

4. `GameSaver` and `GameData` classes: These classes facilitate the methods related to the
serialization and deserialization of the uno game. They also contain methods related to the 
functionality of the undo/redo buttons.

5. `Player`, `Deck`, and `Card` classes: These classes remain
integral to the Uno game, defining players, the deck of Uno cards, and
the properties of each card, respectively.

6. `Main` class: The entry point of the Uno game, where an instance
of the `UnoGameView` class is created to start the graphical
interface.

## How to Play 
1. Run the game by executing the `Main` class. This
initializes the MVC components and starts the Uno game.

2. The game will prompt you to enter the number of human players from a drop-down menu (1 to 4 players are allowed).

3. The game will prompt you to enter the number of AI players from a drop-down menu. (0-4 are allowed). If one human player was selected, at least one AI player must be selected to play.

4. The graphical user interface provided by `UnoGameView` will guide
you through the game. Follow on-screen instructions to play your turns,
draw cards, and strategically use special action cards.

5. When a FLIP card is played, the deck is flipped over and everyone must play off the Dark side of the cards, until someone else plays another FLIP card, and the deck is flipped back over to the Light side.

6. The game continues until one player has no cards left, and they are
declared the winner. The game also considers scores based on the cards
left in other players' hands. Points are scored based on which side (light or dark) the game ended on.

7. The winner is the player with the lowest score (or no cards left)
when the game ends. 

## Special Actions Special action cards have unique effects in the game: 

- Reverse: Reverses the order of play. 
- Skip: Skips the next player\'s turn. 
- Wild: Allows the current player to choose a new colour for the discard pile. - \*\*Draw One\*\*: Forces
the next player to draw one card and skip their turn. If this card is
the starting card, it will prompt the first player to choose a colour,
and will set the current card to this colour for the next player to
play. 
- Wild Draw Two: Allows the current player to choose a new
colour for the discard pile and forces the next player to draw two cards
and skip their turn.
- Flip: The cards in the Discard Pile, Draw Pile, and everyone's hands, are flipped from the current (Dark or Light) side to the opposite. The game continues based on which side it is flipped on. This card may be played on a matching colour or another Flip card.
- Draw Five: The next player must draw five cards and miss their turn. This card is played on Dark Side.
- Skip Everyone: All players are "skipped" and the play goes back to whoever played the Skip Everyone card. This card is played on Dark Side.