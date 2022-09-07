package UI;

import javafx.util.Duration;

// Defines a complete action from start to finish.
// Multiple actions should be able to be composed sequentially
public class ClockAction {

    // Measured in degrees
    private final Float angle1;
    private final Float angle2;

    // Measured in degrees per second
    private final Float speed;

    // Defines how long to pause after this action is completed
    private final Duration delay;

    public ClockAction(float angle1, float angle2, float speed, Duration delay) {

        // 0 speed is converted to practically 0 to prevent divide errors
        this.speed = speed == 0 ? (float).00001 : speed;

        this.angle1 = angle1;
        this.angle2 = angle2;

        this.delay = delay;
    }

    public Float getAngle1() {
        return angle1;
    }

    public Float getAngle2() {
        return angle2;
    }

    public Float getSpeed() {
        return speed;
    }

    public Duration getDelay() {
        return delay;
    }
}