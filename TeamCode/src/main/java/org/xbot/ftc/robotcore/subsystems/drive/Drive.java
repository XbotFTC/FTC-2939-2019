package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.utils.GameClock;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;

public class Drive extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private DcMotor leftDriveMotors = null;
    private DcMotor rightDriveMotors = null;

    private static final double COUNTS_PER_MOTOR_REV = 1120;
    private static final double DRIVE_GEAR_REDUCTION = 1.0;
    private static final double WHEEL_DIAMETER_INCHES = 3.0 ;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                    (WHEEL_DIAMETER_INCHES * Math.PI);

    private ArcadeDrive arcadeDrive;
    private TankDrive tankDrive;

    public enum DrivePower {
        FULL(1.0), HALF(0.5), QUARTER(0.25);

        public final double POWER;

        DrivePower(double power) {
            this.POWER = power;
        }
    }

    public enum TurnDirection {
        LEFT(-1, 1), RIGHT(1, -1);

        public final double LEFT_POWER;
        public final double RIGHT_POWER;

        TurnDirection(double leftPower, double rightPower) {
            this.LEFT_POWER = leftPower;
            this.RIGHT_POWER = rightPower;
        }
    }

    private Drive() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;

        super.init(hardwareMap, telemetry);
        leftDriveMotors = hardwareMap.get(DcMotor.class, XbotRobotConstants.RIGHT_DRIVE);
        rightDriveMotors = hardwareMap.get(DcMotor.class, XbotRobotConstants.LEFT_DRIVE);

        leftDriveMotors.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveMotors.setDirection(DcMotorSimple.Direction.REVERSE);

        arcadeDrive = new ArcadeDrive(this);
        tankDrive = new TankDrive(this);

        initialized = true;
    }

    public void setMotorPowers(double leftPower, double rightPower) {
        leftDriveMotors.setPower(leftPower);
        rightDriveMotors.setPower(rightPower);
    }

    public void setMotorPowers(double power) {
        setMotorPowers(power, power);
    }

    public void turn(TurnDirection direction, DrivePower drivePower) {
        setMotorPowers(direction.LEFT_POWER * drivePower.POWER,
                direction.RIGHT_POWER * drivePower.POWER);
    }

    public void stop() {
        setMotorPowers(0);
    }

    public void encoderDrive(double power, double leftInches, double rightInches, double timeout) {
        int leftMotorTargets = leftDriveMotors.getCurrentPosition() +
                (int)(leftInches * COUNTS_PER_INCH);
        int rightMotorTargets = rightDriveMotors.getCurrentPosition() +
                (int)(rightInches * COUNTS_PER_INCH);
        leftDriveMotors.setTargetPosition(leftMotorTargets);
        rightDriveMotors.setTargetPosition(rightMotorTargets);

        leftDriveMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPowers(power);

        double startTime = GameClock.getInstance().getTimeElapsed();
        while (opMode.opModeIsActive() &&
                GameClock.getInstance().getTimeElapsed() - startTime < timeout &&
                areMotorsBusy()) {
            //Do Nothing
        }

        setMotorPowers(0);

        leftDriveMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    private boolean areMotorsBusy() {
        return leftDriveMotors.isBusy() || rightDriveMotors.isBusy();
    }

    public DcMotor getLeftDriveMotors() {
        return leftDriveMotors;
    }

    public DcMotor getRightDriveMotors() {
        return rightDriveMotors;
    }

    public ArcadeDrive getArcadeDrive() {
        return arcadeDrive;
    }

    public TankDrive getTankDrive() {
        return tankDrive;
    }

    @Override
    public void shutdownSubsystem() {
        setMotorPowers(0);
        initialized = false;
    }

    @Override
    public String getClassName() {
        return Drive.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new Drive();
        return instance;
    }
}
