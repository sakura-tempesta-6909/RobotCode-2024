package frc.robot.mode;

import frc.robot.components.link.LinkParameter;
import frc.robot.components.shooter.ShooterParameter;
import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LEDModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;
import frc.robot.subClass.Util;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_midDrive;
        DriveModel.driveSideSpeed = Util.deadband(driveController.getLeftX());
        DriveModel.driveFowardSpeed = Util.deadband(-driveController.getLeftY()); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = Util.deadband(-driveController.getRightX()); //スティックを右に倒すと反時計回りになるように変更
        DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
        if(driveController.getRightBumper()) {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
        } 
        

        /**SHOOTER LINK系 */

        /** SOURCEからのインテイクの角度にする＆インテイクする */
        if(operateController.getRightBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_intakeNote;
            ShooterModel.shooterMode = ShooterMode.s_intake;
        }
        
        /** アウトテイクする */
        if(operateController.getXButton()) {
            ShooterModel.shooterMode = ShooterMode.s_outtake;
        }

        /** SPEAKERの真下からのシュートの角度にする＆回転速度を上げる */
        if(operateController.getYButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootBelow;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** podiumからのシュートの角度にする＆回転速度を上げる  */
        if(operateController.getBButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootPodium;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** SPEAKERのleft&rightからのシュートの角度にする＆回転速度上げる */
        if(operateController.getAButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /**  SPEAKERにシュートする*/
        if(0.6 <= operateController.getRightTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** AMPからのシュートの角度にする＆回転速度を上げる */
        if(operateController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /** AMPにシュートする */
        if(0.6 <= operateController.getLeftTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }
        

        if (ShooterMeasuredState.isNoteGet) {
            LEDModel.pattern = LEDModel.LEDFlashes.NOTEGet;
        } else if (LinkMeasuredState.linkUnderStage) {
            LEDModel.pattern = LEDModel.LEDFlashes.UnderStage;
        } else if (ShooterMeasuredState.shooterSpeed > ShooterParameter.Speed.ShooterSpeedWhenPusherMove) {
            LEDModel.pattern = LEDModel.LEDFlashes.ShooterSpeed;
        } else if (LinkParameter.Current.ClimbCurrent > LinkMeasuredState.linkCurrent) {
            LEDModel.pattern = LEDModel.LEDFlashes.ClimbSuccess;
        }
    }
    
}
