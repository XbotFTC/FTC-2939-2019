package org.xbot.ftc.operatingcode.teleop.operator_1;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.xbot.ftc.robotcore.subsystems.drive.ArcadeDrive;
import org.xbot.ftc.robotcore.subsystems.drive.TankDrive;
import org.xbot.ftc.robotcore.utils.XbotTelemetry;
import org.xbot.ftc.operatingcode.teleop.XbotOperatorSubHandler;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;

public class TeleOpDrive extends XbotOperatorSubHandler {

    private Drive drive;
    private TankDrive tankDrive;

    @Override
    public void start() {
        drive = (Drive)robotSystemsManager.getSubsystem(Drive.class.getName());
        tankDrive = drive.getTankDrive();
    }

    @Override
    public void handle(Gamepad gamepad) {
        tankDrive.drive(gamepad);
    }

    @Override
    public void onAPress() {

    }

    @Override
    public void onBPress() {

    }

    @Override
    public void onXPress() {

    }

    @Override
    public void onYPress() {

    }

    @Override
    public void onDpadUpPress() {

    }

    @Override
    public void onDpadDownPress() {

    }

    @Override
    public void onDpadLeftPress() {

    }

    @Override
    public void onDpadRightPress() {

    }

    @Override
    public void updateTelemetry() {

    }

    @Override
    public void stop() {
        drive.stop();
    }
}
