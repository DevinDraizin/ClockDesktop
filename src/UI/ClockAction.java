package UI;

// Defines a complete action from start to finish.
// Multiple actions should be able to be composed sequentially
public class ClockAction {

    // Measured in degrees
    private final Float angle1;
    private final Float angle2;

    // Measured in degrees per second
    private final Float speed1;
    private final Float speed2;

    public ClockAction(float angle1, float angle2, float speed1, float speed2) {

        this.speed1 = speed1;
        this.speed2 = speed2;

        this.angle1 = angle1;
        this.angle2 = angle2;

    }

    public Float getAngle1() {
        return angle1;
    }

    public Float getAngle2() {
        return angle2;
    }

    public Float getSpeed1() {
        return speed1;
    }

    public Float getSpeed2() {
        return speed2;
    }
}