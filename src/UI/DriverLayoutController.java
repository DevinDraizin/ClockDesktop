package UI;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class DriverLayoutController {

    private ClockController clockController = new ClockController(80);


    @FXML
    GridPane clockGridLayout;

    public void initialize() {
        initializeGridLayout();
        test();
    }

    private void initializeGridLayout() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockGridLayout.add(clockController.getClock(i,j),j,i);
            }
        }
    }

    private void test() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                if(j % 2 == 0) {
                    clockController.getClock(i,j).rotateForTime(30, clock.HandNum.HAND1, clock.Direction.CW);
                    clockController.getClock(i,j).rotateForTime(30, clock.HandNum.HAND2, clock.Direction.CCW);
                }else {
                    clockController.getClock(i,j).rotateForTime(30, clock.HandNum.HAND1, clock.Direction.CCW);
                    clockController.getClock(i,j).rotateForTime(30, clock.HandNum.HAND2, clock.Direction.CW);
                }

            }
        }
    }
}
