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
        drive.setMotorPowers(Range.clip(leftPower, -1.0, 1.0),
                Range.clip(rightPower, -1.0, 1.0));
    }

    public void stop() {
        drive.setMotorPowers(0);
    }
}
