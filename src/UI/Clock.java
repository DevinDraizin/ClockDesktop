package UI;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.*;

// JavaFX Node class, and it's children (including Pane) all inherit a coordinate system where
// the y-axis is inverted from the traditional cartesian plane. We are operating in the 4th
// quadrant of a cartesian plane so (0,0) is in the top left and as y increases we move down.
public class Clock extends Pane {

    private Rotate hand1Rotation;
    private Rotate hand2Rotation;

    private float hand1Angle;
    private float hand2Angle;

    private final float size;
    private final float radius;

    private volatile boolean running;

    private ArrayList<ClockAction> clockActions;
    private ArrayList<Float> actionDurations;

    private SequentialTransition animation;

    public enum HandNum {
        HAND1,HAND2
    }

    public float getHand1Angle() {
        return hand1Angle;
    }

    public float getHand2Angle() {
        return hand2Angle;
    }

    public Clock(float size) {
        this.size = size;
        this.radius = size/2;

        //Default hand angle
        this.hand1Angle = 0;
        this.hand2Angle = 0;

        //Prevent Pane from resizing
        this.setMinHeight(size);
        this.setMaxHeight(size);
        this.setMinWidth(size);
        this.setMaxWidth(size);

        this.initializeClock();

        this.clockActions = new ArrayList<>();
        this.actionDurations = new ArrayList<>();
        this.animation = new SequentialTransition();
    }

    private void initializeClock() {
        Circle clockBody = drawCircle();
        Circle clockPivot = new Circle(this.size/2,this.size/2,(size * .01),Paint.valueOf("black"));
        Line hand1 = drawHand(HandNum.HAND1);
        Line hand2 = drawHand(HandNum.HAND2);

        getStylesheets().add("UI/CSS/clockStyle.css");

        //CSS ids
        clockBody.setId("clockFace");
        clockPivot.setId("clockPivot");
        hand1.setId("hand1");
        hand2.setId("hand2");

        this.getChildren().addAll(clockBody, hand1, hand2, clockPivot);
    }

    // Whenever an action is added to a clock we recalculate
    // all the durations.
    public void addAction(ClockAction action) {
        this.clockActions.add(action);
        calculateDurations();
    }

    public void addAllActions(ClockAction ... actions) {
        this.clockActions.addAll(Arrays.asList(actions));
        calculateDurations();
    }

    public void clearActions() {
        this.clockActions.clear();
    }

    public List<ClockAction> getClockActionsUnmodifiable() {
        return Collections.unmodifiableList(this.clockActions);
    }

    public void runActions() {

        for(int i =0; i<this.clockActions.size(); i++) {
            animation.getChildren().add(new Timeline(getKeyFrameForHands(this.clockActions.get(i), this.actionDurations.get(i))));
            if(this.clockActions.get(i).getDelay().greaterThan(Duration.ZERO)) {
                animation.getChildren().add(new PauseTransition(this.clockActions.get(i).getDelay()));
            }
        }
        animation.playFromStart();
        this.running = true;
    }

    public void pauseActions() {
        if(this.running) {
            this.animation.pause();
            this.running = false;
        }
    }

    public void resumeActions() {
        if(!this.running) {
            this.animation.play();
            this.running = true;
        }
    }

    // Since the duration of a particular clock action depends on the ending angle of the previous action
    // we calculate the animation durations for all actions as they would play in sequence.
    private void calculateDurations() {
        if(!this.clockActions.isEmpty()) {
            this.actionDurations.clear();

            float currentAngle1 = this.hand1Angle;
            float currentAngle2 = this.hand2Angle;

            for (ClockAction clockAction : clockActions) {
                this.actionDurations.add(calculateDuration(clockAction, currentAngle1, currentAngle2));
                currentAngle1 = clockAction.getAngle1();
                currentAngle2 = clockAction.getAngle2();
            }
        }
    }

    // Calculates the duration an animation should take based on (degrees traveled / speed) for each hand
    // and returns the larger of the two durations.
    private float calculateDuration(ClockAction action, float hand1Angle, float hand2Angle) {
        float dist1 = action.getAngle1() < 0 ? -action.getAngle1() + hand1Angle : action.getAngle1() - hand1Angle;
        float dist2 = action.getAngle2() < 0 ? -action.getAngle2() + hand2Angle : action.getAngle2() - hand2Angle;

        float duration1 = (dist1 / action.getSpeed1());
        float duration2 = (dist2 / action.getSpeed2());

        return Math.max(Math.abs(duration1), Math.abs(duration2));
    }


    private KeyFrame getKeyFrameForHands(ClockAction action, float duration) {
        return new KeyFrame(Duration.seconds(duration), e -> {
            // Normalize and update new angles for hands
            this.hand1Angle = normalizeAngle(action.getAngle1());
            this.hand2Angle = normalizeAngle(action.getAngle2());
        } , new KeyValue(this.hand1Rotation.angleProperty(), -action.getAngle1()),
            new KeyValue(this.hand2Rotation.angleProperty(), -action.getAngle2()));
    }

    private float normalizeAngle(float angle) {
        return angle < 0 ? 360 - ((-angle) % 360) : angle%360;
    }

    private Circle drawCircle() {
        Circle circle = new Circle();

        circle.setCenterX(this.size/2);
        circle.setCenterY(this.size/2);
        circle.setRadius(this.radius);

        return circle;
    }

    // Hand angles reflect position of a unit circle (0-360) degrees increasing CCW where
    // 0 degrees is at (1,0) on a unit circle.
    private Line drawHand(HandNum handNum) {
        Line hand = new Line();

        hand.setScaleX(.9);
        hand.setScaleY(.9);

        hand.setStartX(this.size/2);
        hand.setStartY(this.size/2);

        if(handNum == HandNum.HAND1) {
            this.hand1Rotation = new Rotate();
            this.hand1Rotation.pivotXProperty().bind(hand.startXProperty());
            this.hand1Rotation.pivotYProperty().bind(hand.startYProperty());

            hand.setEndX((this.radius)+this.radius*Math.cos(Math.toRadians(-this.hand1Angle)));
            hand.setEndY((this.radius)+this.radius*Math.sin(Math.toRadians(-this.hand1Angle)));

            hand.getTransforms().add(this.hand1Rotation);
        }else {
            this.hand2Rotation = new Rotate();
            this.hand2Rotation.pivotXProperty().bind(hand.startXProperty());
            this.hand2Rotation.pivotYProperty().bind(hand.startYProperty());

            hand.setEndX((this.radius)+this.radius*Math.cos(Math.toRadians(-this.hand2Angle)));
            hand.setEndY((this.radius)+this.radius*Math.sin(Math.toRadians(-this.hand2Angle)));

            hand.getTransforms().add(this.hand2Rotation);
        }

        return hand;
    }

}
