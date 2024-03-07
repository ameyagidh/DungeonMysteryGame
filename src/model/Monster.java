package model;

/**
 * This interface represents a Monster.
 * A monster in the game has properties like the number of arrow hits taken and its dwelling location.
 * A monster can eat the player if the player enters its dwelling cave.
 */
public interface Monster {

  /**
   * Retrieves the dwelling location of the monster.
   *
   * @return the dwelling location
   */
  Location getDwellingLocation();

  /**
   * Retrieves the number of arrow hits taken by the monster.
   *
   * @return the number of arrow hits taken already
   */
  int getHitsTaken();

  /**
   * Checks if the monster is alive or dead.
   *
   * @return true if the monster is alive, else false
   */
  boolean isAlive();
}
