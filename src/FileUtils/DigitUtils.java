package FileUtils;

import UI.ClockAction;
import UI.GridController;

// This is a class that will be able to load in all the possible digits for a clock including the border and
// minute/hour divider. The idea is to create a yaml format that is modular enough to allow this class to
// construct any time representation on the fly by figuring out the positions. Since Jackson seems to only
// care that the yaml structure matches the object you are trying to create from it, we might as well create
// a completely separate format just for digits.
//
// All digits fit inside a 3x6 grid of clocks. A time representation for now will just be a single action sent to each
// clock instructing it to go to the correct position. So we can start out with a grid
public class DigitUtils {

    private ClockAction[][] clockGrid;
    private GridController gridController = GridController.getInstance();

    public DigitUtils() {
        clockGrid = new ClockAction[gridController.getGridHeight()][gridController.getGridWidth()];
        createBorder();
        addDigit(1,0);
        addDigit(2,0);
        addDigit(3,0);
        addDigit(4,0);
    }

    public ClockAction getAction(int x, int y) {
        return clockGrid[x][y];
    }

    private void createBorder() {
        
        float defaultSpeed = 40;

        for(int k=1; k< gridController.getGridHeight()-1; k++) {
            for(int l=1; l< gridController.getGridWidth()-1; l++) {
                clockGrid[k][l] = new ClockAction(0,0,0,0);
            }
        }

        // top and bottom border
        for(int i=0; i<gridController.getGridWidth(); i++) {
            clockGrid[0][i] = new ClockAction(-135,-135,defaultSpeed,defaultSpeed);
            clockGrid[gridController.getGridHeight()-1][i] = new ClockAction(-135,-135,defaultSpeed,defaultSpeed);
        }

        // left and right sides
        for(int j=0; j< gridController.getGridHeight(); j++) {
            clockGrid[j][0] = new ClockAction(-135,-135,defaultSpeed,defaultSpeed);
            clockGrid[j][gridController.getGridWidth()-1] = new ClockAction(-135,-135,defaultSpeed,defaultSpeed);
        }

        // divider
        for(int i=1; i< gridController.getGridHeight()-1; i++) {
            clockGrid[i][7] = new ClockAction(-135,-135,defaultSpeed,defaultSpeed);
        }
    }

    private void addDigit(int pos, int digit) {

        int offset;
        float defaultSpeed = 40;

        if(pos == 1) {
            offset = 0;
        } else if(pos == 2) {
            offset = 3;
        }else if(pos == 3) {
            offset = 7;
        }else {
            offset = 10;
        }

        switch (digit) {
            case 0:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed,defaultSpeed);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed,defaultSpeed);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed,defaultSpeed);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed,defaultSpeed);
                for(int i=2; i<6; i++) {
                    clockGrid[i][1+offset] = new ClockAction(90,-90,defaultSpeed,defaultSpeed);
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed,defaultSpeed);
                }
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed,defaultSpeed);
                clockGrid[2][2+offset] = new ClockAction(-90,-90,defaultSpeed,defaultSpeed);
                clockGrid[3][2+offset] = new ClockAction(90,-90,defaultSpeed,defaultSpeed);
                clockGrid[4][2+offset] = new ClockAction(90,-90,defaultSpeed,defaultSpeed);
                clockGrid[5][2+offset] = new ClockAction(90,90,defaultSpeed,defaultSpeed);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed,defaultSpeed);
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;

        }
    }


}
