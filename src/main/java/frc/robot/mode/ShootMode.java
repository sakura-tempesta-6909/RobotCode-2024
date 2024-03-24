package frc.robot.mode;

import frc.robot.components.shooter.infrastructure.Shooter;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;

class ShootMode extends ModeManager {
    public static void changeModel() {
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
        }

        /** podiumからのシュートの角度にする＆回転速度を上げる  */
        if(operateController.getBButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootPodium;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /** SPEAKERのleft&rightからのシュートの角度にする＆回転速度上げる */
        if(operateController.getAButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /**  SPEAKERにシュートする*/
        if(-1 <= operateController.getRightTriggerAxis() && operateController.getRightTriggerAxis() <= 1) {
            ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
        }

        /** AMPからのシュートの角度にする＆回転速度を上げる */
        if(operateController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

        /** AMPにシュートする */
        if(-1 <= operateController.getLeftTriggerAxis() && operateController.getLeftTriggerAxis() <= 1) {
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
        }


        /** SPEAKERの左右からShootできるようにリンクの角度を変えて回転数を早くする */
        if(operateController.getBButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }


    }
    
}

/**
 * shootする時にリンクの角度を確認する（事故防止）
 * ->ampとshootの違い
 */

