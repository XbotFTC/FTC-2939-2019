package org.xbot.ftc.robotcore.subsystems.marker;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

public class MarkerDropper extends XbotSubsystem {

    private static MarkerDropper instance = null;
    private static boolean initialized = false;

    private Servo servo;

    private MarkerDropper() {
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        servo = hardwareMap.get(Servo.class, "");

        this.initialized = true;
    }

    @Override
    public void shutdownSubsystem() {

    }

    @Override
    public String getClassName() {
        return Drive.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new MarkerDropper();
        return instance;
    }
}
