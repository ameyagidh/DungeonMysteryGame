package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

import controller.GuiGameFeatures;
import model.Location;
import model.ReadonlyGameModel;

/**
 * The DungeonPanel class represents a customized JPanel containing the map of the dungeon adventure game.
 * It utilizes a grid layout where each grid location is represented by another custom JPanel
 * representing a specific dungeon location. This class also adds a mouse listener to all grid locations.
 */
class DungeonPanel extends JPanel {
  private final ReadonlyGameModel model;

  /**
   * Constructs a DungeonPanel object with the specified game model.
   *
   * @param m The ReadonlyGameModel representing the game model.
   */
  public DungeonPanel(ReadonlyGameModel m) {
    this.model = m;

    Location[][] dungeon = this.model.getDungeon().getDungeonGrid();

    // Set layout as grid layout matching dungeon dimensions
    this.setLayout(new GridLayout(dungeon.length, dungeon[0].length, 0, 0));

    // Create panels for each grid location in the dungeon
    for (int row = 0; row < dungeon.length; row++) {
      for (int col = 0; col < dungeon[0].length; col++) {
        JPanel panel = new GridPanel(this.model, row, col);
        this.add(panel);
      }
    }
  }

  /**
   * Adds a mouse listener to all grid locations in the dungeon panel.
   *
   * @param f The GuiGameFeatures object representing GUI game features.
   */
  void addMouseListener(GuiGameFeatures f) {
    // Create mouse adapter
    MouseAdapter clickAdapter = new GameMouseAdapter(f, this.model);

    // Add mouse listener to all grid panels
    for (Component c : this.getComponents()) {
      if (c instanceof GridPanel) {
        GridPanel p = (GridPanel) c;
        p.addMouseListener(clickAdapter);
      }
    }
  }
}
