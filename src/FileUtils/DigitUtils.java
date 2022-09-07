package FileUtils;

import UI.ClockAction;
import UI.GridController;
import javafx.util.Duration;

// Build builds digits 0-9 in the first position. Then we define an offset for the other 3 positions.
// Now we can display any digits in any position. This class also provides wrappers to sanitize and
// display time formats.
public class DigitUtils {

    private ClockAction[][] clockGrid;
    private GridController gridController = GridController.getInstance();
    private Duration delay;

    public DigitUtils(int num) {
        clockGrid = new ClockAction[gridController.getGridHeight()][gridController.getGridWidth()];
        createBorder();
        printNumber(num);
        this.delay = Duration.ZERO;
    }

    public DigitUtils(int num, Duration delay) {
        clockGrid = new ClockAction[gridController.getGridHeight()][gridController.getGridWidth()];
        this.delay = delay;
        createBorder();
        printNumber(num);
    }

    public ClockAction getAction(int x, int y) {
        return clockGrid[x][y];
    }

    public void printNumber(int num) {
        if(num < 0 || num > 9999) {
            return;
        }

        for(int i=4; i>0; i--) {
            addDigit(i,num%10);
            num /= 10;
        }
    }

    private void createBorder() {

        float defaultSpeed = 40;

        for(int k=1; k< gridController.getGridHeight()-1; k++) {
            for(int l=1; l< gridController.getGridWidth()-1; l++) {
                clockGrid[k][l] = new ClockAction(0,0,0, this.delay);
            }
        }

        // top and bottom border
        for(int i=0; i<gridController.getGridWidth(); i++) {
            clockGrid[0][i] = new ClockAction(-135,-135,defaultSpeed, this.delay);
            clockGrid[gridController.getGridHeight()-1][i] = new ClockAction(-135,-135,defaultSpeed, this.delay);
        }

        // left and right sides
        for(int j=0; j< gridController.getGridHeight(); j++) {
            clockGrid[j][0] = new ClockAction(-135,-135,defaultSpeed, this.delay);
            clockGrid[j][gridController.getGridWidth()-1] = new ClockAction(-135,-135,defaultSpeed, this.delay);
        }

        // divider
        for(int i=1; i< gridController.getGridHeight()-1; i++) {
            clockGrid[i][7] = new ClockAction(-135,-135,defaultSpeed, this.delay);
        }

        // Colon
        clockGrid[2][7] = new ClockAction(90,-90,defaultSpeed, this.delay);
        clockGrid[5][7] = new ClockAction(90,-90,defaultSpeed, this.delay);
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
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                for(int i=2; i<6; i++) {
                    clockGrid[i][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(-90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,90,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                break;
            case 1:
                for(int i=1; i<7; i++) {
                    clockGrid[i][1+offset] = new ClockAction(-135,-135,defaultSpeed, this.delay);
                }
                clockGrid[1][2+offset] = new ClockAction(-90,0,defaultSpeed, this.delay);
                for(int i=2; i<6; i++) {
                    clockGrid[i][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[6][2+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                for(int i=2; i<6; i++) {
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                break;
            case 2:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                clockGrid[3][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                break;
            case 3:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                clockGrid[3][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                break;
            case 4:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[2][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(-135,-135,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(-135,-135,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                break;
            case 5:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[2][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                clockGrid[3][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(0,90,defaultSpeed, this.delay);
                clockGrid[3][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[4][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                break;
            case 6:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(-90,0,defaultSpeed, this.delay);
                clockGrid[2][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                clockGrid[3][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[3][3+offset] = new ClockAction(-90,180,defaultSpeed, this.delay);
                clockGrid[4][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(-90,-90,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,90,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);

                break;
            case 7:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[2][1+offset] = new ClockAction(0,90,defaultSpeed, this.delay);
                for(int i=3; i<7; i++) {
                    clockGrid[i][1+offset] = new ClockAction(-135,-135,defaultSpeed, this.delay);
                }
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                for(int i=3; i<6; i++) {
                    clockGrid[i][2+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[6][2+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                for(int i=2; i<6; i++) {
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[6][3+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                break;
            case 8:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                for(int i=2; i<6; i++) {
                    clockGrid[i][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(-90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(90,90,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(-90,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(90,90,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                break;
            case 9:
                clockGrid[1][1+offset] = new ClockAction(0,-90,defaultSpeed, this.delay);
                clockGrid[1][3+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                for(int i=2; i<4; i++) {
                    clockGrid[i][1+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                    clockGrid[i][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                }
                clockGrid[4][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[5][1+offset] = new ClockAction(-90,0,defaultSpeed, this.delay);
                clockGrid[6][1+offset] = new ClockAction(90,0,defaultSpeed, this.delay);
                clockGrid[4][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[5][3+offset] = new ClockAction(90,-90,defaultSpeed, this.delay);
                clockGrid[6][3+offset] = new ClockAction(90,180,defaultSpeed, this.delay);
                clockGrid[1][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                clockGrid[2][2+offset] = new ClockAction(-90,-90,defaultSpeed, this.delay);
                clockGrid[3][2+offset] = new ClockAction(90,90,defaultSpeed, this.delay);
                clockGrid[4][2+offset] = new ClockAction(180,-90,defaultSpeed, this.delay);
                clockGrid[5][2+offset] = new ClockAction(180,90,defaultSpeed, this.delay);
                clockGrid[6][2+offset] = new ClockAction(180,0,defaultSpeed, this.delay);
                break;

        }
    }


}
