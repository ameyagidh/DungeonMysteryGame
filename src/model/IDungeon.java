package model;

/**
 * This interface represents a dungeon in the adventure game.
 * A game dungeon has features like caves and tunnels, start and end
 * and is represented as a 2D grid.
 */
public interface IDungeon {

  /**
   * Retrieves the location of the start of the game dungeon.
   *
   * @return the location of the start cave
   */
  Location getStart();

  /**
   * Retrieves the location of the end of the game dungeon.
   *
   * @return the location of the end cave
   */
  Location getEnd();

  /**
   * Retrieves the location of any general position in the game dungeon grid.
   *
   * @param x the x-coordinate of the queried location
   * @param y the y-coordinate of the queried location
   * @return the Location at (x, y) coordinate of the game dungeon
   * @throws IllegalArgumentException if x or y are out of bounds
   */
  Location getLocation(int x, int y);

  /**
   * Retrieves the total number of caves in the game dungeon.
   *
   * @return the number of caves
   */
  int getNoOfCaves();

  /**
   * Retrieves the total number of tunnels in the game dungeon.
   *
   * @return the number of tunnels
   */
  int getNoOfTunnels();

  /**
   * Retrieves the 2D grid representation of the dungeon.
   *
   * @return the 2D array representing the dungeon grid
   */
  Location[][] getDungeonGrid();
}
