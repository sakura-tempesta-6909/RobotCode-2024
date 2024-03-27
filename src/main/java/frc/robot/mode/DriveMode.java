package frc.robot.mode;

import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LEDModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.ShooterModel.ShooterMode;
import frc.robot.subClass.Util;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        DriveModel.driveSideSpeed = Util.deadband(driveController.getLeftX());
        DriveModel.driveFowardSpeed = Util.deadband(-driveController.getLeftY()); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = Util.deadband(-driveController.getRightX()); //スティックを右に倒すと反時計回りになるように変更
        DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
        if(driveController.getRightBumper()) {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
        } 
        if (operateController.getRightBumper()) {
            ShooterModel.shooterMode = ShooterModel.ShooterMode.s_shootSpeaker;
        } else if (operateController.getLeftBumper()) {
            ShooterModel.shooterMode = ShooterModel.ShooterMode.s_intake;
        } else if (operateController.getBButton()) {
            ShooterModel.shooterMode = ShooterMode.s_outtake;
        } else if (operateController.getAButton()) {
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
        } else {
            ShooterModel.shooterMode = ShooterMode.s_stopIntake;
        }

        if (ShooterMeasuredState.isNoteGet) {
            LEDModel.pattern = LEDModel.LEDFlashes.NOTEGet;
        } else if (LinkMeasuredState.linkUnderStage) {
            LEDModel.pattern = LEDModel.LEDFlashes.Under720mm;
        }
    }
}
