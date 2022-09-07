package UI;

import FileUtils.DigitUtils;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
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
        // Work on a way to synchronize clock actions.
        // i.e. we need to be able to wait for the last
        // clock to finish its animation before running
        // the next queued action. But we don't want to
        // overwrite the current delay function in ClockActions.
        //
        // One idea is to not add a new set of actions to
        // the clocks until all the actions being run have
        // finished. We could search the clocks for the one
        // that has the largest animation duration.
        //
        // Maybe some sort of middleware that can take
        // a full grid of actions and normalize all
        // the actions by adding delays making them
        // all match

//        setDigitalClock(1000);
//        Duration duration1 = getMaxDuration();
//        clearAllClocks();
//        setDigitalClock(1001);
//        Duration duration2 = getMaxDuration();
//        clearAllClocks();
//        setDigitalClock(1002);
//        Duration duration3 = getMaxDuration();
//        clearAllClocks();
//        setDigitalClock(1004);
//        Duration duration4 = getMaxDuration();
//        clearAllClocks();
//        System.out.println(duration1 + "\n" + duration2 + "\n" + duration3 + "\n" + duration4);
//
//
//        SequentialTransition animation = new SequentialTransition();
//
//        animation.getChildren().add(new Timeline(new KeyFrame(Duration.millis(1), e -> {
//            setDigitalClock(1000);
//            runAllClocks();
//        })));
//        animation.getChildren().add(new PauseTransition(duration1));
//        animation.getChildren().add(new Timeline(new KeyFrame(Duration.millis(1), e -> {
//            setDigitalClock(1001);
//            runAllClocks();
//        })));
//        animation.getChildren().add(new PauseTransition(duration2));
//        animation.getChildren().add(new Timeline(new KeyFrame(Duration.millis(1), e -> {
//            setDigitalClock(1002);
//            runAllClocks();
//        })));
//        animation.getChildren().add(new PauseTransition(duration3));
//        animation.getChildren().add(new Timeline(new KeyFrame(Duration.millis(1), e -> {
//            setDigitalClock(1003);
//            runAllClocks();
//        })));
//        animation.getChildren().add(new PauseTransition(duration4));
//
//        animation.playFromStart();

//        setDigitalClock(2849);
//        setDigitalClock(2850);
//        setDigitalClock(1256);
//        runAllClocks();
        Clock test = clockController.getClock(0,0);
        test.addAction(new ClockAction(60,-60,20,Duration.seconds(3)));
        test.addAction(new ClockAction(20,-20,20, Duration.seconds(2)));
        test.addAction(new ClockAction(80,-80,20, Duration.seconds(0)));
        test.runActions();
        System.out.println(test.getAnimationDuration());
    }

    private Duration getMaxDuration() {
        Duration max = Duration.ZERO;
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                if(clockController.getClock(i,j).getAnimationDuration().greaterThan(max)) {
                    max = clockController.getClock(i,j).getAnimationDuration();
                }
            }
        }

        return max.add(Duration.seconds(2));
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
        DigitUtils digits = new DigitUtils(num);
        for(int i=0; i<clockController.getGridHeight(); i++) {
            for(int j=0; j<clockController.getGridWidth(); j++) {
                clockController.getClock(i,j).addAction(digits.getAction(i,j));
            }
        }
    }

}
