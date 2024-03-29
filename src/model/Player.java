package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the Player in the dungeon adventure game.
 */
public class Player implements IPlayer {
  private Location currLocation;
  private final Map<Treasure, Integer> treasureCollected;
  private final List<Weapon> arrows;
  private boolean alive;

  /**
   * Constructs a player and assigns it a given start location.
   *
   * @param startLocation the Location of the start cave in the game dungeon
   * @throws IllegalArgumentException if the starting location is null
   */
  public Player(Location startLocation) throws IllegalArgumentException {
    if (startLocation == null) {
      throw new IllegalArgumentException("Starting location of the player cannot be null!");
    }

    this.currLocation = startLocation;
    ((Cell) this.currLocation).markVisited();
    this.treasureCollected = new HashMap<>();
    this.arrows = new ArrayList<>(List.of(Weapon.ARROW, Weapon.ARROW, Weapon.ARROW));
    this.alive = true;
  }

  /**
   * Updates the player's location to a new location.
   *
   * @param newLocation the new Location of the player
   */
  void updateLocation(Location newLocation) {
    this.currLocation = newLocation;
    ((Cell) this.currLocation).markVisited();
  }

  /**
   * Adds the collected treasure to the player's inventory.
   *
   * @param t the Treasure to be added
   */
  void pickTreasure(Treasure t) {
    this.treasureCollected.put(t, treasureCollected.getOrDefault(t, 0) + 1);
    ((Cell) this.currLocation).pop(t);
  }

  /**
   * Adds an arrow to the player's inventory.
   *
   * @param w the Weapon to be added
   */
  void pickArrow(Weapon w) {
    this.arrows.add(w);
    ((Cell) this.currLocation).pop(w);
  }

  /**
   * Removes an arrow from the player's inventory when shot.
   */
  void shootArrow() {
    this.arrows.remove(Weapon.ARROW);
  }

  /**
   * Sets the player's state to dead.
   */
  void die() {
    this.alive = false;
  }

  @Override
  public Location getCurrentLocation() {
    return this.currLocation;
  }

  @Override
  public Map<Treasure, Integer> getTreasureCollected() {
    Map<Treasure, Integer> treasureCollectedCopy = this.treasureCollected.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

    return treasureCollectedCopy;
  }

  @Override
  public int getArrowsLeft() {
    return this.arrows.size();
  }

  @Override
  public boolean isAlive() {
    return this.alive;
  }
}
