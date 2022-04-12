package UI;

// Defines a complete action from start to finish.
// Multiple actions should be able to be composed sequentially
public class ClockAction {

    private final Clock.Direction direction1;
    private final Clock.Direction direction2;

    // Measured in seconds
    private final Float duration1;
    private final Float duration2;

    // Measured in degrees
    private final Float angle1;
    private final Float angle2;

    // Measured in degrees per second
    private final Float speed1;
    private final Float speed2;

    private final boolean durationMode;

    public ClockAction(boolean mode, float value1, float value2, float speed1, float speed2,
                       Clock.Direction direction1, Clock.Direction direction2) {

        this.direction1 = direction1;
        this.direction2 = direction2;

        this.speed1 = speed1;
        this.speed2 = speed2;

        this.durationMode = mode;

        if(this.durationMode) {
            this.duration1 = value1;
            this.duration2 = value2;
            this.angle1 = null;
            this.angle2 = null;
        }else {
            this.duration1 = null;
            this.duration2 = null;
            this.angle1 = value1;
            this.angle2 = value2;
        }
    }

    // Setters
    public Clock.Direction getDirection1() {
        return direction1;
    }

    public Clock.Direction getDirection2() {
        return direction2;
    }

    public Float getDuration1() {
        return duration1;
    }

    public Float getDuration2() {
        return duration2;
    }

    public Float getAngle1() {
        return angle1;
    }

    public Float getAngle2() {
        return angle2;
    }

    public boolean isDurationMode() {
        return durationMode;
    }

    public Float getSpeed1() {
        return speed1;
    }

    public Float getSpeed2() {
        return speed2;
    }
}