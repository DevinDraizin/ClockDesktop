package UI;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class DriverLayoutController {

    private GridController clockController = new GridController(90);


    @FXML
    GridPane clockGridLayout;

    public void initialize() {
        initializeGridLayout();
        testClockAction();
    }

    private void initializeGridLayout() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockGridLayout.add(clockController.getClock(i,j),j,i);
            }
        }
    }

    private void testClockAction() {
        ClockAction action1 = new ClockAction(40,-40,20,20);
        ClockAction action2 = new ClockAction(20,-20,20,20);
        ClockAction action3 = new ClockAction(60,-60,20,20);
        Clock clock = clockController.getClock(0,0);
        clock.addAction(action1);
        clock.addAction(action2);
        clock.addAction(action3);
        clock.runActions();
    }

}
