package org.xbot.ftc.operatingcode.teleop.operator_2;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.hanger.Hanger;
import org.xbot.ftc.robotcore.utils.XbotTelemetry;

public class TeleOpHanger extends XbotOperatorSubHandler {

    private Hanger hanger;

    @Override
    public void start() {
        hanger = (Hanger)robotSystemsManager.getSubsystem(Hanger.class.getName());
        hanger.resetEncoder();
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.dpad_up) {
            hanger.setHangerGoal(-17000);
        } else if (gamepad2.dpad_down) {
            hanger.setHangerGoal(-9000);
        } else if (gamepad2.dpad_left) {
            hanger.setHangerGoal(-500);
        } else if (gamepad2.a) {
            hanger.resetEncoder();
        } else if (gamepad2.b) {
            hanger.stopEncoderDrive();
        } else if ((gamepad2.right_stick_y > 0) || (gamepad2.right_stick_y < 0)) {
            hanger.setPower(gamepad2.right_stick_y);
        } else {
            hanger.setPower(0);
        }
    }

    @Override
    public void stop() {
        hanger.setPower(0);
    }

    @Override
    public void updateTelemetry() {
        XbotTelemetry.addData("Hanger Encoder Position", hanger.getEncoderValue());
        XbotTelemetry.addData("Are Hanger Motors Busy?", hanger.areMotorsBusy());
    }
}
