package org.xbot.ftc.operatingcode.teleop;

import org.xbot.ftc.operatingcode.teleop.operator_1.TeleOpDrive;
import org.xbot.ftc.operatingcode.teleop.operator_2.TeleOpHanger;

public class XbotTeleOpSubHandlerRegister {

    /**
     * Use this method to register sub-handler(s)
     * When registering a sub-handler make sure that it inherits XbotOperatorSubHandler
     * @param xbotTeleOp
     */
    public void registerHandlers(XbotTeleOp xbotTeleOp) {
        xbotTeleOp.registerHandler(new TeleOpDrive(), XbotTeleOp.SubHandlerController.DRIVER);
        xbotTeleOp.registerHandler(new TeleOpHanger(), XbotTeleOp.SubHandlerController.OPERATOR);
    }
}
