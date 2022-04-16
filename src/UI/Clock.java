package UI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class Clock extends Pane {

    private Rotate hand1Rotation;
    private Rotate hand2Rotation;

    private float hand1Angle;
    private float hand2Angle;

    private final float size;
    private final float radius;

    private ArrayList<ClockAction> clockActions;

    private volatile boolean isMoving = false;

    public enum HandNum {
        HAND1,HAND2
    }

    public boolean getIsMoving() {
        return isMoving;
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

        initializeClock();

        this.clockActions = new ArrayList<>();
    }

    private void initializeClock() {
        Circle clockBody = drawCircle();
        clockBody.setFill(Paint.valueOf("white"));
        clockBody.setStroke(Paint.valueOf("black"));
        Circle clockPivot = new Circle(this.size/2,this.size/2,(size * .06),Paint.valueOf("black"));
        Line hand1 = drawHand(1);
        Line hand2 = drawHand(2);

        getStylesheets().add("UI/CSS/clockStyle.css");

        //CSS ids
        clockBody.setId("clockFace");
        clockPivot.setId("clockPivot");
        hand1.setId("hand1");
        hand2.setId("hand2");

        this.getChildren().addAll(clockBody, hand1, hand2, clockPivot);
    }

    public void addAction(ClockAction action) {
        this.clockActions.add(action);
    }

    public void addAllActions(ClockAction ... actions) {
        this.clockActions.addAll(Arrays.asList(actions));
    }

    public void clearActions() {
        this.clockActions.clear();
    }

    public void runActions() {
        Timeline timeLine = new Timeline();
        for(int i=0; i<this.clockActions.size(); i++) {

            final ClockAction action = this.clockActions.get(i);
            final float duration = calculateDuration(action, i);

            timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(duration), t -> {
                getTimelineForHand(action, HandNum.HAND1).play();
                getTimelineForHand(action, HandNum.HAND2).play();
            }));
        }
        timeLine.play();
    }

    // Returns the longer of the two hand animations
    private float calculateDuration(ClockAction action, int index) {
        // Stupid hack
        if(index == 0) {
            return (float).001;
        }
        float duration1 = (Math.abs(this.hand1Angle - action.getAngle1()) / action.getSpeed1());
        float duration2 = (Math.abs(this.hand1Angle - action.getAngle2()) / action.getSpeed2());
        return Math.max(duration1, duration2);
    }

    public Timeline getTimelineForHand(ClockAction action, HandNum hand) {
        if(hand == HandNum.HAND1) {
            float duration = (Math.abs(this.hand1Angle - action.getAngle1())/action.getSpeed1());

            Timeline hand1Animation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(this.hand1Rotation.angleProperty(), this.hand1Angle)),
                    new KeyFrame(Duration.seconds(duration), new KeyValue(this.hand1Rotation.angleProperty(), action.getAngle1())));

            this.hand1Angle = action.getAngle1();
            return hand1Animation;
        }else {
            float duration = (Math.abs(this.hand2Angle - action.getAngle2())/action.getSpeed2());

            Timeline hand2Animation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(this.hand2Rotation.angleProperty(), this.hand2Angle)),
                    new KeyFrame(Duration.seconds(duration), new KeyValue(this.hand2Rotation.angleProperty(), action.getAngle2())));

            this.hand2Angle = action.getAngle2();
            return hand2Animation;
        }
    }

    private Circle drawCircle() {
        Circle circle = new Circle();

        circle.setCenterX(this.size/2);
        circle.setCenterY(this.size/2);
        circle.setRadius(this.radius);

        return circle;
    }

    private Line drawHand(int handNum) {
        Line hand = new Line();

        hand.setStartX(this.size/2);
        hand.setStartY(this.size/2);

        if(handNum == 1) {
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
