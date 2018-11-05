package org.xbot.ftc.robotcore.subsystems.hanger;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class Hanger extends XbotSubsystem {

    private static Hanger instance = null;
    private static boolean initialized = false;

    private DcMotor hangerMotor;

    public static final int HANGER_FULLY_EXTENDED = -16000;
    public static final int HANGER_HALF_EXTENDED = -8000;
    public static final int HANGER_FULLY_DOWN = -100;

    public enum HangerPosition {
        HIGH, MID, LOW
    }

    private Hanger() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;

        super.init(hardwareMap, telemetry);
        hangerMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.HANGER_MOTOR);
        hangerMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        initialized = true;
    }

    public void setPower(double power) {
        if (hangerMotor.isBusy()) {
            hangerMotor.setPower(1.0);
            //Do Nothing
        } else {
            hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hangerMotor.setPower(Range.clip(power, -1, 1));
        }
    }

    public void resetEncoder() {
        hangerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runUntilGoalMet() {
        setPower(0);
        while (hangerMotor.isBusy()) {
            // Wait Until Goal Met
        }
        setPower(0);
    }

    public void setHangerGoal(int goal) {
        hangerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangerMotor.setTargetPosition(goal);
    }

    public void setHangerGoal(HangerPosition position) {
        switch (position) {
            case LOW:
                setHangerGoal(HANGER_FULLY_DOWN);
                break;
            case MID:
                setHangerGoal(HANGER_HALF_EXTENDED);
                break;
            case HIGH:
                setHangerGoal(HANGER_FULLY_EXTENDED);
                break;
        }
    }

    public void disruptHanger() {
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int getEncoderValue() {
        return hangerMotor.getCurrentPosition();
    }

    public boolean areMotorsBusy() {
        return hangerMotor.isBusy();
    }

    @Override
    public void shutdownSubsystem() {
        hangerMotor.setPower(0);
        initialized = false;
    }

    @Override
    public String getClassName() {
        return Hanger.class.getName();
    }

    public static Hanger getInstance() {
        if (instance == null) {
            instance = new Hanger();
        }
        return instance;
    }
}
