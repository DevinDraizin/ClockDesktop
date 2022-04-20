package UI;

// Creates, manages, and loads clock mechanics
public class GridController {

    private static GridController gridController = null;

    private Clock[][] clockGrid;

    // default params
    private final int gridHeight = 8;
    private final int gridWidth = 15;
    private final float clockSize = 90;

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public static GridController getInstance() {
        return gridController == null ? new GridController() : gridController;
    }

    private GridController() {
        makeGrid();
    }

    public Clock getClock(int x, int y) {
        return clockGrid[x][y];
    }

    private void makeGrid() {
        clockGrid = new Clock[gridHeight][gridWidth];
        for(int i=0; i<gridHeight; i++) {
            for(int j=0; j<gridWidth; j++) {
                clockGrid[i][j] = new Clock(clockSize);
            }
        }
    }
}
