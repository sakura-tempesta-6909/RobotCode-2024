package frc.robot.mode;

import frc.robot.domain.model.DriveModel;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveBaseMode = DriveModel.DriveBaseMode.s_fastDrive;
        DriveModel.driveXSpeed = driveController.getLeftY();
        DriveModel.driveZRotation = driveController.getRightX();
    }
}
