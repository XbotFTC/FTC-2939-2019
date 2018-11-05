package org.xbot.ftc.robotcore.subsystems.drive;

import com.qualcomm.robotcore.hardware.Gamepad;

public class VideoGameDrive {

    private Drive drive;

    protected VideoGameDrive(Drive drive) {
        this.drive = drive;
    }

    public void drive(Gamepad gamepad) {
        drive.setMotorPowers(gamepad.right_trigger + Math.min(0, gamepad.left_stick_x),
                            gamepad.right_trigger - Math.abs(Math.max(0, gamepad.left_stick_x)));
    }

    public void stop() {
        drive.setMotorPowers(0);
    }
}
