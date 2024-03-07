package controller;

/**
 * This interface represents a controller for the Dungeon Adventure Game.
 * It handles user moves by executing them using the model and conveys
 * move outcomes to the user in some form.
 */
public interface GameController {

  /**
   * Method to start the game using the game controller.
   * In case of a text-based game, this initiates one round of the dungeon game.
   * In case of a GUI-based game, this initiates the game view and makes it visible to the user.
   */
  void playGame();
}
