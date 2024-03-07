package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import utils.Tuple;

/**
 * This class represents a Dungeon Builder that constructs a dungeon grid based on given parameters.
 */
class DungeonBuilder {
  private final boolean wrapping;
  private final int rows;
  private final int cols;
  private final int interconn;
  private final Random rand;

  /**
   * Constructs a DungeonBuilder with the specified parameters.
   *
   * @param rows      the number of rows in the dungeon grid
   * @param cols      the number of columns in the dungeon grid
   * @param wrapping  indicates whether the dungeon is wrapping
   * @param interconn the degree of interconnectivity in the dungeon
   * @param rand      the Random object used for generating random values
   */
  protected DungeonBuilder(int rows, int cols, boolean wrapping, int interconn, Random rand) {
    this.rows = rows;
    this.cols = cols;
    this.wrapping = wrapping;
    this.interconn = interconn;
    this.rand = rand;
  }

  /**
   * Builds the dungeon grid based on the parameters provided.
   *
   * @return a 2D array representing the dungeon grid
   */
  Location[][] buildDungeon() {
    Set<String> potentialPaths = getAllPotentialPaths(wrapping);
    List<Set<String>> setList = new ArrayList<>();
    HashMap<Direction, String>[][] directions = createPaths(setList, potentialPaths);
    return createCellsInDungeon(directions);
  }

  private Location[][] createCellsInDungeon(HashMap<Direction, String>[][] directions) {
    Location[][] locations = new Location[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        locations[row][col] = new Cell(row, col, directions[row][col]);
      }
    }
    return locations;
  }

  private HashMap<Direction, String>[][] createPaths(List<Set<String>> setList, Set<String> potentialPaths) {
    HashMap<Direction, String>[][] directions = new HashMap[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        String xy = row + " " + col;
        Set<String> nodeSet = new HashSet<>();
        nodeSet.add(xy);
        setList.add(nodeSet);
      }
    }

    int noOfPaths;
    List<String> paths = new ArrayList<>(potentialPaths);
    List<String> leftoverPaths = new ArrayList<>();

    while (paths.size() > 1) {
      noOfPaths = paths.size();
      String chosenPath = paths.get(rand.nextInt(noOfPaths));

      String[] nodes = chosenPath.split("-");
      int x1 = Integer.parseInt(nodes[0].split(" ")[0]);
      int y1 = Integer.parseInt(nodes[0].split(" ")[1]);
      int x2 = Integer.parseInt(nodes[1].split(" ")[0]);
      int y2 = Integer.parseInt(nodes[1].split(" ")[1]);

      int j = -1;
      int k = -1;
      for (int i = 0; i < setList.size(); i++) {
        if (setList.get(i).contains(nodes[0]) && setList.get(i).contains(nodes[1])) {
          leftoverPaths.add(chosenPath);
        } else {
          if (setList.get(i).contains(nodes[0])) {
            j = i;
          }
          if (setList.get(i).contains(nodes[1])) {
            k = i;
          }
        }
      }

      if (j != -1 && k != -1) {
        setList.set(j, mergeSet(setList.get(j), setList.get(k)));
        setList.remove(k);

        if (Objects.isNull(directions[x1][y1])) {
          directions[x1][y1] = new HashMap<>();
        }
        directions[x1][y1].put(getDirection(x1, y1, x2, y2), nodes[1]);

        if (Objects.isNull(directions[x2][y2])) {
          directions[x2][y2] = new HashMap<>();
        }
        directions[x2][y2].put(getDirection(x2, y2, x1, y1), nodes[0]);
      }

      paths.remove(chosenPath);
    }

    if (interconn > leftoverPaths.size()) {
      throw new IllegalArgumentException("Dungeon size too small for interconnectivity = " + interconn);
    }

    int interconnectivity = interconn;
    while (interconnectivity > 0) {
      String chosenPath;
      noOfPaths = leftoverPaths.size();
      chosenPath = leftoverPaths.get(rand.nextInt(noOfPaths));

      String[] nodes = chosenPath.split("-");
      int x1 = Integer.parseInt(nodes[0].split(" ")[0]);
      int y1 = Integer.parseInt(nodes[0].split(" ")[1]);
      int x2 = Integer.parseInt(nodes[1].split(" ")[0]);
      int y2 = Integer.parseInt(nodes[1].split(" ")[1]);

      if (Objects.isNull(directions[x1][y1])) {
        directions[x1][y1] = new HashMap<>();
      }
      directions[x1][y1].put(getDirection(x1, y1, x2, y2), nodes[1]);

      if (Objects.isNull(directions[x2][y2])) {
        directions[x2][y2] = new HashMap<>();
      }
      directions[x2][y2].put(getDirection(x2, y2, x1, y1), nodes[0]);

      leftoverPaths.remove(chosenPath);
      interconnectivity -= 1;
    }

    return directions;
  }

  private Set<String> getAllPotentialPaths(boolean wrapping) {
    Set<String> paths = new HashSet<>();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        Tuple<Integer, Integer> currentNode = new Tuple<>(row, col);
        List<String> currentPaths = getPathsForNode(currentNode, wrapping);
        paths = addToSet(currentPaths, paths);
      }
    }
    return paths;
  }

  private List<String> getPathsForNode(Tuple<Integer, Integer> currentNode, boolean wrapping) {
    int x = currentNode.getX();
    int y = currentNode.getY();
    List<String> currPaths = new ArrayList<>();

    int xPlus = x + 1;
    int xMinus = x - 1;
    int yPlus = y + 1;
    int yMinus = y - 1;

    if (xPlus < rows) {
      currPaths.add(String.format("%d %d-%d %d", x, y, xPlus, y));
    }
    if (xMinus >= 0) {
      currPaths.add(String.format("%d %d-%d %d", x, y, xMinus, y));
    }
    if (yPlus < cols) {
      currPaths.add(String.format("%d %d-%d %d", x, y, x, yPlus));
    }
    if (yMinus >= 0) {
      currPaths.add(String.format("%d %d-%d %d", x, y, x, yMinus));
    }

    if (wrapping) {
      if (xMinus < 0) {
        currPaths.add(String.format("%d %d-%d %d", x, y, rows - 1, y));
      }
      if (yPlus > cols) {
        currPaths.add(String.format("%d %d-%d %d", x, y, x, 0));
      }
      if (xPlus > rows) {
        currPaths.add(String.format("%d %d-%d %d", x, y, 0, y));
      }
      if (yMinus < 0) {
        currPaths.add(String.format("%d %d-%d %d", x, y, x, cols - 1));
      }
    }
    return currPaths;
  }

  private Set<String> addToSet(List<String> currentPaths, Set<String> paths) {
    for (String path : currentPaths) {
      if (!(paths.contains(path)) && !(paths.contains(flipPath(path)))) {
        paths.add(path);
      }
    }
    return paths;
  }

  private String flipPath(String path) {
    String[] points = path.split("-");
    return points[1] + "-" + points[0];
  }

  private Direction getDirection(int xOne, int yOne, int xTwo, int yTwo) {
    if ((xTwo - xOne) == -1 && (yTwo - yOne) == 0) {
      return Direction.NORTH;
    } else if ((yTwo - yOne) == 1 && (xTwo - xOne) == 0) {
      return Direction.EAST;
    } else if ((xTwo - xOne) == 1 && (yTwo - yOne) == 0) {
      return Direction.SOUTH;
    } else if ((yTwo - yOne) == -1 && (xTwo - xOne) == 0) {
      return Direction.WEST;
    } else if ((xTwo - xOne) == (rows - 1) && (yTwo - yOne) == 0) {
      return Direction.NORTH;
    } else if ((yOne - yTwo) == (cols - 1) && (xTwo - xOne) == 0) {
      return Direction.EAST;
    } else if ((xOne - xTwo) == (rows - 1) && (yTwo - yOne) == 0) {
      return Direction.SOUTH;
    } else {
      return Direction.WEST;
    }
  }

  private <T> Set<T> mergeSet(Set<T> a, Set<T> b) {
    Set<T> mergedSet = new HashSet<>();
    mergedSet.addAll(a);
    mergedSet.addAll(b);
    return mergedSet;
  }
}
