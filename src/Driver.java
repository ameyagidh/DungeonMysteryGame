import java.io.InputStreamReader;
import java.util.Random;

import controller.GameController;
import controller.GuiGameController;
import controller.GuiGameFeatures;
import controller.TextBasedGameController;
import model.AdventureGameModel;
import model.GameModel;
import utils.Randomizer;

/**
 * The Driver class is responsible for initializing the Dungeon Adventure Game
 * by creating the game model and controller and starting the game.
 */
public class Driver {

  /**
   * The main method to drive the text-based game.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    // Uncomment the next line to use default arguments
    // args = new String[]{"", "10", "false", "0", "50", "20"};

    // Check if the correct number of command-line arguments is provided
    if (args.length == 6) {
      try {
        // Parsing the user-provided arguments
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        boolean wrap = Boolean.parseBoolean(args[2]);
        int interconn = Integer.parseInt(args[3]);
        int percent = Integer.parseInt(args[4]);
        int difficulty = Integer.parseInt(args[5]);

        // Create a deterministic random object
        Random rand = new Randomizer(42).getRandom();

        // Create the Adventure Game Model
        GameModel model;

        try {
          // Print information about the dungeon being created
          System.out.printf("Building dungeon with %s rows, %s columns, "
                  + "interconnectivity = %s, %s percent of treasure caves, "
                  + "difficulty level = %s "
                  + "and wrapping = %s\n\n", rows, cols, interconn, percent, difficulty, wrap);

          model = new AdventureGameModel(rand, rows, cols, wrap, interconn, percent, difficulty);
        } catch (IllegalStateException | IllegalArgumentException e) {
          // Catch exceptions if the dungeon creation fails
          System.out.println(e.getMessage());
          return;
        }

        // Create the console-based Adventure Game Controller
        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out;

        GameController controller = new TextBasedGameController(input, output, model);

        // Start the game
        controller.playGame();
      } catch (NumberFormatException nfe) {
        // Catch exception if parsing fails
        System.out.println("Unable to parse dungeon arguments, please check and try again");
      }
    }
    // Check if no arguments are provided, implying GUI mode
    else if (args.length == 0) {
      try {
        // Create and start the GUI Adventure Game Controller
        GuiGameFeatures controller = new GuiGameController();
      } catch (IllegalArgumentException e) {
        // Catch exceptions if GUI controller creation fails
        System.out.println(e.getMessage());
      }
    } else {
      // Print an error message if incorrect number of arguments provided
      System.out.println("Missing arguments. Required 6, given" + args.length);
    }
  }
}
