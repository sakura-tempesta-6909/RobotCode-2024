package frc.robot.mode;

import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.ShooterModel.ShooterMode;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        DriveModel.driveXSpeed = driveController.getLeftY();
        DriveModel.driveZRotation = driveController.getRightX();
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
    }
}
