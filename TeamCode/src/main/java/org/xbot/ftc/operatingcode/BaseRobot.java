package org.xbot.ftc.operatingcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

public abstract class BaseRobot {

    public static void initOpMode(HardwareMap hardwareMap, Telemetry telemetry) {
        RobotSubsystemManager.getInstance().init(hardwareMap, telemetry);
    }
}
