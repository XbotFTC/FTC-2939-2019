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
        hangerMotor.setPower(power);
    }

    @Override
    public void shutdownSubsystem() {
        hangerMotor.setPower(0);
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
