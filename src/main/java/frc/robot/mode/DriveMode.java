package frc.robot.mode;

import frc.robot.domain.model.DriveModel;

public class DriveMode extends ModeManager {
    public static void changeState() {
        driveModel.driveBaseMode = DriveModel.DriveBaseMode.s_fastDrive;
        driveModel.driveXSpeed = driveController.getLeftY();
        driveModel.driveZRotation = driveController.getRightX();
    }
}
