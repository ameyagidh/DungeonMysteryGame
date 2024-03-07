package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import controller.GuiGameController;
import controller.GuiGameFeatures;
import model.ReadonlyGameModel;

/**
 * The GuiGameView class represents a GUI-based view for the dungeon adventure game.
 * It sets all the features described in the controller and attaches listeners to appropriate
 * components in the view. It also gives callbacks to the controller by capturing listener events.
 */
public class GuiGameView extends JFrame implements GameView {
  private final ReadonlyGameModel model;

  private final JMenuItem newGameMenuItem;
  private final JMenuItem resetGameMenuItem;
  private final JMenuItem quitGameMenuItem;

  private final DungeonPanel dungeonPanel;

  /**
   * Constructs a GuiGameView object with the specified read-only model.
   *
   * @param rom The read-only model representing the game model.
   */
  public GuiGameView(ReadonlyGameModel rom) {
    super("DAG - Dungeon Adventure Game");

    if (Objects.isNull(rom)) {
      this.showMessage("Read-only model specified cannot be null",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Set frame properties
    this.setMinimumSize(new Dimension(650, 650));
    this.setLocation(300, 50);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.model = rom;

    // Set layout for the content pane
    this.getContentPane().setLayout(new BorderLayout());

    // Create menu items
    this.newGameMenuItem = new JMenuItem("New Game");
    this.resetGameMenuItem = new JMenuItem("Reset Game");
    this.quitGameMenuItem = new JMenuItem("Quit Game");

    // Create menu and add menu items
    JMenu menu = new JMenu("Game Menu");
    menu.add(this.newGameMenuItem);
    menu.add(this.resetGameMenuItem);
    menu.add(this.quitGameMenuItem);

    // Create menu bar and add menu
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);

    // Add menu bar and visual cues for playing the game
    JPanel topBar = new JPanel();
    topBar.setLayout(new BorderLayout());
    topBar.add(menuBar, BorderLayout.WEST);
    JPanel visualCues = new JPanel();
    visualCues.setLayout(new FlowLayout());
    visualCues.add(new JLabel("Shoot : [W] [A] [S] [D] "));
    visualCues.add(new JLabel("Pick : "));
    visualCues.add(createInfoJLabel(ImageCategory.DIAMOND, "[X] "));
    visualCues.add(createInfoJLabel(ImageCategory.RUBY, "[C] "));
    visualCues.add(createInfoJLabel(ImageCategory.SAPPHIRE, "[V] "));
    visualCues.add(createInfoJLabel(ImageCategory.ARROW, "[Z] "));
    topBar.add(visualCues, BorderLayout.CENTER);
    this.add(topBar, BorderLayout.NORTH);

    // Create scrollable dungeon map
    this.dungeonPanel = new DungeonPanel(rom);
    JScrollPane scrollablePanel = new JScrollPane(this.dungeonPanel);
    scrollablePanel.setAutoscrolls(true);
    scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.getContentPane().add(scrollablePanel, BorderLayout.CENTER);

    // Create information panel for player and location details
    InfoPanel infoPanel = new InfoPanel(rom);
    this.getContentPane().add(infoPanel, BorderLayout.SOUTH);
  }

  @Override
  public void setFeatures(GuiGameFeatures f) {
    // Add action listeners to menu items
    this.newGameMenuItem.addActionListener(l -> f.restartGame());
    this.resetGameMenuItem.addActionListener(l -> f.resetGame());
    this.quitGameMenuItem.addActionListener(l -> f.quitGame());

    // Add key listener and mouse listener to dungeon panel
    this.addKeyListener(new GameKeyListener(this.model, f, this));
    this.dungeonPanel.addMouseListener(f);

    this.setVisible(true);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void delete() {
    this.setVisible(false);
    this.dispose();
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void showMessage(String error, String title, int type) {
    JOptionPane.showMessageDialog(this, error, title, type);
  }

  private static class GameKeyListener implements KeyListener {
    private final ReadonlyGameModel model;
    private final GuiGameView view;
    private final GuiGameController controller;

    public GameKeyListener(ReadonlyGameModel m, GuiGameFeatures f, GuiGameView v) {
      this.model = m;
      this.controller = (GuiGameController) f;
      this.view = v;
    }

    @Override
    public void keyTyped(KeyEvent e) {
      // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (!this.model.isGameOver()) {
        // Handle movement and actions based on key events
        switch (e.getKeyCode()) {
          case KeyEvent.VK_UP:
            controller.move(0);
            break;
          case KeyEvent.VK_DOWN:
            controller.move(1);
            break;
          case KeyEvent.VK_RIGHT:
            controller.move(2);
            break;
          case KeyEvent.VK_LEFT:
            controller.move(3);
            break;
          case KeyEvent.VK_Z:
            controller.pick(0);
            break;
          case KeyEvent.VK_X:
            controller.pick(1);
            break;
          case KeyEvent.VK_C:
            controller.pick(2);
            break;
          case KeyEvent.VK_V:
            controller.pick(3);
            break;
          case KeyEvent.VK_W:
            controller.shoot(0, this.popUp());
            break;
          case KeyEvent.VK_S:
            controller.shoot(1, this.popUp());
            break;
          case KeyEvent.VK_D:
            controller.shoot(2, this.popUp());
            break;
          case KeyEvent.VK_A:
            controller.shoot(3, this.popUp());
            break;
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      // Not used
    }

    private String popUp() {
      String input = JOptionPane.showInputDialog(this.view,
              "Enter number of caves to shoot over?", null);

      if (Objects.equals(input, "")) {
        this.view.showMessage("Shooting distance invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        return "";
      }

      return input;
    }
  }

  private JLabel createInfoJLabel(ImageCategory item, String key) {
    ImageIcon icon;

    try {
      InputStream imageStream = getClass().getResourceAsStream(item.getFilePath());
      icon = new ImageIcon(ImageIO.read(imageStream));
    } catch (IOException ioe) {
      throw new IllegalStateException("Cannot find icon location image!");
    }

    JLabel thumbnail = new JLabel();
    thumbnail.setIcon(icon);
    thumbnail.setText(" " + key + " ");

    return thumbnail;
  }
}
