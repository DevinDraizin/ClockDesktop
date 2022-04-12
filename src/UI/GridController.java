package UI;

public class GridController {

    private Clock[][] clockGrid;
    private int gridHeight;
    private int gridWidth;
    private float clockSize;

    public int getGridHeight() {
        return this.gridHeight;
    }

    public int getGridWidth() {
        return this.gridWidth;
    }

    public GridController(int clockSize) {
        this.clockSize = clockSize;
        this.gridHeight = 8;
        this.gridWidth = 15;

        makeGrid(gridHeight,gridWidth);
    }

    public Clock getClock(int x, int y) {
        return clockGrid[x][y];
    }

    private void makeGrid(int clocksTall, int clocksWide) {
        clockGrid = new Clock[clocksTall][clocksWide];
        for(int i=0; i<gridHeight; i++) {
            for(int j=0; j<gridWidth; j++) {
                clockGrid[i][j] = new Clock(this.clockSize);
            }
        }
    }
}
