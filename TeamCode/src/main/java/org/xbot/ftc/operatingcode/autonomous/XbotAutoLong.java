package org.xbot.ftc.operatingcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.hanger.Hanger;
import org.xbot.ftc.robotcore.subsystems.marker.MarkerDropper;

@Autonomous(name="Main: AutoLong", group="main")
public class XbotAutoLong extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BaseRobot.initOpMode(this, hardwareMap, telemetry);
        RobotSubsystemManager manager = RobotSubsystemManager.getInstance();
        Hanger hanger = (Hanger) manager.getSubsystem(Hanger.class.getName());
        Drive drive = (Drive) manager.getSubsystem(Drive.class.getName());
        MarkerDropper markerDropper = (MarkerDropper) manager.getSubsystem(MarkerDropper.class.getName());
        hanger.resetEncoder();

        waitForStart();

        hanger.setHangerGoal(Hanger.HangerPosition.HIGH);
        hanger.runUntilGoalMet();
        drive.setMotorPowers(0.5);
        Thread.sleep(3000);
        drive.stop();
        markerDropper.drop();
        RobotSubsystemManager.getInstance().stop();
    }
}
