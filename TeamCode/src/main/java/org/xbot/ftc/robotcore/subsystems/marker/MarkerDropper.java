package org.xbot.ftc.robotcore.subsystems.marker;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

public class MarkerDropper extends XbotSubsystem {

    private static MarkerDropper instance = null;
    private static boolean initialized = false;

    private Servo servo;

    public enum Position {
        DOWN, UP
    }

    private MarkerDropper() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        servo = hardwareMap.get(Servo.class, XbotRobotConstants.MARKER_SERVO);

        initialized = true;
    }

    public void setPosition(double position) {
        servo.setPosition(position);
    }

    public void setPosition(Position position) {
        if (position == Position.DOWN) {
            servo.setPosition(0);
        } else if (position == Position.UP) {
            servo.setPosition(1);
        }
    }

    public void drop() {
        setPosition(Position.DOWN);
    }

    @Override
    public void shutdownSubsystem() {
        initialized = false;
    }

    @Override
    public String getClassName() {
        return MarkerDropper.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new MarkerDropper();
        return instance;
    }
}
