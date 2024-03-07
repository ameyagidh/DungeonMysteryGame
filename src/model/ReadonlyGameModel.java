package model;

/**
 * This interface represents a read-only model for the Dungeon Adventure Game.
 * Using this interface, all information about the game and its current state can be accessed.
 */
public interface ReadonlyGameModel {
  /**
   * Retrieves the player in the adventure game.
   *
   * @return the player in the adventure game
   */
  IPlayer getPlayer();

  /**
   * Retrieves the adventure game dungeon.
   *
   * @return the adventure game dungeon
   */
  IDungeon getDungeon();

  /**
   * Retrieves the Location of the game starting point.
   *
   * @return the start location of the dungeon
   */
  Location getGameStart();

  /**
   * Retrieves the co-ordinates of the game ending point.
   *
   * @return the end location of the dungeon
   */
  Location getGameEnd();

  /**
   * Checks whether the game is over. The game is over when either the player reaches the
   * end location alive or if a monster eats a player.
   *
   * @return true if the game is over, else false
   */
  boolean isGameOver();

  /**
   * Gets a string representation for a bird's eye view of the dungeon having
   * the player, caves, tunnels, start, and the end.
   * This representation is helpful to navigate the player through the dungeon.
   *
   * @return the current game state as a string
   */
  String getGameState();
}
