package frc.robot.mode;

import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LEDModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.ShooterModel.ShooterMode;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        DriveModel.driveSideSpeed = driveController.getLeftX();
        DriveModel.driveFowardSpeed = -driveController.getLeftY(); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = -driveController.getRightX(); //スティックを右に倒すと反時計回りになるように変更
        if(driveController.getRightBumper()) {
            ShooterModel.shooterMode = ShooterModel.ShooterMode.s_shootSpeaker;
        } else if (driveController.getLeftBumper()) {
            ShooterModel.shooterMode = ShooterModel.ShooterMode.s_intake;
        } else if (driveController.getBButton()){
            ShooterModel.shooterMode = ShooterMode.s_outtake;
        } else if (driveController.getAButton()){
            ShooterModel.shooterMode = ShooterMode.s_shootAmp;
        } else {
            ShooterModel.shooterMode = ShooterMode.s_stopIntake;
        }

        if (ShooterMeasuredState.isNoteGet) {
            LEDModel.pattern = LEDModel.LEDFlashes.NOTEGet;
        }
        if(DriveModel.driveOriented == DriveModel.DriveOriented.s_fieldOriented) {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
        } else {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
        }
    }
}
