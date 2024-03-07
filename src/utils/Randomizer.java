package utils;

import java.util.Random;

/**
 * The Randomizer class represents an object that helps in generating random values,
 * particularly useful for dungeon generation.
 */
public class Randomizer {
  private final Random rand;

  /**
   * Constructs a Randomizer object with a truly random seed.
   */
  public Randomizer() {
    this.rand = new Random();
  }

  /**
   * Constructs a Randomizer object with a deterministic seed value.
   *
   * @param seedValue The seed value for the Random object as an integer
   */
  public Randomizer(int seedValue) {
    this.rand = new Random();
    this.rand.setSeed(seedValue);
  }

  /**
   * Retrieves the initialized Random object.
   *
   * @return The Random object
   */
  public Random getRandom() {
    return this.rand;
  }
}
