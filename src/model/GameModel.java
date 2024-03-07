package model;

/**
 * This interface represents the complete Dungeon Adventure Game model and its functionalities.
 * It provides methods to play the game interactively.
 */
public interface GameModel extends ReadonlyGameModel {
  /**
   * Moves the player in a given direction.
   *
   * @param d the direction to move the player
   * @throws IllegalArgumentException if the player cannot move in the given direction
   */
  void movePlayer(Direction d);

  /**
   * Picks up the given item from the current location of the player, if available.
   *
   * @param i the item to be picked up from the current location
   * @throws IllegalArgumentException if the item is not present at the current location
   */
  void pickItem(Item i);

  /**
   * Shoots an arrow in a given direction and distance from the current location of the player.
   *
   * @param d        the direction to shoot the arrow in
   * @param distance the number of caves the arrow should travel
   * @return true if the arrow hits a monster, else false
   * @throws IllegalArgumentException if the direction or distance is not valid
   * @throws IllegalStateException    if the player has already exhausted all his arrows
   */
  boolean shootArrow(Direction d, int distance);
}
