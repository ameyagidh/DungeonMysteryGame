package utils;

/**
 * The Tuple class represents an immutable tuple data type, used to represent coordinates (x, y)
 * of different locations in a dungeon.
 *
 * @param <X> The type of the x-coordinate
 * @param <Y> The type of the y-coordinate
 */
public class Tuple<X, Y> {
  private final X x;
  private final Y y;

  /**
   * Constructs a Tuple object with the given x and y coordinates.
   *
   * @param x The x-coordinate
   * @param y The y-coordinate
   */
  public Tuple(X x, Y y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Retrieves the x-coordinate of the tuple.
   *
   * @return The x-coordinate
   */
  public X getX() {
    return this.x;
  }

  /**
   * Retrieves the y-coordinate of the tuple.
   *
   * @return The y-coordinate
   */
  public Y getY() {
    return this.y;
  }
}
