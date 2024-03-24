package frc.robot.mode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.DriveModel.DriveOriented;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        DriveModel.driveSideSpeed = driveController.getLeftX();
        DriveModel.driveFowardSpeed = -driveController.getLeftY(); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = -driveController.getRightX(); //スティックを右に倒すと反時計回りになるように変更
        if(driveController.getRightBumper()) {
            if(DriveModel.driveOriented == DriveModel.DriveOriented.s_fieldOriented) {
                DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
            } else {
                DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
            }
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
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /**  SPEAKERにシュートする*/
        if(0.6 <= operateController.getRightTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
        }

        /** AMPからのシュートの角度にする＆回転速度を上げる */
        if(operateController.getLeftBumper()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
            LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        }

        /** AMPにシュートする */
        if(0.6 <= operateController.getLeftTriggerAxis()) {
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
        }


        /** SPEAKERの左右からShootできるようにリンクの角度を変えて回転数を早くする */
        if(operateController.getBButton()) {
            LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootSide;
            ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
        }

    }
    
}
