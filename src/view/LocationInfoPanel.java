package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Item;
import model.ReadonlyGameModel;
import model.Treasure;
import model.Weapon;

/**
 * The LocationInfoPanel class represents a custom JPanel that displays information
 * about the player's current location, such as the treasure and number of arrows available.
 */
class LocationInfoPanel extends JPanel {
  private final ReadonlyGameModel model;
  private final JLabel diamondLabel;
  private final JLabel rubyLabel;
  private final JLabel sapphireLabel;
  private final JLabel arrowLabel;

  /**
   * Constructs a LocationInfoPanel object with the specified read-only model.
   *
   * @param model The read-only model representing the game model.
   */
  public LocationInfoPanel(ReadonlyGameModel model) {
    this.model = model;
    List<Item> content = this.model.getPlayer().getCurrentLocation().getContent();

    this.setLayout(new FlowLayout());

    JLabel locationLabel = new JLabel(" Location:");
    this.add(locationLabel);

    diamondLabel = createInfoJLabel(ImageCategory.DIAMOND, Collections.frequency(content, Treasure.DIAMOND));
    this.add(diamondLabel);
    rubyLabel = createInfoJLabel(ImageCategory.RUBY, Collections.frequency(content, Treasure.RUBY));
    this.add(rubyLabel);
    sapphireLabel = createInfoJLabel(ImageCategory.SAPPHIRE, Collections.frequency(content, Treasure.SAPPHIRE));
    this.add(sapphireLabel);
    arrowLabel = createInfoJLabel(ImageCategory.ARROW, Collections.frequency(content, Weapon.ARROW));
    this.add(arrowLabel);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    List<Item> content = this.model.getPlayer().getCurrentLocation().getContent();

    diamondLabel.setText(String.valueOf(Collections.frequency(content, Treasure.DIAMOND)));
    rubyLabel.setText(String.valueOf(Collections.frequency(content, Treasure.RUBY)));
    sapphireLabel.setText(String.valueOf(Collections.frequency(content, Treasure.SAPPHIRE)));
    arrowLabel.setText(String.valueOf(Collections.frequency(content, Weapon.ARROW)));

    this.validate();
  }

  private JLabel createInfoJLabel(ImageCategory item, int count) {
    ImageIcon icon;

    try {
      InputStream imageStream = getClass().getResourceAsStream(item.getFilePath());
      icon = new ImageIcon(ImageIO.read(imageStream));
    } catch (IOException ioe) {
      throw new IllegalStateException("Cannot find icon location image!");
    }

    JLabel thumbnail = new JLabel();
    thumbnail.setIcon(icon);
    thumbnail.setText(" " + count + " ");

    return thumbnail;
  }
}
