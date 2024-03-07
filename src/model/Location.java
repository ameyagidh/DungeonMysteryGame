package model;

import java.util.List;
import java.util.Set;

/**
 * This interface represents a Location in the dungeon.
 * Each location has its own properties like its coordinates on the 2D grid, its neighbors,
 * and if it contains any items or houses any monsters.
 */
public interface Location {

  /**
   * Retrieves the (x, y) coordinates of the location on the 2D grid.
   *
   * @return the x and the y coordinates as a 2-length integer array.
   */
  int[] getCoordinates();

  /**
   * Retrieves the possible directions the player can move from the current location.
   *
   * @return the set of possible directions
   */
  Set<Direction> getPossibleDirections();

  /**
   * Retrieves the neighbor of a location in the given direction.
   *
   * @param d the given direction to move a player
   * @return the coordinates of the neighbor
   * @throws IllegalArgumentException if the direction is null
   */
  int[] getNeighbour(Direction d);

  /**
   * Retrieves the treasure contents of the location as a list.
   *
   * @return the list of treasures available at the current location
   */
  List<Item> getContent();

  /**
   * Checks if the given location houses a monster.
   *
   * @return true if the location houses a monster, else false
   */
  boolean hasMonster();

  /**
   * Retrieves the Monster object if present at a current location.
   * The method 'hasMonster()' should be checked before calling this method.
   *
   * @return the monster
   * @throws IllegalStateException if there is no monster present at the given location
   */
  Monster getMonster();

  /**
   * Checks the smell detected, if any, at the location.
   *
   * @return the intensity of smell detected
   */
  Smell getSmell();

  /**
   * Checks if a given location is a cave.
   *
   * @return true if the location is a cave, false if it's a tunnel
   */
  boolean isCave();

  /**
   * Checks if a location has been visited by the player.
   *
   * @return true if the location has been visited at least once, else false
   */
  boolean isVisited();
}
