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
    public void handle(Gamepad gamepad) {
        hanger.setPower(gamepad.right_stick_y);
    }

    @Override
    public void onAPress() {
        hanger.resetEncoder();
    }

    @Override
    public void onBPress() {
        hanger.disruptHanger();
    }

    @Override
    public void onXPress() {

    }

    @Override
    public void onYPress() {

    }

    @Override
    public void onDpadUpPress() {
        hanger.setHangerGoal(Hanger.HangerPosition.HIGH);
    }

    @Override
    public void onDpadDownPress() {
        hanger.setHangerGoal(Hanger.HangerPosition.LOW);
    }

    @Override
    public void onDpadLeftPress() {
        hanger.setHangerGoal(Hanger.HangerPosition.MID);
    }

    @Override
    public void onDpadRightPress() {
        hanger.setHangerGoal(Hanger.HangerPosition.MID);
    }

    @Override
    public void updateTelemetry() {
        XbotTelemetry.addData("Hanger Encoder Position", hanger.getEncoderValue());
        XbotTelemetry.addData("Are Hanger Motors Busy?", hanger.areMotorsBusy());
    }

    @Override
    public void stop() {
        hanger.setPower(0);
    }
}
