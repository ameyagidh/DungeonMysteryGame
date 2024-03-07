package model;

import java.util.Map;

/**
 * This interface represents the Player.
 * The player in the game has properties like its current location and collected treasure so far.
 * The player can move in the dungeon and pick up treasure if available.
 */
public interface IPlayer {
  /**
   * Retrieves the current location of the player on the game dungeon grid.
   *
   * @return the Location of the player
   */
  Location getCurrentLocation();

  /**
   * Retrieves the treasure collected by the player and its quantity at any point in the game.
   *
   * @return a key-value pair with the treasure as key and quantity as value
   */
  Map<Treasure, Integer> getTreasureCollected();

  /**
   * Retrieves the number of arrows left with the player.
   *
   * @return the number of arrows
   */
  int getArrowsLeft();

  /**
   * Checks if the player is alive.
   *
   * @return true if player is alive, else false
   */
  boolean isAlive();
}
