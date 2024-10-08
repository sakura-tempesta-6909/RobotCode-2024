package frc.robot.mode;

import frc.robot.components.link.LinkParameter;
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
            DriveModel.setAngle = -50;
        }
        if(driveController.getAButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 180;
        }
        if(driveController.getXButton()) {
            DriveModel.driveAngle = true;
            DriveModel.setAngle = 50;  
        }
        if(130 < driveController.getPOV() && driveController.getPOV() < 230 ) {
            DriveModel.resetGyroSensor = true;
            DriveModel.offset = 0;
        }
        

        /**SHOOTER LINK系 */

        /** SOURCEからのインテイクの角度にする＆インテイクする */
        if(operateController.getAButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_intakeNote;
            ShooterModel.shooterMode = ShooterMode.s_intake;
        }
        
        /** アウトテイクする */
        if(operateController.getXButton()) {
            ShooterModel.shooterMode = ShooterMode.s_outtake;
        }

        /** SPEAKERの真下からのシュートの角度にする */
        if(operateController.getYButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootBelow;
        }

        /** podiumからのシュートの角度にする  */
        if(operateController.getBButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootPodium;
        }

        /** shooterの回転数を上げる */
        if(operateController.getRightBumper()) {
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /**  SPEAKERにシュートする*/
        if(0.6 <= operateController.getRightTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** AMPからのシュートの角度にする＆回転速度を上げる */
        if(operateController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_ampShoot;
        }

        /** AMPにシュートする */
        if(0.6 <= operateController.getLeftTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** 720mm以下にする */
        if(operateController.getBackButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_stageAngle;
        }

        /** Linkを微調整する */
        if(operateController.getLeftY() >= 0.8) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbDownFineAdjustment;
        } else if(operateController.getLeftY() <= -0.8) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_climbUpFineAdjustment;
        }

        if (ShooterMeasuredState.isNoteGet && ShooterModel.shooterMode == ShooterMode.s_intake) {
            LEDModel.pattern = LEDModel.LEDFlashes.NOTEGet;
        } else if (ShooterMeasuredState.readyToShoot) {
            LEDModel.pattern = LEDModel.LEDFlashes.ShooterSpeed;
        } else if (LinkParameter.Current.ClimbCurrent <= LinkMeasuredState.linkCurrent) {
            LEDModel.pattern = LEDModel.LEDFlashes.ClimbSuccess;
        } else if (LinkMeasuredState.linkUnderStageHeight) {
            LEDModel.pattern = LEDModel.LEDFlashes.UnderStage;
        }
    }
    
}
