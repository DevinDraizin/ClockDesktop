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

public class Clock extends Pane {

    private Rotate hand1Rotation;
    private Rotate hand2Rotation;

    private float hand1Angle;
    private float hand2Angle;

    //Hand speed measured in degrees per second
    private float hand1Speed;
    private float hand2Speed;

    private final float size;
    private final float radius;

    private volatile boolean isMoving = false;

    public enum HandNum {
        HAND1,HAND2
    }

    public enum Direction {
        CW,CCW
    }

    public void setHand1Speed(float speed) {
        this.hand1Speed = speed;
    }

    public void setHand2Speed(float speed) {
        this.hand2Speed = speed;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public Clock(float size) {
        this.size = size;
        this.radius = size/2;

        //Default hand angle
        this.hand1Angle = 0;
        this.hand2Angle = 0;

        //Default speed in degrees per second
        this.hand1Speed = 20;
        this.hand2Speed = 20;

        //Prevent Pane from resizing
        this.setMinHeight(size);
        this.setMaxHeight(size);
        this.setMinWidth(size);
        this.setMaxWidth(size);

        initializeClock();
    }

    private void initializeClock() {
        Circle clockBody = drawCircle();
        Circle clockPivot = new Circle(this.size/2,this.size/2,(size * .06),Paint.valueOf("black"));
        Line hand1 = drawHand(1);
        Line hand2 = drawHand(2);

        getStylesheets().add("UI/clockStyle.css");

        //CSS ids
        clockBody.setId("clockFace");
        clockPivot.setId("clockPivot");
        hand1.setId("hand1");
        hand2.setId("hand2");

        this.getChildren().addAll(clockBody, hand1, hand2, clockPivot);
    }

    public void rotateForTime(int seconds, HandNum hand, Direction direction) {
        float angle = seconds;

        angle = direction == Direction.CCW ? -angle : angle;
        angle *= hand == HandNum.HAND1 ? this.hand1Speed : this.hand2Speed;

        moveHandToAngle(angle,hand);
    }

    public void moveHandToAngle(float angle, HandNum hand) {
        isMoving = true;

        if(hand == HandNum.HAND1) {
            float duration = (Math.abs(this.hand1Angle - angle)/this.hand1Speed);

            Timeline hand1Animation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(this.hand1Rotation.angleProperty(), this.hand1Angle)),
                    new KeyFrame(Duration.seconds(duration), new KeyValue(this.hand1Rotation.angleProperty(), angle)));

            hand1Animation.setOnFinished(e -> isMoving = false);
            hand1Animation.play();
            this.hand1Angle = angle;
        }else {
            float duration = (Math.abs(this.hand2Angle - angle)/this.hand2Speed);

            Timeline hand2Animation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(this.hand1Rotation.angleProperty(), this.hand2Angle)),
                    new KeyFrame(Duration.seconds(duration), new KeyValue(this.hand2Rotation.angleProperty(), angle)));

            hand2Animation.setOnFinished(e -> isMoving = false);
            hand2Animation.play();
            this.hand2Angle = angle;
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

        hand.setEndX(this.size/2);
        hand.setEndY(size*.9);

        if(handNum == 1) {
            this.hand1Rotation = new Rotate();
            this.hand1Rotation.pivotXProperty().bind(hand.startXProperty());
            this.hand1Rotation.pivotYProperty().bind(hand.startYProperty());

            hand.getTransforms().add(this.hand1Rotation);
        }else {
            this.hand2Rotation = new Rotate();
            this.hand2Rotation.pivotXProperty().bind(hand.startXProperty());
            this.hand2Rotation.pivotYProperty().bind(hand.startYProperty());

            hand.getTransforms().add(this.hand2Rotation);
        }

        return hand;
    }

}
