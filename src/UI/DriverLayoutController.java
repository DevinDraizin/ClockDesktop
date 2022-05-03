package UI;

import FileUtils.DigitUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class DriverLayoutController {

    private GridController clockController = GridController.getInstance();


    @FXML
    GridPane clockGridLayout;

    public void initialize() {
        initializeGridLayout();
    }

    public void startAction() {
        setDigitalClock(2849);
        setDigitalClock(2850);
        setDigitalClock(2851);
        runAllClocks();
    }

    public void pauseActions() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).pauseActions();
            }
        }
    }

    public void resumeActions() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).resumeActions();
            }
        }
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

    private void runAllClocks() {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).runActions();
            }
        }
    }

    private void setAllClocks(ClockAction action) {
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).addAction(action);
            }
        }
    }



    private void setDigitalClock(int num) {
        DigitUtils digits = new DigitUtils(num, Duration.seconds(5));
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).addAction(digits.getAction(i,j));
            }
        }
    }

}
