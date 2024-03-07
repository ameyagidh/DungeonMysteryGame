package model;

import java.util.Random;

/**
 * This class represents a type of monster known as Otyugh.
 * Otyughs are extremely smelly creatures that lead solitary lives in caves within our dungeon.
 */
public class Otyugh implements Monster {
  private final Location dwellLocation;
  private int hitsTaken;

  /**
   * Constructs an Otyugh at a given location.
   *
   * @param dwellLocation the location of the cave where the Otyugh dwells
   */
  public Otyugh(Location dwellLocation) {
    this.dwellLocation = dwellLocation;
    this.hitsTaken = 0;
  }

  /**
   * Increments the number of hits taken by the Otyugh.
   */
  void takeHit() {
    this.hitsTaken += 1;
  }

  /**
   * Determines if the Otyugh can eat the player.
   *
   * @return true if the Otyugh can eat the player, else false
   */
  boolean eatPlayer() {
    Random rand = new Random();

    if (this.hitsTaken == 0) {
      return true;
    } else if (this.hitsTaken == 1) {
      return rand.nextInt(2) == 0;
    } else {
      return false;
    }
  }

  @Override
  public Location getDwellingLocation() {
    return this.dwellLocation;
  }

  @Override
  public int getHitsTaken() {
    return this.hitsTaken;
  }

  @Override
  public boolean isAlive() {
    return this.hitsTaken < 2;
  }
}
