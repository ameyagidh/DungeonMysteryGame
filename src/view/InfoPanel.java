package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import model.ReadonlyGameModel;

/**
 * The InfoPanel class represents a custom JPanel for displaying game information in
 * the bottom panel of the game GUI.
 */
class InfoPanel extends JPanel {

  /**
   * Constructs an InfoPanel object to display game information.
   *
   * @param rom The read-only model representing the game model.
   */
  public InfoPanel(ReadonlyGameModel rom) {
    // Set layout for the panel
    this.setLayout(new BorderLayout());

    // Add PlayerInfoPanel to the left side of the panel
    this.add(new PlayerInfoPanel(rom), BorderLayout.WEST);

    // Add LocationInfoPanel to the right side of the panel
    this.add(new LocationInfoPanel(rom), BorderLayout.EAST);
  }
}
