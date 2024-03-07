package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

import model.Location;
import model.ReadonlyGameModel;

/**
 * The GridPanel class represents a custom JPanel containing one grid location of the dungeon.
 */
class GridPanel extends JPanel {
  private final ReadonlyGameModel model;
  private final int row;
  private final int col;

  /**
   * Constructs a GridPanel object with the specified read-only model, row index, and column index.
   *
   * @param m The ReadonlyGameModel representing the read-only model.
   * @param r The row index for the grid panel.
   * @param c The column index for the grid panel.
   */
  public GridPanel(ReadonlyGameModel m, int r, int c) {
    this.row = r;
    this.col = c;
    this.model = m;
  }

  /**
   * Retrieves the row index of the grid panel.
   *
   * @return The row index of the grid panel.
   */
  int getRow() {
    return row;
  }

  /**
   * Retrieves the column index of the grid panel.
   *
   * @return The column index of the grid panel.
   */
  int getCol() {
    return col;
  }

  /**
   * Overrides the paintComponent method to paint the grid panel.
   *
   * @param g The Graphics object used for painting.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Retrieve the location from the model
    Location loc = this.model.getDungeon().getLocation(this.row, this.col);

    // Check if the player is at the current location
    boolean player = this.model.getPlayer().getCurrentLocation().equals(loc)
            && this.model.getPlayer().isAlive();

    Graphics2D g2d = (Graphics2D) g;
    setPreferredSize(new Dimension(100, 100)); // Set preferred size for the panel

    // Get the image for the panel
    Image image = new PanelImage(this, loc, player).getImage();

    // Draw the image on the panel
    g2d.drawImage(image, 0, 0, null);
  }
}
