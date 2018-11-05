package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

public abstract class XbotOperatorSubHandler {

    protected RobotSubsystemManager robotSystemsManager = RobotSubsystemManager.getInstance();

    public abstract void start();
    public abstract void handle(Gamepad gamepad);
    public abstract void onAPress();
    public abstract void onBPress();
    public abstract void onXPress();
    public abstract void onYPress();
    public abstract void onDpadUpPress();
    public abstract void onDpadDownPress();
    public abstract void onDpadLeftPress();
    public abstract void onDpadRightPress();
    public abstract void stop();
    public abstract void updateTelemetry();
}
