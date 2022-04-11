package UI;

public class ClockController {

    private clock[][] clockGrid;
    private int gridHeight;
    private int gridWidth;
    private float clockSize;

    public int getGridHeight() {
        return this.gridHeight;
    }

    public int getGridWidth() {
        return this.gridWidth;
    }

    public ClockController(int clockSize) {
        this.clockSize = clockSize;
        this.gridHeight = 8;
        this.gridWidth = 15;

        makeGrid(gridHeight,gridWidth);
    }

    public clock getClock(int x, int y) {
        return clockGrid[x][y];
    }

    private void makeGrid(int clocksTall, int clocksWide) {
        clockGrid = new clock[clocksTall][clocksWide];
        for(int i=0; i<gridHeight; i++) {
            for(int j=0; j<gridWidth; j++) {
                clockGrid[i][j] = new clock(this.clockSize);
            }
        }
    }
}
