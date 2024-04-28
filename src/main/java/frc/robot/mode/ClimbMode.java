package frc.robot.mode;

import java.sql.ClientInfoStatus;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.infrastructure.Link;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.subClass.Util;

class ClimbMode extends ModeManager {
    public static void changeModel() {
        if(operateController.getAButtonPressed() || operateController.getBButtonPressed() || operateController.getYButtonPressed() || operateController.getRightBumperPressed() || operateController.getBackButtonPressed()) {
            LinkModel.resetPID = true;
        }
        DriveModel.driveMovement = DriveModel.DriveMovement.s_midDrive;
        if(0.6 <= driveController.getLeftTriggerAxis()){
            DriveModel.driveMovement = DriveModel.DriveMovement.s_slowDrive;
        }else if (0.6 <= driveController.getRightTriggerAxis()) {
            DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        }

        DriveModel.driveSideSpeed = Util.deadband(driveController.getLeftX());
        DriveModel.driveForwardSpeed = Util.deadband(-driveController.getLeftY()); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = Util.deadband(-driveController.getRightX()); //スティックを右に倒すと反時計回りになるように変更
        DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
        if(driveController.getRightBumper()) {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
        }
        if(driveController.getYButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 0;
        } 
        if(driveController.getBButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 55;
        }
        if(driveController.getAButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 180;
        }
        if(driveController.getXButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 305;  
        }
        if(130 < driveController.getPOV() && driveController.getPOV() < 230 ) {
            DriveModel.resetGyroSensor = true;
        }

        /** LinkをClimb時の角度にする */
        if(operateController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbAngle;
        }

        /** Linkを上方向に微調整する */
        if(0.8 <= operateController.getRightTriggerAxis()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbUpFineAdjustment;
        }

        /** Linkを下方向に動かす */
        if(operateController.getLeftTriggerAxis() > 0.1) {
            LinkModel.climbSpeed = -driveController.getLeftTriggerAxis();
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climb;
            //Link.linkMotorLeft.set(ControlMode.PercentOutput, LinkModel.climbSpeed * driveController.getRightTriggerAxis());
            //Link.linkMotorRight.set(ControlMode.PercentOutput, LinkModel.climbSpeed * driveController.getRightTriggerAxis());
            //linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
        }
    }
}
