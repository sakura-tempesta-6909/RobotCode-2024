package frc.robot.mode;

import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.DriveModel.DriveOriented;
import frc.robot.domain.model.ShooterModel.ShooterMode;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_fastDrive;
        DriveModel.driveXSpeed = driveController.getLeftX();
        DriveModel.driveYSpeed = driveController.getLeftY();
        DriveModel.driveThetaSpeed = driveController.getRightX();
        if(driveController.getRightBumper()) {
            if(DriveModel.driveOriented == DriveModel.DriveOriented.s_fieldOriented) {
                DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
            } else {
                DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
            }
        } 
    }
}
