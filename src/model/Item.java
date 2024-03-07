package model;

/**
 * This interface represents an item that can be found inside the dungeon.
 */
public interface Item {

  /**
   * Retrieves the name of the item.
   *
   * @return the name of the item as a string
   */
  String getName();

  /**
   * Allows a player to pick up the item.
   *
   * @param player the player in the game that is picking up the item
   */
  void pick(Player player);
}
