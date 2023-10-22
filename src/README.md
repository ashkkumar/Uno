# Uno Game README

This README provides an overview of the Uno game, its components, and how to play it.

## Introduction
Uno is a popular card game known for its fun and fast-paced gameplay. The goal of the game is to be the first to play all of your cards or accumulate the lowest score when the game ends.

## Game Components
The Uno game is implemented in Java and consists of several key components:

1. `Uno` class: This is the main class that manages the game. It includes methods to handle player turns, card actions, and determine the winner.

2. `Player` class: Represents a player in the game. Each player has a name, a list of Uno cards, and a score.

3. `Deck` class: Represents the deck of Uno cards. It includes methods for drawing cards, checking if the deck is empty, and creating a shuffled deck.

4. `Card` class: Defines Uno cards with a card type (e.g., number, skip, reverse) and a color (e.g., red, blue, green, yellow, or wild).

5. `Main` class: The entry point of the Uno game, where an instance of the `Uno` class is created and the game is played.

## How to Play
1. Run the game by executing the `Main` class. It will create an instance of the `Uno` class and start the game.

2. The game will prompt you to enter the number of players (2 to 4 players are allowed). Then, you'll be prompted to enter the names of the players.

3. The game will start with a randomly chosen starting card from the deck.

4. Players take turns, and on each turn, they can play a card that matches either the color or the number of the card on top of the discard pile. If they can't play a valid card, they must draw a card.

5. Special action cards like "Reverse," "Skip," "Wild," "Draw Two," and "Wild Draw Two" have unique effects in the game.

6. The game continues until one player has no cards left, and they are declared the winner. The game also takes into account scores based on the cards left in other players' hands.

7. The winner is the player with the lowest score (or no cards left) when the game ends.

## Special Actions
- `Reverse`: Reverses the order of play.
- `Skip`: Skips the next player's turn.
- `Wild`: Allows the current player to choose a new color for the discard pile.
- `Draw Two`: Forces the next player to draw two cards and skip their turn.
- `Wild Draw Two`: Allows the current player to choose a new color for the discard pile and forces the next player to draw two cards and skip their turn.

## Card Types and Scoring
Uno cards come in various types and colors, and they have different scoring values. The goal is to minimize your score.

- Number cards (1-9): Score equal to the face value of the card.
- Reverse, Skip, and Draw Two: Score 20 points.
- Wild: Score 40 points.
- Wild Draw Two: Score 50 points.

## Important Notes
- The game includes input validation to ensure that players make valid choices.
- Players can view their cards and select cards to play using index numbers.

## Enjoy the Game!
Uno is a fun and interactive card game that can be enjoyed with friends and family. Have a great time playing and may the best player win!