package frc.robot.mode;

import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;

class ClimbMode extends ModeManager {
    public static void changeModel() {
         /** LinkをClimb時の角度にする */
        if(driveController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbAngle;
        }

        /** Linkを上方向に微調整する */
        if(0.8 <= driveController.getRightTriggerAxis()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbUpFineAdjustment;
        } else {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** Linkを下方向に動かす */
        if(0.8 <= driveController.getLeftTriggerAxis()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climb;
        } else {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** DriveModeに切り替える */
        if(driveController.getBackButton()) {
            
        }
    }
}
