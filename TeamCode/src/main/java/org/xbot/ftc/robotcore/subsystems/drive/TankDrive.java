package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public class TankDrive {

    private Drive drive;

    protected TankDrive(Drive drive) {
        this.drive = drive;
    }

    public void drive(Gamepad gamepad) {
        double leftPower = gamepad.left_stick_y;
        double rightPower = gamepad.right_stick_y;
        if (Math.abs(leftPower - rightPower) <= 0.1) {
            drive.setMotorPowers(Range.clip(Math.max(leftPower, rightPower), -1, 1));
        } else drive.setMotorPowers(Range.clip(leftPower, -1.0, 1.0),
                Range.clip(rightPower, -1.0, 1.0));
    }

    public void stop() {
        drive.setMotorPowers(0);
    }
}
