package org.xbot.ftc.robotcore;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;
import org.xbot.ftc.robotcore.subsystems.drive.Drive;
import org.xbot.ftc.robotcore.subsystems.hanger.Hanger;

public class XbotSubsystemRegister {

    public void registerListeners(RobotSubsystemManager robotSubsystemManager) {
        robotSubsystemManager.registerSubsystem(Drive.getInstance());
        robotSubsystemManager.registerSubsystem(Hanger.getInstance());
    }
}