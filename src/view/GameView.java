package view;

import controller.GuiGameFeatures;

/**
 * The GameView interface represents the view for the dungeon adventure game
 * and lists the functions that a view can perform.
 */
public interface GameView {

  /**
   * Sets the features as listed in the controller so that the view
   * can send appropriate delete callbacks in case of certain listener events.
   *
   * @param f The controller providing the features.
   */
  void setFeatures(GuiGameFeatures f);

  /**
   * Resets the focus on the appropriate part of the view.
   */
  void resetFocus();

  /**
   * Destroys the view by setting it invisible and disposing it.
   */
  void delete();

  /**
   * Repaints the view and all the enclosed components.
   */
  void refresh();

  /**
   * Creates a message pop-up in the view for displaying errors and other relevant messages.
   *
   * @param err          The message to be displayed in the pop-up.
   * @param title        The title of the pop-up.
   * @param messageType  The type of pop-up depending on the context.
   */
  void showMessage(String err, String title, int messageType);
}
