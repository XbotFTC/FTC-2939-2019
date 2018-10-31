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
    }

    @Override
    public void handle(Gamepad gamepad1, Gamepad gamepad2) {
        hanger.setPower(gamepad2.right_stick_y);
    }

    @Override
    public void stop() {
        hanger.setPower(0);
    }

    @Override
    public void updateTelemetry() {
        XbotTelemetry.addData("I'm running", 0);
    }
}
