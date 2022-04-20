package UI;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class DriverLayoutController {

    private GridController clockController = GridController.getInstance();


    @FXML
    GridPane clockGridLayout;

    public void initialize() {
        initializeGridLayout();
    }

    public void startAction() {
        testClockAction();
    }

    private void initializeGridLayout() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockGridLayout.add(clockController.getClock(i,j),j,i);
            }
        }
    }

    private void clearAllClocks() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).clearActions();
            }
        }
    }


    private void testClockAction() {
        ClockAction action1 = new ClockAction(40,-40,30,30);
        ClockAction action2 = new ClockAction(-40,40,30,30);
        ClockAction action3 = new ClockAction(180,0,30,30);
        ClockAction action4 = new ClockAction(0,180,30,30);
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).addAllActions(action1,action2,action3,action4);
                clockController.getClock(i,j).runActions();
            }
        }
        clearAllClocks();
    }

}
