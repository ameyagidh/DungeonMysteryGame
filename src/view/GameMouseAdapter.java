package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.GuiGameController;
import controller.GuiGameFeatures;
import model.ReadonlyGameModel;

/**
 * The GameMouseAdapter class represents a mouse adapter that captures a click on the dungeon game grid
 * and passes it to the controller for handling move features.
 */
class GameMouseAdapter extends MouseAdapter {
  private final ReadonlyGameModel model;
  private final GuiGameController controller;

  /**
   * Constructs a GameMouseAdapter with the specified controller and model.
   *
   * @param f The GuiGameFeatures representing the controller.
   * @param m The ReadonlyGameModel representing the model.
   */
  public GameMouseAdapter(GuiGameFeatures f, ReadonlyGameModel m) {
    this.model = m;
    this.controller = (GuiGameController) f;
  }

  /**
   * Handles the mouse click event.
   *
   * @param e The MouseEvent representing the mouse click event.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);

    // Get the clicked grid panel and its coordinates
    GridPanel gridPanel = (GridPanel) e.getComponent();
    int clickedX = gridPanel.getRow();
    int clickedY = gridPanel.getCol();

    // Get the player's current coordinates
    int[] playerCoordinates = this.model.getPlayer().getCurrentLocation().getCoordinates();
    int playerX = playerCoordinates[0];
    int playerY = playerCoordinates[1];

    // If the game is not over, handle the cell click
    if (!this.model.isGameOver()) {
      this.controller.handleCellClick(clickedX, clickedY, playerX, playerY);
    }
  }
}
