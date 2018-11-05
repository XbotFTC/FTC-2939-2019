package org.xbot.ftc.robotcore.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class XbotSubsystem {

    protected HardwareMap hardwareMap;
    protected Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    public String getClassName() {
        throw new UnsupportedOperationException("Subsystem Class Name Not Set");
    }

    public abstract void shutdownSubsystem();
}
