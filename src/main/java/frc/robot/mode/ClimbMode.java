package frc.robot.mode;

import java.sql.ClientInfoStatus;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.infrastructure.Link;
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
        }

        /** Linkを下方向に動かす */
        if(driveController.getLeftTriggerAxis() > 0.1) {
            LinkModel.climbSpeed = -driveController.getLeftTriggerAxis();
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climb;
            //Link.linkMotorLeft.set(ControlMode.PercentOutput, LinkModel.climbSpeed * driveController.getRightTriggerAxis());
            //Link.linkMotorRight.set(ControlMode.PercentOutput, LinkModel.climbSpeed * driveController.getRightTriggerAxis());
            //linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
        }
    }
}
