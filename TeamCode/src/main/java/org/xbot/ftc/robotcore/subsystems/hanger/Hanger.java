package org.xbot.ftc.robotcore.subsystems.hanger;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.utils.GameClock;

public class Hanger extends XbotSubsystem {

    private static Hanger instance = null;
    private static boolean initialized = false;

    private boolean hasActiveGoal = false;

    private DcMotor hangerMotor;

    public enum HangerPosition {
        HIGH, MID, LOW;
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
            hangerMotor.setPower(power);
        }
    }

    public void resetEncoder() {
        hangerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setHangerGoal(int goal) {
        hangerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangerMotor.setTargetPosition(goal);
    }

    public void runUntilGoalMet() {
        setPower(0);
        while (hangerMotor.isBusy() && opMode.opModeIsActive()) {
            //Wait Until Goal Met
        }
        setPower(0);
    }

    public void stopEncoderDrive() {
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setHangerGoal(HangerPosition position) {
        switch (position) {
            case LOW:
                setHangerGoal(-100);
                break;
            case MID:
                setHangerGoal(-9000);
                break;
            case HIGH:
                setHangerGoal(-15000);
                break;
        }
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
