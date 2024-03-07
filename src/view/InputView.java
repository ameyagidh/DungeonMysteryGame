package view;

/**
 * The InputView interface represents the initial view inflated by the controller
 * to obtain model parameters from the user and choose between GUI and console-based game options.
 */
public interface InputView {

  /**
   * Retrieves the parameters for constructing the dungeon in the model.
   *
   * @return The user-given arguments as a string array.
   */
  String[] getDungeonParams();
}
