package org.xbot.ftc.operatingcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.xbot.ftc.operatingcode.BaseRobot;
import org.xbot.ftc.robotcore.utils.XbotTelemetry;
import org.xbot.ftc.robotcore.subsystems.RobotSubsystemManager;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Main: TeleOp", group="Main")
public class XbotTeleOp extends OpMode {

    private List<XbotOperatorSubHandler> driverHandlers = new ArrayList<>();
    private List<XbotOperatorSubHandler> operatorHandlers = new ArrayList<>();

    public enum SubHandlerController {
        DRIVER, OPERATOR
    }

    public void registerHandler(XbotOperatorSubHandler handler, SubHandlerController controller) {
        if (controller == SubHandlerController.DRIVER) {
            driverHandlers.add(handler);
        } else if (controller == SubHandlerController.OPERATOR) {
            operatorHandlers.add(handler);
        }
    }

    @Override
    public void start() {
        for (XbotOperatorSubHandler handler : driverHandlers) {
            handler.start();
        }

        for (XbotOperatorSubHandler handler : operatorHandlers) {
            handler.start();
        }
    }

    @Override
    public void init() {
        telemetry.addData("Listeners:", "Registering");
        telemetry.update();
        BaseRobot.initOpMode(hardwareMap, telemetry);
        new XbotTeleOpSubHandlerRegister().registerHandlers(this);
        telemetry.addData("Listeners:", "Registered");
        telemetry.update();
    }

    @Override
    public void loop() {
        handleControllerInput(driverHandlers, gamepad1);
        handleControllerInput(operatorHandlers, gamepad2);

        for (XbotTelemetry telemetryData : XbotTelemetry.getDataToAddToTelemetry()) {
            telemetry.addLine();
            telemetry.addData(telemetryData.getCaption(), telemetryData.getValue());
        }
        telemetry.addData("Game Clock: ", RobotSubsystemManager.getInstance().getGameClock().getTimeElapsed());
        XbotTelemetry.clearData();
        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
        for (XbotOperatorSubHandler handler : driverHandlers) {
            handler.stop();
        }

        for (XbotOperatorSubHandler handler : operatorHandlers) {
            handler.stop();
        }

        RobotSubsystemManager.getInstance().stop();
    }

    private void handleControllerInput(List<XbotOperatorSubHandler> handlers, Gamepad gamepad) {
        for (XbotOperatorSubHandler handler : handlers) {
            handler.handle(gamepad1);

            if (gamepad.a) {
                handler.onAPress();
            }
            if (gamepad.b) {
                handler.onBPress();
            }
            if (gamepad.x) {
                handler.onXPress();
            }
            if (gamepad.y) {
                handler.onYPress();
            }
            if (gamepad.dpad_down) {
                handler.onDpadDownPress();
            }
            if (gamepad.dpad_up) {
                handler.onDpadUpPress();
            }
            if (gamepad.dpad_left) {
                handler.onDpadLeftPress();
            }
            if (gamepad.dpad_right) {
                handler.onDpadRightPress();
            }
            handler.updateTelemetry();
        }
    }
}
